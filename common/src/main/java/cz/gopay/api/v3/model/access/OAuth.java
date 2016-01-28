package cz.gopay.api.v3.model.access;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class OAuth {

    /*grant types*/
    public static String GRANT_TYPE_PASSWORD = "password";
    public static String GRANT_TYPE_PIN = "pin";
    public static String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    public static String GRANT_TYPE_AUTH_CODE = "authorization_code";
    public static String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    /*token types*/
    public static String TOKEN_TYPE_BEARER = "bearer";

    /*access scopes*/
    public static String SCOPE_ALL = "all";
    public static String SCOPE_PAYMENT_ALL = "payment-all";
    public static String SCOPE_PAYMENT_CREATE = "payment-create";
}
