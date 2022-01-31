package test.utils;

import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class LoginTests implements RestClientTest {

    private static final Logger logger = LogManager.getLogger(LoginTests.class);

    @Test
    public void testAuthApacheHttpClient() {
        try {
            IGPConnector connector = getConnector().getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET);
            Assertions.assertNotNull(connector.getAccessToken());
        } catch (GPClientException ex) {
            TestUtils.handleException(ex, logger);
        }
    }
    
    
}
