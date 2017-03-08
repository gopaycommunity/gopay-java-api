/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package cz.gopay.api.v3;

import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.AuthHeader;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.RefundPayment;
import cz.gopay.api.v3.model.payment.support.AccountStatement;
import cz.gopay.api.v3.model.payment.support.PaymentInstrumentRoot;

import org.apache.log4j.Logger;

import java.util.List;

import javax.ws.rs.WebApplicationException;

/**
 * @author Zbynek Novak novak.zbynek@gmail.com
 * @author František Sichinger
 */
public abstract class AbstractGPConnector implements IGPConnector {

    protected static final Logger logger = Logger.getLogger(AbstractGPConnector.class);

    public static int CONNECTION_POOL_SIZE = 1;
    public static int CONNECTION_SETUP_TO = 1;
    public static int CONNECTION_SERVICE_TO = 1;

    protected String apiUrl;

    protected AccessToken accessToken;

    public AbstractGPConnector(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public AbstractGPConnector(String apiUrl, AccessToken token) {
        this(apiUrl);
        this.accessToken = token;
    }

    protected abstract <T> T createRESTClientProxy(String apiUrl, Class<T> proxy);

    @Override
    public IGPConnector getAppToken(String clientId, String clientCredentials) throws GPClientException {
        return getAppToken(clientId, clientCredentials, OAuth.SCOPE_PAYMENT_CREATE);
    }

    @Override
    public IGPConnector getAppToken(String clientId, String clientCredentials, String scope) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": get-token [" + clientId + "]");

            AuthClient simple = createRESTClientProxy(apiUrl, AuthClient.class);

            accessToken = simple.loginApplication(AuthHeader.build(clientId, clientCredentials),
                    OAuth.GRANT_TYPE_CLIENT_CREDENTIALS, scope != null ? scope : OAuth.SCOPE_PAYMENT_ALL);

            logger.debug(
                    getClass().getSimpleName() + ": get-token [" + clientId + "] -> [" + accessToken.getAccessToken() + "]");

        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": get-token Error [" + clientId + "] RC ["
                    + e.getResponse().getStatus() + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }

        return this;
    }

    @Override
    public Payment createPayment(BasePayment payment) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": create-payment payer[" + payment.getPayer() + "] -> ["
                    + payment.getTarget() + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.createPayment(AuthHeader
                    .build(accessToken != null ? accessToken.getAccessToken() : null),
                    payment);

        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": create-payment Error [" + payment.getPayer() + "] -> ["
                    + payment.getTarget() + "] RC [" + e.getResponse().getStatus() + "] Ex: " + e.getResponse()
                    .getStatusInfo(),
                    e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult refundPayment(Long id, Long amount) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": refund-payment [" + id + "] amnt[" + amount + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            PaymentResult r = paymentClient
                    .refundPayment(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id, amount);
            return r;
        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": refund-payment Error [" + id + "] amnt[" + amount + "] RC ["
                    + e.getResponse().getStatus() + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }
    
    @Override
    public PaymentResult refundPayment(Long id, RefundPayment refundPayment) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": refund-payment [" + id + "] amnt[" + refundPayment + "]");
            
            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);
            
            PaymentResult r = paymentClient
                    .refundPayment(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null),
                            id, refundPayment);
            return r;
        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": refund-payment Error [" + id + "] amnt[" + refundPayment + "] RC ["
                    + e.getResponse().getStatus() + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }
        
        return null;
    }
    
    @Override
    public Payment createRecurrentPayment(Long id, NextPayment nextPayment) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": create-recurrent - parent id[" + id + "] ["
                    + nextPayment.getOrderNumber() + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.createRecurrentPayment(
                    AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id, nextPayment);

        } catch (WebApplicationException e) {
            logger.fatal(
                    getClass().getSimpleName() + ": create-recurrent Error parent id[" + id + "] [" + nextPayment
                    .getOrderNumber()
                    + "] RC [" + e.getResponse().getStatus() + "] Ex: " + e.getResponse().getStatusInfo(),
                    e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult voidRecurrency(Long id) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": void-recurrency parent id [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.voidRecurrence(AuthHeader
                    .build(accessToken != null ? accessToken.getAccessToken() : null),
                    id);

        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": void recurrency Error parent id[" + id + "] RC ["
                    + e.getResponse().getStatus() + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult capturePayment(Long id) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": capture payment [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.capturePayment(AuthHeader
                    .build(accessToken != null ? accessToken.getAccessToken() : null),
                    id);

        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": capture payment Error [" + id + "] RC ["
                    + e.getResponse().getStatus() + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult voidAuthorization(Long id) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": void auth payment [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient
                    .voidAuthorization(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id);

        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": void auth payment Error [" + id + "] RC ["
                    + e.getResponse().getStatus() + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public Payment paymentStatus(Long id) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": payment-status [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient
                    .getPayment(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id);
            
        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": payment-status Error [" + id + "] RC [" + e.getResponse()
                    .getStatus()
                    + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }
    
//  TODO   GPMAIN-3351  Nutne snizeni scopu
    @Override
    public PaymentInstrumentRoot generatePaymentInstruments(Long goId, Currency currency) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": payment-instruments [" + goId + "][" + currency + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient
                    .getPaymentInstruments(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), goId, currency);

        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": payment-instruments Error [" + goId + "][" + currency + "] RC [" + e.getResponse()
                    .getStatus()
                    + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }
    
    @Override
    public byte[] generateStatement(AccountStatement accountStatement) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + ": generate-statement [" + accountStatement + "]");
            
            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);
            
            return paymentClient
                    .getStatement(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), accountStatement);
            
        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": generate-statement Error [" + accountStatement + "] RC [" + e.getResponse()
                    .getStatus()
                    + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }
        
        return null;
    }
    
    
    @Override
    public List<EETReceipt> findEETREceiptsByFilter(EETReceiptFilter filter) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + "eet-receipt findByFilter " + filter.toString());
    
            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);
    
            return paymentClient.findEETReceiptsByFilter(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), filter);
            
        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": eet-receipt findByFilter " + filter.toString() + " RC [" + e.getResponse()
                    .getStatus()
                    + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }
        return null;
    }
    
    @Override
    public List<EETReceipt> getEETReceiptByPaymentId(Long id) throws GPClientException {
        try {
            logger.debug(getClass().getSimpleName() + "eet-receipt findByPaymentId PaymentId=[" + id + "]");
        
            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);
        
            return paymentClient.getEETReceiptByPaymentId(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id);
        
        } catch (WebApplicationException e) {
            logger.fatal(getClass().getSimpleName() + ": eet-receipt findByPaymentId PaymentId=[" + id + "] RC [" + e.getResponse()
                    .getStatus()
                    + "] Ex: " + e.getResponse().getStatusInfo(), e);
            GPExceptionHandler.handleException(e);
        }
        return null;
    }
    
    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public AccessToken getAccessToken() {
        return accessToken;
    }
    
    @Override
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
