package cz.gopay.api.v3.impl.apacheclient;

import cz.gopay.api.v3.PaymentClient;
import cz.gopay.api.v3.model.access.AuthHeader;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;

/**
 *
 * @author Frantisek Sichinger
 */
public class HttpClientPaymentclientImpl extends AbstractImpl implements PaymentClient {

    protected HttpClientPaymentclientImpl(String apiUrl) {
        super(apiUrl);
        logger = Logger.getLogger(HttpClientAuthClientImpl.class);
    }

    @Override
    public Payment createPayment(AuthHeader authHeader, BasePayment createPayment) {
        Response response = null;

        try {
            response = Request.Post(apiUrl + "/payments/payment").
                    addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON).
                    addHeader(AUTHORIZATION, authHeader.
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
    public Payment createRecurrentPayment(AuthHeader authHeader, Long id, NextPayment createPayment) {
        Response response = null;
        
        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/create-recurrence")
                    .addHeader(ACCEPT, MediaType.APPLICATION_JSON).
                    addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString(marshall(createPayment), ContentType.TEXT_XML).
                    execute();
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
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                    execute();
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
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                    execute();
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
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                    execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }

        return unMarshall(response, PaymentResult.class);

    }

    @Override
    public Payment getPayment(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.Get(apiUrl + "/payments/payment/" + id).
                    addHeader(ACCEPT, MediaType.APPLICATION_JSON)
                    .addHeader(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                    execute();
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }

        return unMarshall(response, Payment.class);
    }

}
