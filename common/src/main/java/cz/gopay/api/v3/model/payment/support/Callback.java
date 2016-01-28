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

    @XmlElement(name = "success_url")
    private String successUrl;

    @XmlElement(name = "failed_url")
    private String failedUrl;

    @XmlElement(name = "return_url")
    private String returnUrl;

    @XmlElement(name = "notification_url")
    private String notificationUrl;

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailedUrl() {
        return failedUrl;
    }

    public void setFailedUrl(String failedUrl) {
        this.failedUrl = failedUrl;
    }

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
        return String.format("Callback [successUrl=%s, failedUrl=%s, returnUrl=%s, notificationUrl=%s]",
                successUrl, failedUrl, returnUrl, notificationUrl);
    }

    public static Callback of(String successUrl, String failedUrl, String notificationUrl) {
        Callback result = new Callback();
        result.setSuccessUrl(successUrl);
        result.setFailedUrl(failedUrl);
        result.setNotificationUrl(notificationUrl);
        return result;
    }

    public static Callback of(String returnUrl, String notificationUrl) {
        Callback result = new Callback();
        result.setReturnUrl(returnUrl);
        result.setNotificationUrl(notificationUrl);
        return result;
    }
    
    public static Callback of(String successfullUrl, String failedUrl, String notificationUrl, String returnUrl) {
        Callback result = new Callback();
        result.setReturnUrl(returnUrl);
        result.setNotificationUrl(notificationUrl);
        result.setFailedUrl(failedUrl);
        result.setSuccessUrl(successfullUrl);
        return result;           
    }

}
