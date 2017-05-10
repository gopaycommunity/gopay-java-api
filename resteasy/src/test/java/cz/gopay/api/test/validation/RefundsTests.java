package cz.gopay.api.test.validation;

import cz.gopay.api.test.payment.AbstractPaymentTests;
import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.payment.PaymentResult;

import org.apache.log4j.Logger;
import org.junit.Test;

public class RefundsTests {
	private static final Logger logger = Logger.getLogger(AbstractPaymentTests.class);
	
	private PaymentResult refundPayment(long id, long amount){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult refundPayment= null;
		
		try {
			refundPayment = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.refundPayment(id, amount);
		} catch (GPClientException ex) {
			TestUtils.handleException(ex, logger);
		}
		
		return refundPayment;
	}
	
	@Test
	public void testPaymentRefund(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentResult refundPayment= null;
		
		try {
			refundPayment = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.refundPayment(3048416345L, 1000L);					// zaplacena platba - OK pro zaplacenou platbu a spravny amount
		} catch (GPClientException ex) {
			TestUtils.handleException(ex, logger);
		}
		
		System.out.println("Refunded payment result: " + refundPayment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testRefundWrongId(){
		
		// spatne ID platby - 500 Internal Server Error, AssertionFailedError (null)
		PaymentResult refundPayment= refundPayment(3000000L, 1000L);
		
		// vytvorena a nezaplacena - 409 Conflict, AssertionFailedError (PAYMENT_REFUND_NOT_SUPPORTED)
//		PaymentResult refundPayment= refundPayment(3048112730L, 1000L);
		
		System.out.println("Refunded payment result: " + refundPayment);
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testRefundLargerAmount(){
		
		// prilis velka castka - 409 Conflict, AssertionFailedError (PAYMENT_REFUND_WRONG_AMOUNT)
		PaymentResult refundPayment = refundPayment(3048379295L, 10000L);
		
		System.out.println("Refunded payment result: " + refundPayment);
	}
	
	@Test
	public void testRefundPartialAmount(){
		
		// pouze cast castky - OK refunduje se pouze cast z celkove castky, zmena stavu platby na PARTIALLY_REFUNDED
		PaymentResult refundPayment = refundPayment(3048379295L, 100L);
		
		// zbyla cast castky - OK refunduje se zbytek, zmena stavu platby na REFUNDED
//		PaymentResult refundPayment = refundPayment(3048113029L, 900L);
		
		System.out.println("Refunded payment result: " + refundPayment);
	}
}
