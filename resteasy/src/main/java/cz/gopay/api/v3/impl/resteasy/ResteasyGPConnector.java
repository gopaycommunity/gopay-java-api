package cz.gopay.api.v3.impl.resteasy;

import cz.gopay.api.v3.AbstractGPConnector;
import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.OAuth;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientImpl;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    
    private ResteasyGPConnector(String api, AccessToken accessToken, String customUserAgent) {
        super(api, accessToken, customUserAgent);
    }

    public static ResteasyGPConnector build(String api) {
        return new ResteasyGPConnector(api);
    }
    
    public static ResteasyGPConnector build(String api, AccessToken accessToken, String customerUserAgent) {
        return new ResteasyGPConnector(api, accessToken, customerUserAgent);
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
        
        ResteasyClientBuilder builder = new ResteasyClientBuilderImpl();
        builder.connectTimeout(CONNECTION_SETUP_TO, TimeUnit.SECONDS);
        builder.readTimeout(CONNECTION_SETUP_TO, TimeUnit.SECONDS);
        
        ResteasyProviderFactory.getInstance().register(builder);
    
        ResteasyClientImpl client = (ResteasyClientImpl) builder.build();
        client.register((ClientRequestFilter) requestContext -> requestContext.getHeaders().add("User-Agent", getImplementationName() + " " + getVersion()));
        
        ObjectMapper mapper = new ObjectMapper();
        JacksonJaxbJsonProvider jaxbProvider
                = new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
        mapper.setAnnotationIntrospector(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()));

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        builder.register(jaxbProvider);
    
        ResteasyWebTarget target = client.target(i);
        return target.proxy(proxy);
    }
    
    @Override
    public String getImplementationName() {
        return customUserAgent == null ? "GoPay Java Resteasy" : customUserAgent;
    }
    
}
