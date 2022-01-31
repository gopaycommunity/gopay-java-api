/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package test.utils;

import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.common.StatementGeneratingFormat;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.Lang;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.BasePaymentBuilder;
import cz.gopay.api.v3.model.payment.PaymentFactory;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.support.AccountStatement;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;
import cz.gopay.api.v3.model.payment.support.PayerBuilder;
import cz.gopay.api.v3.model.payment.support.PaymentInstrumentRoot;
import cz.gopay.api.v3.model.payment.support.Recurrence;
import cz.gopay.api.v3.model.payment.support.RecurrenceCycle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author František Sichinger
 */
public abstract class AbstractPaymentTests implements RestClientTest {

    private static final Logger logger = LogManager.getLogger(AbstractPaymentTests.class);

    private BasePayment createTestBasePayment() {
        String url = "https://www.eshop123.cz/";
        
        Payer payer = new PayerBuilder().withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
                .addAllowedSwift("FIOBCZPP").build();
        BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
        return builder.withCallback(url+"notify", url+"return")
               .order("123", 10L, Currency.EUR, "description")
               .inLang(Lang.EN)
               .addAdditionalParameter("AKey2", "AValue")
               .addItem("An item", 1L, 1L)
               .toEshop(TestUtils.GOID)
               .payer(payer).build();
    }

    protected long testConnectorCreatePayment(IGPConnector connector) {
        Payment payment = null;
        try {
            BasePayment createpayment = createTestBasePayment();
            payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
        Assertions.assertNotNull(payment.getId());
        Assertions.assertTrue(payment.getId() > 0);
        return payment.getId();
    }

    protected void testPaymentStatus(IGPConnector connector) {
        long id = testConnectorCreatePayment(connector);
        Payment payment = null;
        try {
            payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET).paymentStatus(id);

        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
    
        Assertions.assertNotNull(payment.getId());
    }
    
    protected void testPaymentRefund(IGPConnector connector) {
        try {
            PaymentResult refundPayment = connector.
                    getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
                    .refundPayment(3000027082L, 2L);
            Assertions.assertEquals(3000027082L, (long) refundPayment.getId());
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }

    protected void testPaymentPreAuthorization(IGPConnector connector) {
        Payment payment = null;
        try {
            BasePayment createpayment = createTestBasePayment();
            createpayment.setPreAuthorization(true);
            payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
        Assertions.assertNotNull(payment);
        Assertions.assertTrue(payment.getId() > 0);
    }

    protected long testPaymentRecurrency(IGPConnector connector) {
        Payment payment = null;
        try {
            
            BasePayment createpayment = createTestBasePayment();
            Recurrence recurrence = new Recurrence();
            recurrence.setRecurrencePeriod(1);
            recurrence.setRecurrenceState(Recurrence.RecurrenceState.STARTED);
            recurrence.setRecurrenceCycle(RecurrenceCycle.WEEK);
            long time = System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000;
            recurrence.setRecurrenceDateTo(new Date(time));
            createpayment.setRecurrence(recurrence);
            payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
        Assertions.assertNotNull(payment);
        Assertions.assertTrue(payment.getId() > 0);
        return payment.getId();
    }

    protected void testPaymentVoidAuthorization(IGPConnector connector) {
        long id = testPaymentRecurrency(connector);
        try {
            PaymentResult voidAuthorization = connector
                    .getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL).
                    voidAuthorization(id);
            
            Assertions.assertNotNull(voidAuthorization);
            Assertions.assertTrue(voidAuthorization.getId() > 0);
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }
    protected void testPaymentInstrumentRoot(IGPConnector connector){
        try {
            PaymentInstrumentRoot instrumentsList = connector
                    .getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
                    .generatePaymentInstruments(TestUtils.GOID, Currency.CZK);
            Assertions.assertNotNull(instrumentsList);
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }
    
    
    protected void testGenerateStatement(IGPConnector connector) {
        AccountStatement accountStatement = new AccountStatement();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        accountStatement.setDateFrom(calendar.getTime());
        
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        accountStatement.setDateTo(calendar.getTime());
        
        accountStatement.setGoID(TestUtils.GOID);
        accountStatement.setCurrency(Currency.CZK);
        accountStatement.setFormat(StatementGeneratingFormat.CSV_A);
    
        try {
            byte[] statement = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET).generateStatement(accountStatement);
            Assertions.assertNotNull(statement);
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
    }
    
    
    
    protected void testEETREceiptFindByFilter(IGPConnector connector) {
        Calendar calendarFrom = Calendar.getInstance();
        calendarFrom.set(Calendar.DAY_OF_MONTH, 10);
        calendarFrom.set(Calendar.MONTH, 1);
        calendarFrom.set(Calendar.YEAR, 2016);
        
        Calendar calendarTo = Calendar.getInstance();
        calendarTo.set(Calendar.DAY_OF_MONTH, 20);
        calendarTo.set(Calendar.MONTH, 1);
        calendarTo.set(Calendar.YEAR, 2022);
        
        EETReceiptFilter filter = EETReceiptFilter.create(12, calendarFrom.getTime(), calendarTo.getTime());
        
        try {
            List<EETReceipt> receipts = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
                    .findEETREceiptsByFilter(filter);
            Assertions.assertNotNull(receipts);
            Assertions.assertTrue(!receipts.isEmpty());
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
    }
    
    
    protected void testEETReceiptFindByPayment(IGPConnector connector) {
        try {
            List<EETReceipt> receipts = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
                    .getEETReceiptByPaymentId(3000012418L);
            Assertions.assertNotNull(receipts);
            Assertions.assertTrue(!receipts.isEmpty());
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
    }
}
