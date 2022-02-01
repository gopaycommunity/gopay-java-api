package cz.gopay.resteasy;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;

import test.utils.PaymentTest;
import test.utils.TestUtils;

public class ResteasyPaymentTest extends PaymentTest {
    
    public IGPConnector getConnector() {
        return ResteasyGPConnector.build(TestUtils.API_URL);
    }
}
