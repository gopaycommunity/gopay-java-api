package cz.gopay.api.v3.model.access;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

import jakarta.ws.rs.HeaderParam;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthHeader {

    @HeaderParam(value = "Authorization")
    private String auhorization;

    public String getAuhorization() {
        return auhorization;
    }

    public void setAuhorization(String auhorization) {
        this.auhorization = auhorization;
    }

    @Override
    public String toString() {
        return "AuthHeader [authorization=" + auhorization + "]";
    }

    public static AuthHeader build(String clientId, String clientSecret) {
        try {
            AuthHeader result = new AuthHeader();

            String toEncode = clientId + ":" + clientSecret;
            String ulrEncoded = URLEncoder.encode(toEncode, "UTF-8");
            String base64 = "Basic " + Base64.getEncoder().encodeToString(ulrEncoded.getBytes());

            result.setAuhorization(base64);

            return result;

        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static AuthHeader build(String accessToken) {
        AuthHeader result = new AuthHeader();
        result.setAuhorization("Bearer " + accessToken);

        return result;
    }

}
