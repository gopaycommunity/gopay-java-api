
package cz.gopay.api.test.payment;

import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;
import cz.gopay.api.v3.model.common.StatementGeneratingFormat;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.Lang;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.BasePaymentBuilder;
import cz.gopay.api.v3.model.payment.PaymentFactory;
import cz.gopay.api.v3.model.payment.PaymentResult;
import cz.gopay.api.v3.model.payment.RefundPayment;
import cz.gopay.api.v3.model.payment.VatRate;
import cz.gopay.api.v3.model.payment.support.AccountStatement;
import cz.gopay.api.v3.model.eet.EET;
import cz.gopay.api.v3.model.payment.support.ItemType;
import cz.gopay.api.v3.model.payment.support.OrderItem;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;
import cz.gopay.api.v3.model.payment.support.PayerBuilder;
import cz.gopay.api.v3.model.payment.support.PaymentInstrumentRoot;
import cz.gopay.api.v3.model.payment.support.Recurrence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;
import org.apache.log4j.Logger;

/**
 *
 * @author František Sichinger
 */
public class AbstractPaymentTests {

    private static final Logger logger = Logger.getLogger(AbstractPaymentTests.class);

    private BasePayment createTestBasePayment() {
        String url = "https://www.eshop123.cz/";
        
        Payer payer = new PayerBuilder().withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
                .addAllowedSwift("FIOBCZPP").build();
        BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
        BasePayment basePayment = builder.withCallback(url+"notify", url+"return")
               .order("123", 10L, Currency.EUR, "description")
               .inLang(Lang.EN)
               .addAdditionalParameter("AKey2", "AValue")
               .addItem("An item", 1L, 1L, VatRate.RATE_1, ItemType.ITEM, "4567891234", "url")
               .toEshop(TestUtils.GOID)
               .payer(payer).build();
        return basePayment;
    }

    protected long testConnectorCreatePayment(IGPConnector connector) {
        Payment payment = null;
        try {
            BasePayment createpayment = createTestBasePayment();
            payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
        Assert.assertNotNull(payment.getId());
        System.out.println("Payment gwUrl: " + payment.getGwUrl());
        System.out.println("Payment ID: " + payment.getId());
        Assert.assertTrue(payment.getId() > 0);
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

        Assert.assertNotNull(payment.getId());
    }

    protected void testPaymentRefund(IGPConnector connector) {
        try {
            PaymentResult refundPayment = connector.
                    getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL).
                    refundPayment(3048071845L, 10L);
            Assert.assertTrue(refundPayment.getId() == 3048071845L);
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }
    
    public void createNextPayment(IGPConnector connector) {
        NextPayment nextPayment = new NextPayment();
        EET eet = new EET();
        eet.setDan1(0L);
        nextPayment.setEet(eet);
        nextPayment.setItems(new ArrayList<OrderItem>());
        nextPayment.getItems().add(new OrderItem(){{
            setProductURL("url");
            setEan("ean");
            setVatRate(VatRate.RATE_3);
            setCount(10L);
            setName("name");
            setCount(1L);
            setAmount(500L);
        }});
        nextPayment.setAmount(10L);
        nextPayment.setCurrency(Currency.EUR);
        nextPayment.setOrderNumber("123");
       
        /*
        Long id = ...
        try {
            Payment payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL).createRecurrentPayment(id, nextPayment);
            Assert.assertNotNull(payment);
            Assert.assertNotNull(payment.getId());
        } catch (GPClientException e) {
            e.printStackTrace();
        }
        */
    }
    
    protected void testResteasyRefundEET(IGPConnector connector) {
    
        try {
            PaymentResult payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET)
                    .refundPayment(3000012093L, new RefundPayment(){{
				setAmount(10L);
				setItems(new ArrayList<OrderItem>());
				getItems().add(new OrderItem(){{
				    setCount(10L);
				    setItemType(ItemType.DELIVERY);
                }});
				setEet(new EET(){{
				    setDan2(546546L);
                }});
			}});
            Assert.assertNotNull(payment);
            Assert.assertNotNull(payment.getId());
        } catch (GPClientException e) {
            e.printStackTrace();
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
        Assert.assertNotNull(payment);
        Assert.assertTrue(payment.getId() > 0);
    }

    protected long testPaymentRecurrency(IGPConnector connector) {
        Payment payment = null;
        try {
            
            BasePayment createpayment = createTestBasePayment();
            Recurrence recurrence = new Recurrence();
            recurrence.onDemand();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis() + 5000000000L);
            recurrence.setRecurrenceDateTo(c.getTime());
            createpayment.setRecurrence(recurrence);
            payment = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
        Assert.assertNotNull(payment);
        Assert.assertTrue(payment.getId() > 0);
        System.out.println(payment.getId());
        System.out.println(payment.getGwUrl());
        return payment.getId();
    }

    protected void testPaymentVoidAuthorization(IGPConnector connector) {
        long id = testPaymentRecurrency(connector);
        try {
            PaymentResult voidAuthorization = connector
                    .getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL).
                    voidAuthorization(id);
            Assert.assertNotNull(voidAuthorization);
            Assert.assertTrue(voidAuthorization.getId() > 0);
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }
    
    protected void testPaymentInstrumentRoot(IGPConnector connector){
        try {
            PaymentInstrumentRoot instrumentsList = connector
                    .getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL).generatePaymentInstruments(8712700986L, Currency.CZK);
            Assert.assertNotNull(instrumentsList);
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
            byte[] statement = connector.getAppToken("1689337452", "CKr7FyEE").generateStatement(accountStatement);
            Assert.assertNotNull(statement);
            System.out.println("Content of bytes array to string: " + new String(statement));
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
    }
    
    
    protected void testEETREceiptFindByFilter(IGPConnector connector) {
        Calendar calendarFrom = Calendar.getInstance();
        calendarFrom.set(Calendar.DAY_OF_MONTH, 10);
        calendarFrom.set(Calendar.MONTH, 1);
        calendarFrom.set(Calendar.YEAR, 2017);
    
        Calendar calendarTo = Calendar.getInstance();
        calendarTo.set(Calendar.DAY_OF_MONTH, 20);
        calendarTo.set(Calendar.MONTH, 1);
        calendarTo.set(Calendar.YEAR, 2017);
        
        EETReceiptFilter filter = EETReceiptFilter.create(12, calendarFrom.getTime(), calendarTo.getTime());
        
        try {
            List<EETReceipt> receipts = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_CREATE)
					.findEETREceiptsByFilter(filter);
            Assert.assertNotNull(receipts);
            Assert.assertTrue(!receipts.isEmpty());
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
    }
    
    
    protected void testEETReceiptFindByPayment(ResteasyGPConnector connector) {
        try {
            List<EETReceipt> receipts = connector.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_CREATE)
                    .getEETReceiptByPaymentId(3000012418L);
            Assert.assertNotNull(receipts);
            Assert.assertTrue(!receipts.isEmpty());
        } catch (GPClientException e) {
            TestUtils.handleException(e, logger);
        }
    }
}
