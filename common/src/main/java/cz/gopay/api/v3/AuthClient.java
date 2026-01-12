package cz.gopay.api.v3;

import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.AuthHeader;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public interface AuthClient {
    
    @POST
    @Path("/oauth2/token")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AccessToken loginApplication(@BeanParam AuthHeader authHeader,
            @FormParam(value = "grant_type") String grantType,
            @FormParam(value = "scope") String scope);

}
