package cz.gopay.api.v3;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;

import cz.gopay.api.v3.model.APIError;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class GPExceptionHandler {

    public static void handleException(WebApplicationException ex) throws GPClientException, ClientErrorException {
        if (!ex.getResponse().hasEntity()) {
            throw ex;
        }
        try {
            APIError error = ex.getResponse().readEntity(APIError.class);
            throw new GPClientException(ex.getResponse().getStatus(), error);

        } catch (ProcessingException e) {        
            
        }
        throw ex;
    }

}
