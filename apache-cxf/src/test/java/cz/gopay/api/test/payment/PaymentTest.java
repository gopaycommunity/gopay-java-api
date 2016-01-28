package cz.gopay.api.test.payment;

import cz.gopay.api.test.utils.TestUtils;

import org.junit.Test;

import cz.gopay.api.v3.impl.cxf.CXFGPConnector;

public class PaymentTest extends AbstractPaymentTests {

    @Test
    public void testCXFPaymentCreate() {
        testConnectorCreatePayment(CXFGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testCXFPaymentStatus() {
        testPaymentStatus(CXFGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testCXFPaymentPreauthorization() {
        testPaymentPreAuthorization(CXFGPConnector.build(TestUtils.API_URL));
    }

    @Test
    public void testCXFPaymentRecurrency() {
        testPaymentRecurrency(CXFGPConnector.build(TestUtils.API_URL));
    }


    //@Test
    public void testHttpClientVoidAuthorization() {
        testPaymentVoidAuthorization(CXFGPConnector.build(TestUtils.API_URL));
    }

    //@Test
    public void testPaymentRefund() {
        testPaymentRefund(CXFGPConnector.build(TestUtils.API_URL));
    }

}
