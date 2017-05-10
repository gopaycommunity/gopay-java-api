package cz.gopay.api.test.validation;

import cz.gopay.api.test.payment.AbstractPaymentTests;
import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.BasePaymentBuilder;
import cz.gopay.api.v3.model.payment.Lang;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentFactory;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.VatRate;
import cz.gopay.api.v3.model.payment.support.ItemType;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PayerBuilder;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;

public class PreAuthorizationTests {
	private static final Logger logger = Logger.getLogger(AbstractPaymentTests.class);
	
	private BasePayment createBasePreAuthorizedPayment() {
		BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
		
		String url = "https://www.eshop123.cz/";
		Payer payer = new PayerBuilder().withAllowedPaymentInstruments(
				Arrays.asList(PaymentInstrument.BANK_ACCOUNT, PaymentInstrument.PAYMENT_CARD))
				.addAllowedSwift("FIOBCZPP").addAllowedSwift("RZBCCZPP").build();
		BasePayment basePayment = builder.withCallback(url + "return", url + "notify")
				.order("1234", 1000L, Currency.CZK, "1234Description")
				.inLang(Lang.CS)
				.addAdditionalParameter("AdditionalKey", "AdiitionalValue")
				.addItem("First Item", 1000L, 1L, VatRate.RATE_4, ItemType.ITEM, "4567891234", "url")
				.toEshop(TestUtils.GOID)
				.payer(payer)
				.preauthorize()
				.build();
		
		System.out.println("Base preauthorized payment created: " + basePayment);
		
		return basePayment;
	}
	
	@Test
	public void testPreAuthorizedPayment(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment preAuthorizeBasePayment = createBasePreAuthorizedPayment();
		
		Payment preAuthorizedPayment = CommonMethodTests.createPaymentObject(connector, preAuthorizeBasePayment);
		
		System.out.println("Payment: " + preAuthorizedPayment);
		System.out.println("Payment gwUrl: " + preAuthorizedPayment.getGwUrl());
		System.out.println("Payment ID: " + preAuthorizedPayment.getId());
		System.out.println("Payment authorization: " + preAuthorizedPayment.getPreAuthorization());
	}
	
	@Test
	public void testVoidAuthorization(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult voidAuthorization = null;
		
		try {
			voidAuthorization = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.voidAuthorization(3048377267L);		// preauthorized payment ID
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment result of void authorization: " + voidAuthorization);
		System.out.println("Void preauthorized payment: " + CommonMethodTests.getPaymentStatus(voidAuthorization.getId()));
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testVoidAuthorizationWrongId(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult voidAuthorization = null;
		
		try {
			voidAuthorization = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.voidAuthorization(3002000L)	;		// wrong ID
//					.voidAuthorization(3048372769L);		// non authorized payment ID
//					.voidAuthorization(3048373042L);		// preauthorized state=requested
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment result of void authorization: " + voidAuthorization);
	}
	
	@Test
	public void testCapturePayment(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult captureAuthorization = null;
		
		try {
			captureAuthorization = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.capturePayment(3048268093L)	;		// preauthorized payment ID
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment result of capture authorization " + captureAuthorization);
		System.out.println("Capture preauthorized payment: " + CommonMethodTests.getPaymentStatus(captureAuthorization.getId()));
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testCapturePaymentWrongId(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult captureAuthorization = null;
		
		try {
			captureAuthorization = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.capturePayment(3002000L);			// wrong ID
//					.capturePayment(3048372769L);			// non authorized payment ID
//					.capturePayment(3048373042L);			// preauthorized state=requested
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment result of capture authorization " + captureAuthorization);
	}
	
	
	
}
