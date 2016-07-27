package cz.gopay.api.v3.model.payment.support;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
public class Callback {

    @XmlElement(name = "return_url")
    private String returnUrl;

    @XmlElement(name = "notification_url")
    private String notificationUrl;

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    @Override
    public String toString() {
        return String.format("Callback [ returnUrl=%s, notificationUrl=%s]", returnUrl, notificationUrl);
    }

    public static Callback of(String notificationUrl) {
        Callback result = new Callback();
        result.setNotificationUrl(notificationUrl);
        return result;
    }

    public static Callback of(String returnUrl, String notificationUrl) {
        Callback result = new Callback();
        result.setReturnUrl(returnUrl);
        result.setNotificationUrl(notificationUrl);
        return result;
    }

}
