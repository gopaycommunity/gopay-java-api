package cz.gopay.cfx;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.apacheclient.HttpClientGPConnector;

import test.utils.PaymentTest;
import test.utils.TestUtils;

public class ApacheHttpClientPaymentTest extends PaymentTest {
    
    public IGPConnector getConnector() {
        return HttpClientGPConnector.build(TestUtils.API_URL);
    }
}
