package cz.gopay.api.test.payment;

import cz.gopay.api.test.utils.TestUtils;

import org.junit.Test;

import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;

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

    //@Test
    public void testHttpClientVoidAuthorization() {
        testPaymentVoidAuthorization(ResteasyGPConnector.build(TestUtils.API_URL));
    }

    //@Test
    public void testPaymentRefund() {
        testPaymentRefund(ResteasyGPConnector.build(TestUtils.API_URL));
    }

}
