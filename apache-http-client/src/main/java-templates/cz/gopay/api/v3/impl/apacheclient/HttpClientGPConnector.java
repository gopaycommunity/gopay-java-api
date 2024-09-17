/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package cz.gopay.api.v3.impl.apacheclient;

import cz.gopay.api.v3.AbstractGPConnector;
import cz.gopay.api.v3.AuthClient;
import cz.gopay.api.v3.PaymentClient;
import cz.gopay.api.v3.model.access.AccessToken;

/**
 *
 * @author Frantisek Sichinger
 */
public class HttpClientGPConnector extends AbstractGPConnector {

    public static HttpClientGPConnector build(String apiUrl) {
        return new HttpClientGPConnector(apiUrl);
    }

    private HttpClientGPConnector(String apiUrl) {
        super(apiUrl);
    }
    
    private HttpClientGPConnector(String api, AccessToken accessToken, String customUserAgent) {
        super(api, accessToken, customUserAgent);
    }

    @Override
    protected <T> T createRESTClientProxy(String apiUrl, Class<T> proxy) {
        if (proxy == AuthClient.class) {
            return (T) new HttpClientAuthClientImpl(apiUrl);
        } else if (proxy == PaymentClient.class) {
            return (T) new HttpClientPaymentClientImpl(apiUrl);
        }
        throw new IllegalArgumentException("Unknown interface");
    }
    
    @Override
    protected String getImplementationName() {
        return customUserAgent == null ? "Gopay Java Apache-http-client" : customUserAgent;
    }
}
