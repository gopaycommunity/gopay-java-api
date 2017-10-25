package cz.gopay.api.test.validation;

import cz.gopay.api.test.payment.AbstractPaymentTests;
import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.BasePaymentBuilder;
import cz.gopay.api.v3.model.payment.Lang;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentFactory;
import cz.gopay.api.v3.model.payment.VatRate;
import cz.gopay.api.v3.model.payment.support.ItemType;
import cz.gopay.api.v3.model.payment.support.OrderItem;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PayerBuilder;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;
import cz.gopay.api.v3.model.payment.support.Target;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatePaymentTests {
	private static final Logger logger = Logger.getLogger(AbstractPaymentTests.class);
	
	private BasePayment createBasePayment() {
		BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
		
		String url = "https://www.eshop123.cz/";
		Payer payer = new PayerBuilder().withAllowedPaymentInstruments(
				Arrays.asList(PaymentInstrument.BANK_ACCOUNT, PaymentInstrument.PAYMENT_CARD))
				.addAllowedSwift("GIBACZPX").addAllowedSwift("RZBCCZPP").build();
		BasePayment basePayment = builder.withCallback(url + "return", url + "notify")
				.order("1234", 1300L, Currency.CZK, "1234Description")
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
	public void testCreatePayment() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		Assert.assertTrue("Payment ID is not greater than 0: ", payment.getId()>0);
		System.out.println("Payment: " + payment);
		System.out.println("Payment gwUrl: " + payment.getGwUrl());
		System.out.println("Payment ID: " + payment.getId());
	}
	
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testWrongGoId(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
		Target wrongTarget1 = Target.createEShop(55555L);
		basePayment.setTarget(wrongTarget1);
		
		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		System.out.println("Payment: " + payment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testTargetWithWallet(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
		Target badTarget2 = Target.createEWallet("NotAnEmail");
		basePayment.setTarget(badTarget2);
		
		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		System.out.println("Payment: " + payment);
	}
	
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testWrongAmount(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
 		basePayment.setAmount(0L);
		System.out.println("Amount of base payment: " + basePayment.getAmount());
		
		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		System.out.println("Payment: " + payment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testWrongOrderNumber(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
		basePayment.setOrderNumber("10--!J%hu");
		System.out.println("Non-alphanumeric order number: " + basePayment.getOrderNumber());
		
		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		System.out.println("Payment: " + payment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testEmptyItems(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
		List<OrderItem> emptyItems = new ArrayList<>();
		basePayment.setItems(emptyItems);
		System.out.println("Items: " + basePayment.getItems());

		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		System.out.println("Payment: " + payment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testNonCorrespondingItem() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
		List<OrderItem> differentAmount = new ArrayList<>();
		differentAmount
				.add(OrderItem.of("Different Item", 2000L, 2L, VatRate.RATE_4, ItemType.ITEM, "4567891234", "url"));
		basePayment.setItems(differentAmount);
		System.out.println("Items: " + basePayment.getItems());
		
		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		System.out.println("Payment: " + payment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testWrongCallbackUrl() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment basePayment = createBasePayment();
		
		basePayment.getCallback().setReturnUrl("ThisIsNotAnUrl");
		System.out.println("Bad return URL: " + basePayment.getCallback().getReturnUrl());
		
		Payment payment = CommonMethodTests.createPaymentObject(connector, basePayment);
		
		System.out.println("Payment: " + payment);
	}
}
