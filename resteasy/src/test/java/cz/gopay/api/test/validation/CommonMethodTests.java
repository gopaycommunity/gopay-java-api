package cz.gopay.api.test.validation;

import cz.gopay.api.test.payment.AbstractPaymentTests;
import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.common.StatementGeneratingFormat;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.support.AccountStatement;
import cz.gopay.api.v3.model.payment.support.PaymentInstrumentRoot;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Calendar;

public class CommonMethodTests {
	private static final Logger logger = Logger.getLogger(AbstractPaymentTests.class);
	
	public static Payment createPaymentObject(IGPConnector connector, BasePayment basePayment){
		Payment payment = null;
		
		try {
			payment = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_CREATE)
					.createPayment(basePayment);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		return payment;
	}
	
	public static Payment getPaymentStatus(long id){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		Payment payment = null;
		
		try {
			payment = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.paymentStatus(id);
			
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		Assert.assertNotNull(payment.getId());
		
		return payment;
	}
	
	@Test
	public void testPaymentStatus(){
//		Payment payment = getPaymentStatus(3048433544L);
		Payment payment = getPaymentStatus(3048532708L);
		System.out.println("Payment: " + payment);
		System.out.println("Payment State: " + payment.getState());
		System.out.println("Payment gwUrl: " + payment.getGwUrl());
		System.out.println("Payment preauthorization: " + payment.getPreAuthorization());
		
		System.out.println("Payment recurrence \n" + payment.getRecurrence());
		
		System.out.println("Payment substate: " + payment.getSubState());
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testPaymentStatusWrongId(){
		Payment payment = getPaymentStatus(3048268L);			// wrong ID
		System.out.println("Payment: " + payment);
		System.out.println("Payment State: " + payment.getState());
		System.out.println("Payment gwUrl: " + payment.getGwUrl());
		System.out.println("Payment preauthorization: " + payment.getPreAuthorization());
		
		System.out.println("Payment recurrence \n" + payment.getRecurrence());
	}
	
	@Test
	public void testPaymentInstrumentRoot(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentInstrumentRoot instrumentsList = null;
		
		try {
			instrumentsList = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.generatePaymentInstruments(8712700986L, Currency.CZK);
			Assert.assertNotNull(instrumentsList);
		} catch (GPClientException ex) {
			TestUtils.handleException(ex, logger);
		}
		
		System.out.println("List of enabled payment instruments for shop with go_id: " + 8712700986L);
		System.out.println(instrumentsList.getGroups());
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println(instrumentsList.getEnabledPaymentInstruments());
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testInstrumentsWrongId(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		PaymentInstrumentRoot instrumentsList = null;
		
		try {
			instrumentsList = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.generatePaymentInstruments(8002000L, Currency.CZK);
			Assert.assertNotNull(instrumentsList);
		} catch (GPClientException ex) {
			TestUtils.handleException(ex, logger);
		}
		
		System.out.println("List of enabled payment instruments for shop with go_id: " + 8712700986L);
		System.out.println(instrumentsList.getGroups());
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println(instrumentsList.getEnabledPaymentInstruments());
	}
	
	private Calendar getDateFrom(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar;
	}
	
	private Calendar getDateTo(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 27);
		return calendar;
	}
	
	@Test
	public void testStatementGenerating(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		AccountStatement accountStatement = new AccountStatement();
		
		accountStatement.setDateFrom(getDateFrom().getTime());
		accountStatement.setDateTo(getDateTo().getTime());

		accountStatement.setGoID(TestUtils.GOID);						// without - 409 Conflict, AssertionFailedError (MANDATORY)
		accountStatement.setCurrency(Currency.CZK);
		accountStatement.setFormat(StatementGeneratingFormat.CSV_A);	// without - 409 Conflict, AssertionFailedError (MANDATORY)
		
		try {
			byte[] statement = connector
					.getAppToken("1689337452", "CKr7FyEE")
					.generateStatement(accountStatement);
			Assert.assertNotNull(statement);
			System.out.println("Content of array to string: " + new String(statement));
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Byte array: ");
			for(byte currByte : statement){
				System.out.print(currByte + " ");
			}
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testStatementWrongId(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		AccountStatement accountStatement = new AccountStatement();
		
		accountStatement.setDateFrom(getDateFrom().getTime());
		accountStatement.setDateTo(getDateTo().getTime());

		accountStatement.setGoID(8712700L);
		accountStatement.setCurrency(Currency.CZK);
		accountStatement.setFormat(StatementGeneratingFormat.CSV_A);
		
		try {
			byte[] statement = connector.getAppToken("1689337452", "CKr7FyEE").generateStatement(accountStatement);
			Assert.assertNotNull(statement);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
	}
	
	@Test(expected = junit.framework.AssertionFailedError.class)
	public void testStatementUnsupportedCurrency(){
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		AccountStatement accountStatement = new AccountStatement();
		
		accountStatement.setDateFrom(getDateFrom().getTime());
		accountStatement.setDateTo(getDateTo().getTime());
		
		accountStatement.setGoID(TestUtils.GOID);
		accountStatement.setCurrency(Currency.HUF);
		accountStatement.setFormat(StatementGeneratingFormat.CSV_A);
		
		try {
			byte[] statement = connector.getAppToken("1689337452", "CKr7FyEE").generateStatement(accountStatement);
			Assert.assertNotNull(statement);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
	}
}
