package test.login;

import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import test.RestClientTest;
import test.utils.TestUtils;

public abstract class LoginTests implements RestClientTest {

    private static final Logger logger = Logger.getLogger(LoginTests.class);

    @Test
    public void testAuthApacheHttpClient() {
        try {
            IGPConnector connector = getConnector().getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET);
            Assert.assertNotNull(connector.getAccessToken());
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }
    
    
}
