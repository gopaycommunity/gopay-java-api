package cz.gopay.cfx;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.cxf.CXFGPConnector;

import test.utils.PaymentTest;
import test.utils.TestUtils;

public class CXFPaymentTest extends PaymentTest {
    
    public IGPConnector getConnector() {
        return CXFGPConnector.build(TestUtils.API_URL);
    }
}
