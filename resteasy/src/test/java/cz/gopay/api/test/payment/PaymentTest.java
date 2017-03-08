package cz.gopay.api.test.payment;

import cz.gopay.api.test.utils.TestUtils;

import org.junit.Test;

import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.access.OAuth;

public class PaymentTest extends AbstractPaymentTests {

    @Test
    public void testResteasyPaymentCreate() {
        testConnectorCreatePayment(ResteasyGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testResteasyPaymentStatus() {
        testPaymentStatus(ResteasyGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testResteasyPaymentPreauthorization() {
        testPaymentPreAuthorization(ResteasyGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testResteasyPaymentRecurrency() {
        testPaymentRecurrency(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testResteasyRefundEET() {
        testResteasyRefundEET(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
    //@Test
    public void createNextPayment() {
        createNextPayment(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
    //@Test
    public void testHttpClientVoidAuthorization() {
        testPaymentVoidAuthorization(ResteasyGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testPaymentRefund() {
        testPaymentRefund(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testPaymentInstrumentRoot(){
        testPaymentInstrumentRoot(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testGenerateStatement() {
        testGenerateStatement(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testGetToken(){
        try {
            IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL).getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL);
        } catch (GPClientException e) {
            e.printStackTrace();
        }
    }

    
    @Test
    public void testEETREceiptFindByFilter() {
        testEETREceiptFindByFilter(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
    @Test
    public void testEETReceiptFindByPayment() {
        testEETReceiptFindByPayment(ResteasyGPConnector.build(TestUtils.API_URL));
    }
    
}
