package cz.gopay.api.v3.model.payment.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PayerContact {

    @XmlElement(name = "first_name")
    private String firstName;

    @XmlElement(name = "last_name")
    private String lastName;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "phone_number")
    private String phoneNumber;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "street")
    private String street;

    @XmlElement(name = "postal_code")
    private String postalCode;

    @XmlElement(name = "country_code")
    private String countryCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return String.format(
                "PayerContact [firstName=%s, lastName=%s, email=%s, phoneNumber=%s, city=%s, street=%s, postalCode=%s, countryCode=%s]",
                firstName, lastName, email, phoneNumber, city, street, postalCode, countryCode);
    }

    public static PayerContact build(String firstName, String lastName) {
        PayerContact result = new PayerContact();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        return result;
    }

    public PayerContact withContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PayerContact onAddress(String city, String street, String postalCode, String countryCode) {
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        return this;
    }
}
