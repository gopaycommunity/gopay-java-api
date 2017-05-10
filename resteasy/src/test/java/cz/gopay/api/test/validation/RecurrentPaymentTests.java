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
import cz.gopay.api.v3.model.payment.support.Recurrence;
import cz.gopay.api.v3.model.payment.support.RecurrenceCycle;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

public class RecurrentPaymentTests {
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
	
	@Test
	public void testCreateRecurrentPayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment baseRecurrentPayment = createBasePayment();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);								// without - 409 Conflict, AssertionFailedError (INVALID)
		calendar.set(Calendar.MONTH, 4);								// without - 409 Conflict, AssertionFailedError (INVALID)
		calendar.set(Calendar.DAY_OF_MONTH, 1);							// without - OK, automaticky doplni podle dne vyvolani
		Recurrence recurrence = Recurrence.build(calendar.getTime())
				.withTimeInterval(RecurrenceCycle.WEEK, 1)				// without - 409 Conflict, AssertionFailedError (MANDATORY)
				.inState(Recurrence.RecurrenceState.STARTED);			// without - OK, automaticky doplni recurrenceState=REQUESTED
		
		baseRecurrentPayment.setRecurrence(recurrence);
		
		System.out.println("Base recurrent payment created: " + baseRecurrentPayment);
		
		Payment recurrentPayment = CommonMethodTests.createPaymentObject(connector, baseRecurrentPayment);
		
		System.out.println("Payment: " + recurrentPayment);
		System.out.println("Payment ID: " + recurrentPayment.getId());
		System.out.println("Payment gwUrl: " + recurrentPayment.getGwUrl());
		System.out.println("Recurrence: \n" + recurrentPayment.getRecurrence());
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testNoPeriod() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment baseRecurrentPayment = createBasePayment();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Recurrence recurrence = Recurrence.build(calendar.getTime())
				.inState(Recurrence.RecurrenceState.STARTED);
		
		baseRecurrentPayment.setRecurrence(recurrence);
		
		System.out.println("Base recurrent payment created: " + baseRecurrentPayment);
		
		Payment recurrentPayment = CommonMethodTests.createPaymentObject(connector, baseRecurrentPayment);
		
		System.out.println("Payment: " + recurrentPayment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testWrongDate() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment baseRecurrentPayment = createBasePayment();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2015);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Recurrence recurrence = Recurrence.build(calendar.getTime())
				.withTimeInterval(RecurrenceCycle.WEEK, 1)
				.inState(Recurrence.RecurrenceState.STARTED);
		
		baseRecurrentPayment.setRecurrence(recurrence);
		
		System.out.println("Base recurrent payment created: " + baseRecurrentPayment);
		
		Payment recurrentPayment = CommonMethodTests.createPaymentObject(connector, baseRecurrentPayment);
		
		System.out.println("Payment: " + recurrentPayment);
	}
	
	@Test
	public void testVoidRecurrence(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult voidRecurrency = null;
		
		try {
			voidRecurrency = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.voidRecurrency(3048381973L);		// paid recurrent payment ID
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment result recurrency: " + voidRecurrency);
		System.out.println("Recurrent payment: " + CommonMethodTests.getPaymentStatus(voidRecurrency.getId()));
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testVoidRecurrenceWrongID(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult voidRecurrency = null;
		
		try {
			voidRecurrency = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.voidRecurrency(3002000L);						// wrong ID
//					.voidRecurrency(3048376474L);						// recurrent payment recurrenceState=requested
//					.voidRecurrency(3048376552L);						// non recurrent payment
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment result recurrency: " + voidRecurrency);
	}
	
}
