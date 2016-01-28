package cz.gopay.api.test.login;

import cz.gopay.api.test.utils.TestUtils;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;

public class LoginTest {

    private static final Logger logger = Logger.getLogger(LoginTest.class);

    @Test
    public void testAuthClientResteasy() {
        try {
            IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL).getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET);
            Assert.assertNotNull(connector.getAccessToken());
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }
}
