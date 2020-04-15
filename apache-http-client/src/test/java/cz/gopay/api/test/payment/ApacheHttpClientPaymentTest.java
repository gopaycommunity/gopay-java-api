package cz.gopay.api.test.payment;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.apacheclient.HttpClientGPConnector;

import test.utils.TestUtils;

public class ApacheHttpClientPaymentTest extends test.payment.PaymentTest {
    
    @Override
    public IGPConnector getConnector() {
        return HttpClientGPConnector.build(TestUtils.API_URL);
    }
}
