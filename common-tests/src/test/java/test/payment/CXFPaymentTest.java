package test.payment;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.cxf.CXFGPConnector;

import test.utils.TestUtils;

public class CXFPaymentTest extends PaymentTest {
    
    @Override
    public IGPConnector getConnector() {
        return CXFGPConnector.build(TestUtils.API_URL);
    }
}
