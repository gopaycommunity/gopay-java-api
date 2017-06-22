package cz.gopay.api.test.payment;

import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.apacheclient.HttpClientGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.eet.EETReceipt;
import cz.gopay.api.v3.model.eet.EETReceiptFilter;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;


public class PaymentTest extends AbstractPaymentTests {

    @Test
    public void testHttpClientCreatePayment() {
        testConnectorCreatePayment(HttpClientGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testHttpClientPaymentRecurrency() {
        testPaymentRecurrency(HttpClientGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testHttpClientPaymentStataus() {
        testPaymentStatus(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testHttpClientPaymentStatausWithId() {
        testPaymentStatus(HttpClientGPConnector.build(TestUtils.API_URL), 3048532578L);
    }
    
    @Test
    public void testHttpClientPaymentPreAuthorization() {
        testPaymentPreAuthorization(HttpClientGPConnector.build(TestUtils.API_URL));
    }

    //@Test
    public void testHttpClientVoidAuthorization() {
        testPaymentVoidAuthorization(HttpClientGPConnector.build(TestUtils.API_URL));
    }

    //@Test
    public void testPaymentRefund() {
        testPaymentRefund(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    
    @Test
    public void testEETREceiptFindByFilter() {
        testEETREceiptFindByFilter(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
   // @Test
    public void testEETReceiptFindByPayment() {
        testEETReceiptFindByPayment(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    
    @Test
    public void testPaymentInstrumentRootHttp(){
        testPaymentInstrumentRoot(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testGenerateStatementHttp() {
        testGenerateStatement(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testCreateSupercashCoupon() {
        testCreateSupercashCoupon(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testCreateSupercashCouponBatch() {
        testCreateSupercashCouponBatch(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testGetSupercashCouponBatchStatus() {
        testGetSupercashCouponBatchStatus(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testGetSupercashCouponBatch() {
        testGetSupercashCouponBatch(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testFindSupercashCoupons() {
        testFindSupercashCoupons(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testGetSupercashCoupon() {
        testGetSupercashCoupon(HttpClientGPConnector.build(TestUtils.API_URL));
    }
    
    
}
