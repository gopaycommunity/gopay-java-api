package test.login;

import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;

import test.login.LoginTests;
import test.utils.TestUtils;

public class ResteasyLoginTests extends LoginTests {

    public IGPConnector getConnector() {
        return ResteasyGPConnector.build(TestUtils.API_URL);
    }
}
