package cz.gopay.api.v3.impl.apacheclient;

import cz.gopay.api.v3.PaymentClient;
import cz.gopay.api.v3.model.access.AuthHeader;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.CapturePayment;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.RefundPayment;
import cz.gopay.api.v3.model.payment.support.AccountStatement;
import cz.gopay.api.v3.model.payment.support.PaymentInstrumentRoot;
import cz.gopay.api.v3.model.supercash.SupercashBatch;
import cz.gopay.api.v3.model.supercash.SupercashBatchRequest;
import cz.gopay.api.v3.model.supercash.SupercashBatchResult;
import cz.gopay.api.v3.model.supercash.SupercashBatchState;
import cz.gopay.api.v3.model.supercash.SupercashCoupon;
import cz.gopay.api.v3.model.supercash.SupercashCouponRequest;
import cz.gopay.api.v3.model.supercash.SupercashPayment;

import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Frantisek Sichinger
 */
public class HttpClientPaymentClientImpl extends AbstractImpl implements PaymentClient {

    protected HttpClientPaymentClientImpl(String apiUrl) {
        super(apiUrl);
        logger = Logger.getLogger(HttpClientAuthClientImpl.class);
    }

    @Override
    public Payment createPayment(AuthHeader authHeader, BasePayment createPayment) {
        Response response = null;

        try {
            response = Request.Post(apiUrl + "/payments/payment")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.
                            getAuhorization())
                    .bodyString(marshall(createPayment), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }

        return unMarshall(response, Payment.class);
    }

    @Override
    public PaymentResult refundPayment(AuthHeader authHeader, Long id, Long amount) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/refund")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString("amount=" + amount, ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        return unMarshall(response, PaymentResult.class);
    }
    
    @Override
    public PaymentResult refundPayment(@BeanParam AuthHeader authHeader, Long id, RefundPayment refundPayment) {
        Response response = null;
    
        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/refund")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString(marshall(refundPayment), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        return unMarshall(response, PaymentResult.class);
    }
    
    @Override
    public Payment createRecurrentPayment(AuthHeader authHeader, Long id, NextPayment createPayment) {
        Response response = null;
        
        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/create-recurrence")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString(marshall(createPayment), ContentType.TEXT_XML)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }

        return unMarshall(response, Payment.class);
    }

    @Override
    public PaymentResult voidRecurrence(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/void-recurrence")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return unMarshall(response, PaymentResult.class);
    }

    @Override
    public PaymentResult capturePayment(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/capture")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }

        return unMarshall(response, PaymentResult.class);
    }
    
    @Override
    public PaymentResult capturePayment(AuthHeader authHeader, Long id, CapturePayment capturePayment) {
        Response response = null;
    
        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/capture")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString(marshall(capturePayment), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
    
        return unMarshall(response, PaymentResult.class);
    }
    
    @Override
    public PaymentResult voidAuthorization(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/void-authorization")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }

        return unMarshall(response, PaymentResult.class);

    }

    @Override
    public Payment getPayment(AuthHeader authHeader, Long id) {
        Response response = null;
        try {
            response = Request.Get(apiUrl + "/payments/payment/" + id)
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }

        return unMarshall(response, Payment.class);
    }
    
    @Override
    public PaymentInstrumentRoot getPaymentInstruments(AuthHeader authHeader, Long goId, Currency currency) {
        Response response = null;
    
        try {
            response = Request.Get(apiUrl + "/eshops/eshop/" + goId + "/payment-instruments/" + currency)
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
    
        return unMarshall(response, PaymentInstrumentRoot.class);
    }
    
    @Override
    public List<EETReceipt> findEETReceiptsByFilter(@BeanParam AuthHeader authHeader, EETReceiptFilter filter) {
        Response response = null;
        
        try {
            response = Request.Post(apiUrl + "/eet-receipts")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString(marshall(filter), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        
        return unMarshallComplexResponse(response, new TypeReference<List<EETReceipt>>() {});
    }
    
    @Override
    public List<EETReceipt> getEETReceiptByPaymentId(@BeanParam AuthHeader authHeader, Long id) {
        Response response = null;
        
        try {
            response = Request.Get(apiUrl + "/payments/payment/" + id + "/eet-receipts")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        
        return unMarshallComplexResponse(response, new TypeReference<List<EETReceipt>>() {});
    }
    
    @Override
    public byte[] getStatement(AuthHeader authHeader, AccountStatement accountStatement) {
        Response response = null;
        
        try {
            response = Request.Post(apiUrl + "/accounts/account-statement")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString(marshall(accountStatement), ContentType.APPLICATION_JSON)
                    .execute();
            HttpResponse httpresponse = response.returnResponse();
            return EntityUtils.toByteArray(httpresponse.getEntity());
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
    }
    
    @Override
    public SupercashCoupon createSupercashCoupon(AuthHeader authHeader, SupercashCouponRequest couponRequest) {
        Response response = null;
    
        try {
            response = Request.Post(apiUrl + "/supercash/coupon")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.
                            getAuhorization())
                    .bodyString(marshall(couponRequest), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
    
        return unMarshall(response, SupercashCoupon.class);
    }
    
    @Override
    public SupercashBatchResult createSupercashCouponBatch(AuthHeader authHeader, SupercashBatchRequest batchRequest) {
        Response response = null;
    
        try {
            response = Request.Post(apiUrl + "/supercash/coupon/batch")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.
                            getAuhorization())
                    .bodyString(marshall(batchRequest), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
    
        return unMarshall(response, SupercashBatchResult.class);
    }
    
    @Override
    public SupercashBatchState getSupercashCouponBatchStatus(AuthHeader authHeader, Long batchId) {
        Response response = null;
        try {
            response = Request.Get(apiUrl + "/batch/" + batchId)
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        
        return unMarshall(response, SupercashBatchState.class);
    }
    
    @Override
    public SupercashBatch getSupercashCouponBatch(AuthHeader authHeader, Long goId, Long batchId) {
        Response response = null;
        try {
            response = Request.Get(apiUrl + "/supercash/coupon/find?batch_request_id=" + batchId + "&go_id=" + goId)
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        
        return unMarshall(response, SupercashBatch.class);
    }
    
    public SupercashBatch findSupercashCoupons(AuthHeader authHeader, Long goId, Long...paymentSessionIds){
        String convertedOutput = "";
        for (Long paymentId : paymentSessionIds) {
            convertedOutput += paymentId + ",";
        }
        convertedOutput = convertedOutput.substring(0, convertedOutput.length() - 1);
        return findSupercashCoupons(authHeader, goId, convertedOutput);
    }
    
    @Override
    public SupercashBatch findSupercashCoupons(AuthHeader authHeader, Long goId, String paymentSessionIds) {
        Response response = null;
    
        try {
            response = Request.Get(apiUrl + "/supercash/coupon/find?payment_session_id_list=" + paymentSessionIds + "&go_id=" + goId)
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        
        return unMarshall(response, SupercashBatch.class);
    }
    
    @Override
    public SupercashPayment getSupercashCoupon(AuthHeader authHeader, Long couponId) {
        Response response = null;
        try {
            response = Request.Get(apiUrl + "/supercash/coupon/" + couponId)
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
        
        return unMarshall(response, SupercashPayment.class);
    }
    
}
