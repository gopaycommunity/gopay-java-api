package test.payment;

import org.junit.Test;


public abstract class PaymentTest extends AbstractPaymentTests {

    @Test
    public void testHttpClientCreatePayment() {
        testConnectorCreatePayment(getConnector());
    }

    @Test
    public void testHttpClientPaymentRecurrency() {
        testPaymentRecurrency(getConnector());
    }

    @Test
    public void testHttpClientPaymentStataus() {
        testPaymentStatus(getConnector());
    }
    
    @Test
    public void testHttpClientPaymentStatausWithId() {
        testPaymentStatus(getConnector(), 3048532578L);
    }
    
    @Test
    public void testHttpClientPaymentPreAuthorization() {
        testPaymentPreAuthorization(getConnector());
    }

    //@Test
    public void testHttpClientVoidAuthorization() {
        testPaymentVoidAuthorization(getConnector());
    }

    //@Test
    public void testPaymentRefund() {
        testPaymentRefund(getConnector());
    }
    
    
   // @Test
    public void testEETREceiptFindByFilter() {
        testEETREceiptFindByFilter(getConnector());
    }
    
   // @Test
    public void testEETReceiptFindByPayment() {
        testEETReceiptFindByPayment(getConnector());
    }
    
    
    @Test
    public void testPaymentInstrumentRootHttp(){
        testPaymentInstrumentRoot(getConnector());
    }
    
    @Test
    public void testGenerateStatementHttp() {
        testGenerateStatement(getConnector());
    }
    
    //@Test
    public void testCreateSupercashCoupon() {
        testCreateSupercashCoupon(getConnector());
    }
    
    @Test
    public void testCreateSupercashCouponBatch() {
        testCreateSupercashCouponBatch(getConnector());
    }
    
    @Test
    public void testGetSupercashCouponBatchStatus() {
        testGetSupercashCouponBatchStatus(getConnector());
    }
    
    @Test
    public void testGetSupercashCouponBatch() {
        testGetSupercashCouponBatch(getConnector());
    }
    
    @Test
    public void testFindSupercashCoupons() {
        testFindSupercashCoupons(getConnector());
    }
    
    @Test
    public void testGetSupercashCoupon() {
        testGetSupercashCoupon(getConnector());
    }
    
}
