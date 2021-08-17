package test.login;

import cz.gopay.api.v3.IGPConnector;

import cz.gopay.api.v3.impl.apacheclient.HttpClientGPConnector;

import test.utils.TestUtils;

public class ApacheHttpClientLoginTest extends LoginTests {
    
    public IGPConnector getConnector() {
        return HttpClientGPConnector.build(TestUtils.API_URL);
    }
}