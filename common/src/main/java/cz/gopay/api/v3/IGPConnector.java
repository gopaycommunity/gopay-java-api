package cz.gopay.api.v3;

import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.CapturePayment;
import cz.gopay.api.v3.model.payment.Card;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.RefundPayment;
import cz.gopay.api.v3.model.payment.support.AccountStatement;
import cz.gopay.api.v3.model.payment.support.PaymentInstrumentRoot;

import java.util.List;

/**
 *
 * @author František Sichinger
 */
public interface IGPConnector {

    IGPConnector getAppToken(String clientId, String clientCredentials) throws GPClientException;

    IGPConnector getAppToken(String clientId, String clientCredentials,
            String scope) throws GPClientException;

    Payment createPayment(BasePayment payment) throws GPClientException;

    PaymentResult refundPayment(Long id, Long amount) throws GPClientException;

    Payment createRecurrentPayment(Long id, NextPayment nextPayment) throws GPClientException;

    PaymentResult voidRecurrency(Long id) throws GPClientException;

    PaymentResult capturePayment(Long id) throws GPClientException;
    
    PaymentResult capturePayment(Long id, CapturePayment capturePayment) throws GPClientException;

    PaymentResult voidAuthorization(Long id) throws GPClientException;

    Payment paymentStatus(Long id) throws GPClientException;
    
    PaymentResult refundPayment(Long id, RefundPayment refundPayment) throws GPClientException;

    List<EETReceipt> findEETREceiptsByFilter(EETReceiptFilter filter) throws GPClientException;
    
    PaymentInstrumentRoot getPaymentInstruments(Long goId, Currency currency) throws GPClientException;
    
    List<EETReceipt> getEETReceiptByPaymentId(Long id) throws GPClientException;
    
    byte[] generateStatement(AccountStatement accountStatement) throws GPClientException;
    
    String getApiUrl();

    AccessToken getAccessToken();
    
    void setAccessToken(AccessToken accessToken);
    
    Card getCardDetail(Long cardId) throws GPClientException;
    
    void deleteCard(Long cardId) throws GPClientException;
}
