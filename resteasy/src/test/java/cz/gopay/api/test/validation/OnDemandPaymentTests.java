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
import cz.gopay.api.v3.model.payment.NextPaymentBuilder;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentFactory;
import cz.gopay.api.v3.model.payment.VatRate;
import cz.gopay.api.v3.model.payment.support.ItemType;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PayerBuilder;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;
import cz.gopay.api.v3.model.payment.support.Recurrence;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

public class OnDemandPaymentTests {
	
	private static final Logger logger = Logger.getLogger(AbstractPaymentTests.class);
	
	private BasePayment createBasePayment() {
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
				.build();
		
		System.out.println("Base payment created: " + basePayment);
		
		return basePayment;
	}
	
	/**
	 * Test requires
	 */
	//@Test
	public void testOnDemandPayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment baseOnDemandPayment = createBasePayment();
		System.out.println("Base on demand payment created: " + baseOnDemandPayment);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,	2017);
		calendar.set(Calendar.MONTH, 4);
		Recurrence recurrence = Recurrence.build(calendar.getTime())
				.onDemand();
		
		baseOnDemandPayment.setRecurrence(recurrence);
		
		Payment onDemandPayment = CommonMethodTests.createPaymentObject(connector, baseOnDemandPayment);
		
		System.out.println("Payment ID: " + onDemandPayment.getId());
		System.out.println("Payment gwUrl: " + onDemandPayment.getGwUrl());
		System.out.println("Recurrence: \n" + onDemandPayment.getRecurrence());
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testOnDemandWrongDate() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment baseOnDemandPayment = createBasePayment();
		System.out.println("Base recurrent payment created: " + baseOnDemandPayment);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,	2015);
		calendar.set(Calendar.MONTH, 4);
		Recurrence recurrence = Recurrence.build(calendar.getTime())
				.onDemand();
		
		baseOnDemandPayment.setRecurrence(recurrence);
		
		Payment onDemandPayment = CommonMethodTests.createPaymentObject(connector, baseOnDemandPayment);
		
		System.out.println("Payment: " + onDemandPayment);
	}
	
	@Test
	public void testNextOnDemandPayment(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		// Next payment
		Payment nextOnDemandPayment = null;
		NextPaymentBuilder nextBuilder = PaymentFactory.createNextPaymentBuilder()
				.order("OnDemand1234", 2000L, Currency.CZK, "OnDemand1234Description")		// without - 409 Conflict
				.addItem("OnDemand First Item", 2000L, 2L)									// without - OK, nalez
				;
		
		try {
			nextOnDemandPayment = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.createRecurrentPayment(3048381986L, nextBuilder.build());
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment: " + nextOnDemandPayment);
		System.out.println("Next payment ID: " + nextOnDemandPayment.getId());
		System.out.println("Next payment gwUrl: " + nextOnDemandPayment.getGwUrl());
		System.out.println("Recurrence \n" + nextOnDemandPayment.getRecurrence());
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testNextOnDemandWrongId(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		// Next payment
		Payment nextOnDemandPayment = null;
		NextPaymentBuilder nextBuilder = PaymentFactory.createNextPaymentBuilder()
				.order("OnDemand1234", 2000L, Currency.CZK, "OnDemand1234Description")
				.addItem("OnDemand First Item", 2000L, 2L)
				;
		
		try {
			nextOnDemandPayment = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.createRecurrentPayment(3048376357L, nextBuilder.build());
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment: " + nextOnDemandPayment);
	}
}
