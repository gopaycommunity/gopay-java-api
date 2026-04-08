package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * International bank account details (IBAN / BIC) used in QR payment recipient.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class InternationalBankAccount {

    @XmlElement(name = "bic")
    private String bic;

    @XmlElement(name = "iban")
    private String iban;

    @XmlElement(name = "reference")
    private String reference;

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "InternationalBankAccount{bic=" + bic + ", iban=" + iban + ", reference=" + reference + "}";
    }
}
