package cz.gopay.api.test.validation;

import cz.gopay.api.test.payment.AbstractPaymentTests;
import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EET;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.BasePaymentBuilder;
import cz.gopay.api.v3.model.payment.Lang;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.NextPaymentBuilder;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentFactory;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.RefundPayment;
import cz.gopay.api.v3.model.payment.VatRate;
import cz.gopay.api.v3.model.payment.support.ItemType;
import cz.gopay.api.v3.model.payment.support.OrderItem;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PayerBuilder;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;
import cz.gopay.api.v3.model.payment.support.Recurrence;
import cz.gopay.api.v3.model.payment.support.RecurrenceCycle;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EetTests {
	
	private static final Logger logger = Logger.getLogger(AbstractPaymentTests.class);
	
	public static Payment createEETPaymentObject(IGPConnector connector, BasePayment basePayment) {
		Payment payment = null;
		
		try {
			payment = connector
					.getAppToken(TestUtils.CLIENT_ID_EET, TestUtils.CLIENT_SECRET_EET, OAuth.SCOPE_PAYMENT_CREATE)
					.createPayment(basePayment);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		return payment;
	}
	
	public static Payment getEETPaymentStatus(long id) {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		Payment payment = null;
		
		try {
			payment = connector
					.getAppToken(TestUtils.CLIENT_ID_EET, TestUtils.CLIENT_SECRET_EET, OAuth.SCOPE_PAYMENT_ALL)
					.paymentStatus(id);
			
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		Assert.assertNotNull(payment.getId());
		
		return payment;
	}
	
	private BasePayment createEETBasePayment() {
		BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
		
		String url = "https://www.eshop123.cz/";
		Payer payer = new PayerBuilder().withAllowedPaymentInstruments(
				Arrays.asList(PaymentInstrument.BANK_ACCOUNT, PaymentInstrument.PAYMENT_CARD))
				.addAllowedSwift("FIOBCZPP").addAllowedSwift("RZBCCZPP").build();
		BasePayment basePayment = builder.withCallback(url + "return", url + "notify")
				.order("EET1234", 139950L, Currency.CZK, "EET1234Description")
				.inLang(Lang.CS)
				.addAdditionalParameter("AdditionalKey", "AdditionalValue")
				.addItem("Pocitac Item1", 119990L, 1L, VatRate.RATE_4, ItemType.ITEM, "1234567890123",
						"https://www.eshop123.cz/pocitac")
				.addItem("Oprava Item2", 19960L, 1L, VatRate.RATE_3, ItemType.ITEM, "1234567890189",
						"https://www.eshop123.cz/pocitac/oprava")
				.toEshop(TestUtils.GOID_EET)
				.payer(payer)
				.build();
		
		System.out.println("Base payment created: " + basePayment);
		
		return basePayment;
	}
	
	@Test
	public void testCreateEETPayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createEETBasePayment();
		
		EET eet = new EET();
		eet.setCelkTrzba(139950L);
		eet.setZaklDan1(99165L);
		eet.setDan1(20825L);
		eet.setZaklDan2(17357L);
		eet.setDan2(2603L);
		eet.setMena(Currency.CZK);
		
		basePayment.setEet(eet);
		
		Payment payment = createEETPaymentObject(connector, basePayment);
		
		Assert.assertTrue("Payment ID is not greater than 0: ", payment.getId() > 0);
		System.out.println("EET Payment: " + payment);
		System.out.println("EET Payment gwUrl: " + payment.getGwUrl());
		System.out.println("EET Payment ID: " + payment.getId());
		System.out.println("EET code: " + payment.getEetCode());
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("EET Base Payment: " + basePayment);
		System.out.println("EET: " + basePayment.getEet());
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testNonCorrespondingParameters() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createEETBasePayment();
		
		basePayment.setOrderNumber(null);
		basePayment.setOrderDescription(null);
		
		List<OrderItem> wrongItems = new ArrayList<>();
		OrderItem item1 =
				OrderItem.of("Not!--Alphanumeric", -10200L, -1L, VatRate.RATE_1, ItemType.ITEM, "1234", "NotAnUrl");
		OrderItem item2 = OrderItem.of("!-=A", 0L, 0L, VatRate.RATE_2, ItemType.ITEM, "123456789012345", "!-=A");
		
		wrongItems.add(item1);
		wrongItems.add(item2);
		
		basePayment.setItems(wrongItems);
		
		EET eet = new EET();
		eet.setCelkTrzba(139950L);
		eet.setZaklDan1(99165L);
		eet.setDan1(20825L);
		eet.setZaklDan2(17357L);
		eet.setDan2(2603L);
		eet.setMena(Currency.EUR);
		
		basePayment.setEet(eet);
		
		Payment payment = createEETPaymentObject(connector, basePayment);
		
		Assert.assertTrue("Payment ID is not greater than 0: ", payment.getId() > 0);
		System.out.println("EET Payment: " + payment);
		System.out.println("EET Payment gwUrl: " + payment.getGwUrl());
		System.out.println("EET Payment ID: " + payment.getId());
		System.out.println("EET code: " + payment.getEetCode());
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("EET Base Payment: " + basePayment);
		System.out.println("EET: " + basePayment.getEet());
		System.out.println("Items: " + basePayment.getItems());
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testEETParameters() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createEETBasePayment();
		
		EET eet = new EET();
		eet.setCelkTrzba(-139950L);
		eet.setZaklDan1(0L);
		eet.setDan1(20825L);
		eet.setZaklDan2(17357L);
		eet.setDan2(-2603L);
		eet.setMena(Currency.EUR);
		
		basePayment.setEet(eet);
		
		Payment payment = createEETPaymentObject(connector, basePayment);
		
		Assert.assertTrue("Payment ID is not greater than 0: ", payment.getId() > 0);
		System.out.println("EET Payment: " + payment);
		System.out.println("EET Payment gwUrl: " + payment.getGwUrl());
		System.out.println("EET Payment ID: " + payment.getId());
		System.out.println("EET code: " + payment.getEetCode());
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("EET Base Payment: " + basePayment);
		System.out.println("EET: " + basePayment.getEet());
	}
	
	@Test
	public void testCreateRecurrentEETPayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment baseRecurrentEETPayment = createEETBasePayment();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 4);
		Recurrence recurrence = Recurrence.build(calendar.getTime())
				.withTimeInterval(RecurrenceCycle.MONTH, 3);
		
		baseRecurrentEETPayment.setRecurrence(recurrence);
		
		EET eet = new EET();
		eet.setCelkTrzba(139950L);
		eet.setZaklDan1(99165L);
		eet.setDan1(20825L);
		eet.setZaklDan2(17357L);
		eet.setDan2(2603L);
		eet.setMena(Currency.CZK);
		
		baseRecurrentEETPayment.setEet(eet);
		
		Payment payment = createEETPaymentObject(connector, baseRecurrentEETPayment);
		
		Assert.assertTrue("Payment ID is not greater than 0: ", payment.getId() > 0);
		System.out.println("EET Payment: " + payment);
		System.out.println("EET Payment gwUrl: " + payment.getGwUrl());
		System.out.println("EET Payment ID: " + payment.getId());
		System.out.println("EET recurrence: " + payment.getRecurrence());
		System.out.println("EET code: " + payment.getEetCode());
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("EET Base Payment: " + baseRecurrentEETPayment);
		System.out.println("EET: " + baseRecurrentEETPayment.getEet());
		
	}
	
	@Test
	public void testCreateOnDemandEETPayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment baseOnDemandEETPayment = createEETBasePayment();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 5);
		Recurrence recurrence = Recurrence.build(calendar.getTime())
				.onDemand();
		
		baseOnDemandEETPayment.setRecurrence(recurrence);
		
		EET eet = new EET();
		eet.setCelkTrzba(139950L);
		eet.setZaklDan1(99165L);
		eet.setDan1(20825L);
		eet.setZaklDan2(17357L);
		eet.setDan2(2603L);
		eet.setMena(Currency.CZK);
		
		baseOnDemandEETPayment.setEet(eet);
		
		Payment payment = createEETPaymentObject(connector, baseOnDemandEETPayment);
		
		Assert.assertTrue("Payment ID is not greater than 0: ", payment.getId() > 0);
		System.out.println("EET Payment: " + payment);
		System.out.println("EET Payment gwUrl: " + payment.getGwUrl());
		System.out.println("EET Payment ID: " + payment.getId());
		System.out.println("EET recurrence: " + payment.getRecurrence());
		System.out.println("EET code: " + payment.getEetCode());
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("EET Base Payment: " + baseOnDemandEETPayment);
		System.out.println("EET: " + baseOnDemandEETPayment.getEet());
	}
	
	@Test
	public void testNextOnDemandPayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		// Next payment
		Payment nextOnDemandEETPayment = null;
		NextPayment nextPayment = PaymentFactory.createNextPaymentBuilder()
				.order("OnDemand1234", 2000L, Currency.CZK, "OnDemand1234Description")
				.addItem("OnDemand Prodlouzena zaruka", 2000L, 1L, VatRate.RATE_4, ItemType.ITEM, "12345678901234",
						"https://www.eshop123.cz/pocitac/prodlouzena_zaruka")
				.build();
		
		EET eet = new EET();
		eet.setCelkTrzba(2000L);
		eet.setZaklDan1(1580L);
		eet.setDan1(420L);
		eet.setMena(Currency.CZK);
		
		nextPayment.setEet(eet);
		
		try {
			nextOnDemandEETPayment = connector
					.getAppToken(TestUtils.CLIENT_ID_EET, TestUtils.CLIENT_SECRET_EET, OAuth.SCOPE_PAYMENT_ALL)
					.createRecurrentPayment(3048415253L, nextPayment);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		System.out.println("Payment: " + nextOnDemandEETPayment);
		System.out.println("Next payment ID: " + nextOnDemandEETPayment.getId());
		System.out.println("Next payment gwUrl: " + nextOnDemandEETPayment.getGwUrl());
		System.out.println("Recurrence \n" + nextOnDemandEETPayment.getRecurrence());
		System.out.println("EET code: " + nextOnDemandEETPayment.getEetCode());
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("EET Next Payment: " + nextPayment);
		System.out.println("EET: " + nextPayment.getEet());
	}
	
	@Test
	public void testEETPaymentStatus() {
		Payment EETpayment = getEETPaymentStatus(3048433570L);
		System.out.println("EET Payment: " + EETpayment);
		System.out.println("EET Payment State: " + EETpayment.getState());
		System.out.println("EET Payment gwUrl: " + EETpayment.getGwUrl());
		System.out.println("EET recurrence: " + EETpayment.getRecurrence());
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("EET code: " + EETpayment.getEetCode());
	}
	
	@Test
	public void testEETPaymentRefund() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult refundEETPayment = null;
		
		RefundPayment refundObject = new RefundPayment();
		refundObject.setAmount(139950L);
		
		List<OrderItem> refundItems = new ArrayList<OrderItem>();
		OrderItem item1 = OrderItem.of("Pocitac Item1", 119990L, 1L, VatRate.RATE_4, ItemType.ITEM, "1234567890123",
				"https://www.eshop123.cz/pocitac");
		OrderItem item2 = OrderItem.of("Oprava Item2", 19960L, 1L, VatRate.RATE_3, ItemType.ITEM, "1234567890189",
				"https://www.eshop123.cz/pocitac/oprava");
		refundItems.add(item1);
		refundItems.add(item2);
		refundObject.setItems(refundItems);
		
		EET eet = new EET();
		eet.setCelkTrzba(139950L);
		eet.setZaklDan1(99165L);
		eet.setDan1(20825L);
		eet.setZaklDan2(17357L);
		eet.setDan2(2603L);
		eet.setMena(Currency.CZK);
		refundObject.setEet(eet);
		
		try {
			refundEETPayment = connector
					.getAppToken(TestUtils.CLIENT_ID_EET, TestUtils.CLIENT_SECRET_EET, OAuth.SCOPE_PAYMENT_ALL)
					.refundPayment(3048420713L, refundObject);
		} catch (GPClientException ex) {
			TestUtils.handleException(ex, logger);
		}
		
		System.out.println("Refunded payment result: " + refundEETPayment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testEETRefundParameters() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult refundEETPayment = null;
		
		RefundPayment refundObject = new RefundPayment();
		refundObject.setAmount(139950L);
		
		List<OrderItem> refundItems = new ArrayList<OrderItem>();
		refundObject.setItems(refundItems);
		
		EET eet = new EET();
		eet.setCelkTrzba(-139950L);
		eet.setZaklDan1(0L);
		eet.setDan1(20825L);
		eet.setZaklDan2(17357L);
		eet.setDan2(-2603L);
		eet.setMena(Currency.EUR);
		refundObject.setEet(eet);
		
		
		try {
			refundEETPayment = connector
					.getAppToken(TestUtils.CLIENT_ID_EET, TestUtils.CLIENT_SECRET_EET, OAuth.SCOPE_PAYMENT_ALL)
					.refundPayment(3048422351L, refundObject);
		} catch (GPClientException ex) {
			TestUtils.handleException(ex, logger);
		}
		
		System.out.println("Refunded payment result: " + refundEETPayment);
	}
	
	@Test
	public void testEETREceiptFindByFilter() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		Calendar calendarFrom = Calendar.getInstance();
		calendarFrom.set(Calendar.DAY_OF_MONTH, 2);
		calendarFrom.set(Calendar.MONTH, 2);
		calendarFrom.set(Calendar.YEAR, 2017);
		
		Calendar calendarTo = Calendar.getInstance();
		calendarTo.set(Calendar.DAY_OF_MONTH, 2);
		calendarTo.set(Calendar.MONTH, 3);
		calendarTo.set(Calendar.YEAR, 2017);
		
		EETReceiptFilter filter = EETReceiptFilter.create(11, calendarFrom.getTime(), calendarTo.getTime());
		
		List<EETReceipt> receipts = null;
		
		try {
			receipts = connector
					.getAppToken(TestUtils.CLIENT_ID_EET, TestUtils.CLIENT_SECRET_EET, OAuth.SCOPE_PAYMENT_ALL)
					.findEETREceiptsByFilter(filter);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		for (EETReceipt currReceipt : receipts) {
			System.out.println(currReceipt);
		}
		System.out.println(receipts.size());
	}
	
	@Test
	public void testEETReceiptFindByPayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		List<EETReceipt> receipts = null;
		
		try {
			receipts = connector
					.getAppToken(TestUtils.CLIENT_ID_EET, TestUtils.CLIENT_SECRET_EET, OAuth.SCOPE_PAYMENT_ALL)
					.getEETReceiptByPaymentId(3048429735L);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		for (EETReceipt currReceipt : receipts) {
			System.out.println(currReceipt);
		}
		System.out.println(receipts.size());
	}
	
	
}
