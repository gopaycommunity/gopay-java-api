/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gopay.api.v3;

import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;

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

    PaymentResult voidAuthorization(Long id) throws GPClientException;

    Payment paymentStatus(Long id) throws GPClientException;

    String getApiUrl();

    AccessToken getAccessToken();
    
    void setAccessToken(AccessToken accessToken);
}
