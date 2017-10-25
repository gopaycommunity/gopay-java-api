package cz.gopay.api.v3.impl.cxf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import cz.gopay.api.v3.AbstractGPConnector;
import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.OAuth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.Headers;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 *
 * @author František Sichinger
 */
public class CXFGPConnector extends AbstractGPConnector {

    private CXFGPConnector(String api) {
        super(api);
    }

    private CXFGPConnector(String api, AccessToken token) {
        super(api, token);
    }

    public static CXFGPConnector build(String api) {
        return new CXFGPConnector(api);
    }

    public static CXFGPConnector build(String api, String accessToken, String refreshToken, Date expiresIn) {
        return new CXFGPConnector(api,
                new AccessToken(OAuth.TOKEN_TYPE_BEARER, accessToken, refreshToken, expiresIn.getTime()));
    }

    @Override
    protected <T> T createRESTClientProxy(String apiUrl, Class<T> proxy) {
        List providers = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setAnnotationIntrospector(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()));

        JacksonJaxbJsonProvider jsonProvider
                = new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
        providers.add(jsonProvider);
        T t = JAXRSClientFactory.create(apiUrl, proxy, providers, true);
        Client client = (Client) t;
        client.header("User-Agent", getImplementationName() + "=" + getVersion());
        return t;
    }
    
    @Override
    protected String getImplementationName() {
        return "${project.artifactId}";
    }
}
