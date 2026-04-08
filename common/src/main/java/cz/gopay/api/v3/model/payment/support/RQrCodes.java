package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * QR codes for payment – different types based on currency/country.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RQrCodes {

    /**
     * Czech payment QR code (SPAYD) - only available for the CZK currency
     */
    @XmlElement(name = "spayd")
    private String spayd;

    /**
     * Slovak payment QR code (PayBySquare) - only available for the EUR currency
     */
    @XmlElement(name = "paybysquare")
    private String payBySquare;

    /**
     * SEPA payment QR code (specified by EPC) - only available for the EUR currency
     */
    @XmlElement(name = "sepa")
    private String sepa;

    /**
     * Hungarian payment QR code (specified by MNB) - only available for the HUF currency
     */
    @XmlElement(name = "mnb_qr")
    private String mnbQr;

    public String getSpayd() {
        return spayd;
    }

    public void setSpayd(String spayd) {
        this.spayd = spayd;
    }

    public String getPayBySquare() {
        return payBySquare;
    }

    public void setPayBySquare(String payBySquare) {
        this.payBySquare = payBySquare;
    }

    public String getSepa() {
        return sepa;
    }

    public void setSepa(String sepa) {
        this.sepa = sepa;
    }

    public String getMnbQr() {
        return mnbQr;
    }

    public void setMnbQr(String mnbQr) {
        this.mnbQr = mnbQr;
    }

    @Override
    public String toString() {
        return "RQrCodes{spayd=" + (spayd != null ? "[base64]" : "null")
                + ", payBySquare=" + (payBySquare != null ? "[base64]" : "null")
                + ", sepa=" + (sepa != null ? "[base64]" : "null")
                + ", mnbQr=" + (mnbQr != null ? "[base64]" : "null") + "}";
    }
}
