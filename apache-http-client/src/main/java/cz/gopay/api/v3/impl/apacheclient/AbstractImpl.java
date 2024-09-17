
package cz.gopay.api.v3.impl.apacheclient;

import cz.gopay.api.v3.AbstractGPConnector;
import cz.gopay.api.v3.model.APIError;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Frantisek Sichinger
 */
public class AbstractImpl {
    
    protected static final String IMPLEMENTATION_NAME = "Gopay Java Apache-http-client";
    protected static final String VERSION = AbstractGPConnector.VERSION;
    
    protected static final String AUTHORIZATION = "Authorization";
    protected static final String SCOPE = "scope";
    protected static final String GRANT_TYPE = "grant_type";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String ACCEPT = "Accept";
    protected static final String USER_AGENT = "User-Agent";

    protected String apiUrl;
    private final ObjectMapper mapper;
    protected Logger logger;

    public AbstractImpl(String apiUrl) {
        this.apiUrl = apiUrl;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setAnnotationIntrospector(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()));
    }

    protected <T> T unMarshall(Response response, Class<T> entity) {
        String json = null;
        try {
            HttpResponse httpresponse = response.returnResponse();
            json = entityToString(httpresponse);
            JsonNode tree = mapper.readTree(json);
            APIError error = mapper.treeToValue(tree, APIError.class);
            if (error.getDateIssued() != null) {
                int code = httpresponse.getStatusLine().getStatusCode();
                throw new WebApplicationException(new APIResponse(error,code));
            }                    
            return mapper.treeToValue(tree, entity);
        } catch (JsonParseException e) {
            throw new WebApplicationException("Could not parse json " + json);
        } catch (IOException ex) {
            throw new WebApplicationException(ex);
        }
    }

    protected <T> T unMarshallComplexResponse(Response response, TypeReference<T> typeReference) {
        try {
            HttpResponse httpresponse = response.returnResponse();
            String json = entityToString(httpresponse);
            JsonNode tree = mapper.readTree(json);
            if (tree.findValue("errors") != null) {
                APIError error = mapper.treeToValue(tree, APIError.class);
                int code = httpresponse.getStatusLine().getStatusCode();
                throw new WebApplicationException(new APIResponse(error,code));
            }
            return mapper.readValue(json.getBytes(), typeReference);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    private String entityToString(HttpResponse response) throws IOException {
        byte[] body = EntityUtils.toByteArray(response.getEntity());
        return new String(body);
    }
    
    protected String marshall(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
