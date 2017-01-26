package cz.gopay.api.test.payment;

import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.impl.apacheclient.HttpClientGPConnector;

import org.junit.Test;


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
        testPaymentStatus(HttpClientGPConnector.build(TestUtils.API_URL), 3000023078L);
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

}
