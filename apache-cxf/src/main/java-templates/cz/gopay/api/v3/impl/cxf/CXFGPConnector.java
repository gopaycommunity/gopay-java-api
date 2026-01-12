package cz.gopay.api.v3.impl.cxf;

import cz.gopay.api.v3.AbstractGPConnector;
import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.OAuth;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.jakarta.rs.json.JacksonXmlBindJsonProvider;
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationIntrospector;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    private CXFGPConnector(String api, AccessToken token, String customUserAgent) {
        super(api, token, customUserAgent);
    }
    
    public static CXFGPConnector build(String api, AccessToken token, String customUserAgent) {
        return new CXFGPConnector(api, token, customUserAgent);
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
		
		AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
		AnnotationIntrospector jakartaXmlBindIntrospector =
				new JakartaXmlBindAnnotationIntrospector(mapper.getTypeFactory());
		
		// Combine Jackson and Jakarta XML Binding introspectors
		AnnotationIntrospector pair = AnnotationIntrospector.pair(jacksonIntrospector, jakartaXmlBindIntrospector);
        mapper.setAnnotationIntrospector(pair);
		
		JacksonXmlBindJsonProvider jsonProvider
				= new JacksonXmlBindJsonProvider(mapper, JacksonXmlBindJsonProvider.DEFAULT_ANNOTATIONS);
        providers.add(jsonProvider);
        T t = JAXRSClientFactory.create(apiUrl, proxy, providers, true);
        Client client = (Client) t;
        client.header("User-Agent", getImplementationName() + " " + getVersion());
        return t;
    }
    
    @Override
    protected String getImplementationName() {
        return customUserAgent == null ? "GoPay Java Apache-cfx" : customUserAgent;
    }
}
