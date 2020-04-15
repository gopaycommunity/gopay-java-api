package cz.gopay.api.test.payment;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;

import test.utils.TestUtils;

public class ResteasyPaymentTest extends test.payment.PaymentTest {
    
    @Override
    public IGPConnector getConnector() {
        return ResteasyGPConnector.build(TestUtils.API_URL);
    }
}
