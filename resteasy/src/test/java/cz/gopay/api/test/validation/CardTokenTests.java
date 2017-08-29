package cz.gopay.api.test.validation;

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
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PayerBuilder;
import cz.gopay.api.v3.model.payment.support.PayerContact;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;

public class CardTokenTests {
	
	private String testing_token = "VUHweq2TUuQpgU6UaD4c+123xzUwTBXiZK7jHhW7rhSbUb07XcG69Q0cwTxTYvBG3qyym3sJ5zphQS4vL0kEHvvinxXYMqkZtx4rBA9mtZj9JSpy4cIHkXnH3gR+i6CoQ4M+zI2EXGJ+TQ==";
	
	private BasePayment createBaseCardTokenPayment() {
		BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
		
		PayerContact contact = new PayerContact();
		contact.setFirstName("Jarda");
		contact.setLastName("Sokol");
		contact.setEmail("test-sokol24@test.cz");
		
		String url = "https://www.eshop123.cz/";
		Payer payer = new PayerBuilder()
				.withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.PAYMENT_CARD))
				.withDefaultPaymentInstrument(PaymentInstrument.PAYMENT_CARD)
				.withContactData(contact)
				.withAllowedCardToken(testing_token)			// card-token
				.build();
		
		BasePayment basePayment = builder.withCallback(url + "return", url + "notify")
				.order("1234", 1000L, Currency.CZK, "1234Description")
				.inLang(Lang.CS)
				.addAdditionalParameter("AdditionalKey", "AdditionalValue")
				.addItem("First Item", 1000L, 1L, VatRate.RATE_4, ItemType.ITEM, "4567891234", "url")
				.toEshop(TestUtils.GOID)
				.payer(payer)
				.build();
		
		return basePayment;
	}
	
	/*
	* All fields on gateway are pre-filled with using card-token.
	* */
	@Test
	public void testPaymentWithCardToken(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		BasePayment cardTokenBasePayment = createBaseCardTokenPayment();
		Payment cardTokenPayment = CommonMethodTests.createPaymentObject(connector, cardTokenBasePayment);
		
		System.out.println("Payment: " + cardTokenPayment);
		System.out.println("Payment gwUrl: " + cardTokenPayment.getGwUrl());
		System.out.println("Payment ID: " + cardTokenPayment.getId());
	}
	
	/*
	* After payment completion the used card-token can be found in created payment.
	* */
	@Test
	public void testCardTokenPaymentStatus(){
		Payment cardTokenPayment = CommonMethodTests.getPaymentStatus(3052190128L);
		
		Assert.assertEquals(testing_token, cardTokenPayment.getPayer().getPaymentCard().getCardToken());
		
		System.out.println("Payment: " + cardTokenPayment);
		System.out.println("Payment State: " + cardTokenPayment.getState());
		System.out.println("Payment gwUrl: " + cardTokenPayment.getGwUrl());
		System.out.println("Payment substate: " + cardTokenPayment.getSubState());
		System.out.println("PayerCard - card token: " + cardTokenPayment.getPayer().getPaymentCard().getCardToken());
		System.out.println("Payer 3DS Result: " + cardTokenPayment.getPayer().getPaymentCard().getThreeDResult());
	}
}
