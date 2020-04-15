package cz.gopay.api.test.login;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.cxf.CXFGPConnector;

import test.login.LoginTests;
import test.utils.TestUtils;

public class CXFLoginTest extends LoginTests {

    @Override
    public IGPConnector getConnector() {
        return CXFGPConnector.build(TestUtils.API_URL);
    }
}
