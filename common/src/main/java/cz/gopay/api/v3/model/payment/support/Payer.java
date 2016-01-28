package cz.gopay.api.v3.model.payment.support;

import java.util.List;
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
public class Payer {

    @XmlElement(name = "payment_instrument")
    private PaymentInstrument paymentInstrument;

    @XmlElement(name = "allowed_payment_instruments")
    private List<PaymentInstrument> allowedPaymentInstruments;

    @XmlElement(name = "allowed_swifts")
    private List<String> allowedSwifts;

    @XmlElement(name = "default_payment_instrument")
    private PaymentInstrument defaultPaymentInstrument;

    @XmlElement(name = "default_swift")
    private String defaultSwift;

    @XmlElement(name = "contact")
    private PayerContact contact;

    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public List<PaymentInstrument> getAllowedPaymentInstruments() {
        return allowedPaymentInstruments;
    }

    public List<String> getAllowedSwifts() {
        return allowedSwifts;
    }

    public void setAllowedSwifts(List<String> allowedSwifts) {
        this.allowedSwifts = allowedSwifts;
    }

    public void setAllowedPaymentInstruments(List<PaymentInstrument> allowedPaymentInstruments) {
        this.allowedPaymentInstruments = allowedPaymentInstruments;
    }

    public PaymentInstrument getDefaultPaymentInstrument() {
        return defaultPaymentInstrument;
    }

    public void setDefaultPaymentInstrument(PaymentInstrument defaultPaymentInstrument) {
        this.defaultPaymentInstrument = defaultPaymentInstrument;
    }

    public String getDefaultSwift() {
        return defaultSwift;
    }

    public void setDefaultSwift(String defaultBIC) {
        this.defaultSwift = defaultBIC;
    }

    public PayerContact getContact() {
        return contact;
    }

    public void setContact(PayerContact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return String.format(
                "PayerParty [paymentInstrument=%s, allowedPaymentInstruments=%s, allowedSwifts=%s, defaultPaymentInstrument=%s, defaultSwift=%s, contact=%s]",
                paymentInstrument, allowedPaymentInstruments, allowedSwifts, defaultPaymentInstrument, defaultSwift, contact);
    }

    public static Payer build() {
        return new Payer();
    }

 
}
