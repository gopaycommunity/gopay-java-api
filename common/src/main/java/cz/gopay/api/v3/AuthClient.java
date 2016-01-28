package cz.gopay.api.v3;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.AuthHeader;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.MediaType;

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
    public AccessToken loginApplication(@BeanParam AuthHeader authHeader,
            @FormParam(value = "grant_type") String grantType,
            @FormParam(value = "scope") String scope);

}
