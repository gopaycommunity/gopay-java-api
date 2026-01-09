package cz.gopay.api.v3;

import cz.gopay.api.v3.model.access.AuthHeader;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.CapturePayment;
import cz.gopay.api.v3.model.payment.Card;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.Refund;
import cz.gopay.api.v3.model.payment.RefundPayment;
import cz.gopay.api.v3.model.payment.support.AccountStatement;
import cz.gopay.api.v3.model.payment.support.PaymentInstrumentRoot;

import java.util.List;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public interface PaymentClient {

    @POST
    @Path("/payments/payment")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    Payment createPayment(@BeanParam AuthHeader authHeader,
            BasePayment createPayment);

    @POST
    @Path("/payments/payment/{id}/refund")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    PaymentResult refundPayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id,
            @FormParam(value = "amount") Long amount);
    
    
    @POST
    @Path("/payments/payment/{id}/refund")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    PaymentResult refundPayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id,
            RefundPayment refundPayment);

    @POST
    @Path("/payments/payment/{id}/create-recurrence")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Payment createRecurrentPayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id,
            NextPayment createPayment);

    @POST
    @Path("/payments/payment/{id}/void-recurrence")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    PaymentResult voidRecurrence(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);

    @POST
    @Path("/payments/payment/{id}/capture")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    PaymentResult capturePayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);
    
    @POST
    @Path("/payments/payment/{id}/capture")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    PaymentResult capturePayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id,
            CapturePayment capturePayment);
    
    @POST
    @Path("/payments/payment/{id}/void-authorization")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    PaymentResult voidAuthorization(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);

    @GET
    @Path("/payments/payment/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Payment getPayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);
    
    @GET
    @Path("eshops/eshop/{goid}/payment-instruments/{currency}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    PaymentInstrumentRoot getPaymentInstruments(@BeanParam AuthHeader authHeader, @PathParam("goid") Long goId,
            @PathParam("currency") Currency currency);
    
    @POST
    @Path("accounts/account-statement")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.APPLICATION_JSON)
    byte[] getStatement(@BeanParam AuthHeader authHeader, AccountStatement accountStatement);
    
    
    @POST
    @Path("/eet-receipts")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    List<EETReceipt> findEETReceiptsByFilter(@BeanParam AuthHeader authHeader, EETReceiptFilter filter);
    
    
    @GET
    @Path("/payments/payment/{id}/eet-receipts")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    List<EETReceipt> getEETReceiptByPaymentId(@BeanParam AuthHeader authHeader, @PathParam("id") Long id);
    
    @GET
    @Path("/payments/cards/{card_id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    Card getCardDetail(@BeanParam AuthHeader authHeader, @PathParam("card_id") Long cardId);
    
    @DELETE
    @Path("payments/cards/{card_id}")
    void deleteCard(@BeanParam AuthHeader authHeader, @PathParam("card_id") Long cardId);
    
    @GET
    @Path("payments/payment/{id}/refunds")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    List<Refund> getHistoryOfRefunds(@BeanParam AuthHeader authHeader, @PathParam("id") Long id);
}
