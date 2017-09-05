package cz.gopay.api.v3;

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

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    public PaymentInstrumentRoot getPaymentInstruments(@BeanParam AuthHeader authHeader, @PathParam("goid") Long goId,
            @PathParam("currency") Currency currency);
    
    @POST
    @Path("accounts/account-statement")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.APPLICATION_JSON)
    public byte[] getStatement(@BeanParam AuthHeader authHeader, AccountStatement accountStatement);
    
    
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
    
    
    @POST
    @Path("/supercash/coupon")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    SupercashCoupon createSupercashCoupon(@BeanParam AuthHeader authHeader, SupercashCouponRequest couponRequest);
    
    
    @POST
    @Path("/supercash/coupon/batch")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    SupercashBatchResult createSupercashCouponBatch(@BeanParam AuthHeader authHeader, SupercashBatchRequest batchRequest);
    
    @GET
    @Path("/batch/{batch_id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    SupercashBatchState getSupercashCouponBatchStatus(@BeanParam AuthHeader authHeader, @PathParam("batch_id") Long batchId);
    
    @GET
    @Path("/supercash/coupon/find")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    SupercashBatch getSupercashCouponBatch(@BeanParam AuthHeader authHeader, @QueryParam("go_id") Long goId,
            @QueryParam("batch_request_id") Long batchId);
    
    @GET
    @Path("/supercash/coupon/find")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    SupercashBatch findSupercashCoupons(@BeanParam AuthHeader authHeader, @QueryParam("go_id") Long goId,
            @QueryParam("payment_session_id_list") String paymentSessionIds);
    
    @GET
    @Path("/supercash/coupon/{coupon_id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    SupercashPayment getSupercashCoupon(@BeanParam AuthHeader authHeader, @PathParam("coupon_id") Long couponId);
    
}
