package cz.gopay.api.v3.impl.resteasy;

import cz.gopay.api.v3.AbstractGPConnector;
import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.OAuth;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.URLConnectionEngine;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 *
 * @author František Sichinger
 */
public class ResteasyGPConnector extends AbstractGPConnector {

    private ResteasyGPConnector(String api) {
        super(api);
    }

    private ResteasyGPConnector(String api, AccessToken accessToken) {
        super(api, accessToken);
    }

    public static ResteasyGPConnector build(String api) {
        return new ResteasyGPConnector(api);
    }

    public static ResteasyGPConnector build(String api, String accessToken, String refreshToken, Date expiresIn) {
        return new ResteasyGPConnector(api,
                new AccessToken(OAuth.TOKEN_TYPE_BEARER, accessToken, refreshToken, expiresIn.getTime()));
    }

    @Override
    protected <T> T createRESTClientProxy(String apiUrl, Class<T> proxy) {
        URI i = null;
        try {
            i = new URI(apiUrl);
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        ResteasyClientBuilder builder = new ResteasyClientBuilder();
        builder.connectionCheckoutTimeout(CONNECTION_SETUP_TO, TimeUnit.SECONDS);
        builder.socketTimeout(CONNECTION_SETUP_TO, TimeUnit.SECONDS);
        builder.httpEngine(new URLConnectionEngine());
        
        ResteasyProviderFactory.getInstance().register(builder);

        ResteasyClient client = builder.build();
        client.register(new ClientRequestFilter() {
            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().add("User-Agent", getImplementationName() + "=" + getVersion());
            }
        });
        ObjectMapper mapper = new ObjectMapper();
        JacksonJaxbJsonProvider jaxbProvider
                = new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
        mapper.setAnnotationIntrospector(new JaxbAnnotationIntrospector());

        mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        builder.register(jaxbProvider);
        builder.register(proxy);

        ResteasyWebTarget resteasyWebTarget = client.target(i);
        return resteasyWebTarget.proxy(proxy);
    }
    
    @Override
    public String getImplementationName() {
        return "${project.artifactId}";
    }
    
}
