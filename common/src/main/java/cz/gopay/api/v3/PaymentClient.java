package cz.gopay.api.v3;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cz.gopay.api.v3.model.access.AuthHeader;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.MediaType;

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
    public Payment createPayment(@BeanParam AuthHeader authHeader,
            BasePayment createPayment);

    @POST
    @Path("/payments/payment/{id}/refund")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public PaymentResult refundPayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id,
            @FormParam(value = "amount") Long amount);

    @POST
    @Path("/payments/payment/{id}/create-recurrence")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Payment createRecurrentPayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id,
            NextPayment createPayment);

    @POST
    @Path("/payments/payment/{id}/void-recurrence")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public PaymentResult voidRecurrence(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);

    @POST
    @Path("/payments/payment/{id}/capture")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public PaymentResult capturePayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);

    @POST
    @Path("/payments/payment/{id}/void-authorization")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public PaymentResult voidAuthorization(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);

    @GET
    @Path("/payments/payment/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Payment getPayment(@BeanParam AuthHeader authHeader,
            @PathParam("id") Long id);

}
