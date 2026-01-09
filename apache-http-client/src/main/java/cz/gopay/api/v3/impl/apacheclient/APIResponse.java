package cz.gopay.api.v3.impl.apacheclient;

import cz.gopay.api.v3.model.APIError;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author František Sichinger
 */
public class APIResponse extends Response {

    private final APIError r;
    private final int status;
    
    APIResponse(APIError r, int status) {
        this.r = r;
        this.status = status;     
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public StatusType getStatusInfo() {
        return Status.fromStatusCode(status);
    }

    @Override
    public Object getEntity() {
        return r;
    }

    @Override
    public <T> T readEntity(Class<T> entityType) {
        if (entityType != APIError.class)
            throw new RuntimeException();
        return (T) r;
    }
    
    @Override
    public boolean hasEntity() {
        return r != null;
    }
    
    @Override
    public <T> T readEntity(GenericType<T> entityType) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <T> T readEntity(Class<T> entityType, Annotation[] annotations) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <T> T readEntity(GenericType<T> entityType, Annotation[] annotations) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean bufferEntity() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public MediaType getMediaType() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Locale getLanguage() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getLength() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<String> getAllowedMethods() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Map<String, NewCookie> getCookies() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public EntityTag getEntityTag() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Date getDate() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Date getLastModified() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public URI getLocation() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Set<Link> getLinks() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean hasLink(String relation) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Link getLink(String relation) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Link.Builder getLinkBuilder(String relation) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public MultivaluedMap<String, Object> getMetadata() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getHeaderString(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
   
    
}
