package cz.gopay.cfx;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.cxf.CXFGPConnector;

import test.utils.LoginTests;
import test.utils.TestUtils;

public class CXFLoginTest extends LoginTests {
    
    public IGPConnector getConnector() {
        return CXFGPConnector.build(TestUtils.API_URL);
    }
}
