
package cz.gopay.api.v3.impl.apacheclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import cz.gopay.api.v3.model.APIError;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Frantisek Sichinger
 */
public class AbstractImpl {

    protected static final String AUTHORIZATION = "Authorization";
    protected static final String SCOPE = "scope";
    protected static final String GRANT_TYPE = "grant_type";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String ACCEPT = "Accept";

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
        try {
            
            HttpResponse httpresponse = response.returnResponse();
            int code = httpresponse.getStatusLine().getStatusCode();
            byte[] body = EntityUtils.toByteArray(httpresponse.getEntity());
            String json = new String(body);
            
            JsonNode tree = mapper.readTree(json);
            APIError error = mapper.treeToValue(tree, APIError.class);
            if (error.getDateIssued() != null) {
                throw new WebApplicationException(new APIResponse(error,code));
            }                    
            return mapper.treeToValue(tree, entity);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected String marshall(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
