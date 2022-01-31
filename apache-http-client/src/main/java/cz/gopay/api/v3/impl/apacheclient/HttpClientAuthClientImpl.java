/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package cz.gopay.api.v3.impl.apacheclient;

import cz.gopay.api.v3.AuthClient;
import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.AuthHeader;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;

public class HttpClientAuthClientImpl extends AbstractImpl implements AuthClient {

    protected HttpClientAuthClientImpl(String apiUrl) {
        super(apiUrl);
        super.logger = LogManager.getLogger(HttpClientAuthClientImpl.class);
    }

    @Override
    public AccessToken loginApplication(AuthHeader authHeader, String grantType, String scope) {
        Form form = Form.form();
        form.add(SCOPE, scope);
        form.add(GRANT_TYPE, grantType);
        Response respose = null;

        try {
            respose = Request.Post(apiUrl + "/oauth2/token")
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .addHeader(CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .bodyForm(form.build())
                    .bodyString("grant_type=client_credentials&scope=" + scope, ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebApplicationException();
        }

        return unMarshall(respose, AccessToken.class);
    }

}
