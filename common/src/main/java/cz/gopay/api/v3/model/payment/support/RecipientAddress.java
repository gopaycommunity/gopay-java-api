package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Recipient address in QR payment info.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipientAddress {

    @XmlElement(name = "street")
    private String street;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "zip_code")
    private String zipCode;

    @XmlElement(name = "country")
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "RecipientAddress{street=" + street + ", city=" + city
                + ", zipCode=" + zipCode + ", country=" + country + "}";
    }
}
