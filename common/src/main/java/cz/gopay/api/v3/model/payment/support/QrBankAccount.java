package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Bank account details for QR payment recipient (local + international).
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class QrBankAccount {

    @XmlElement(name = "local")
    private LocalBankAccount local;

    @XmlElement(name = "international")
    private InternationalBankAccount international;

    public LocalBankAccount getLocal() {
        return local;
    }

    public void setLocal(LocalBankAccount local) {
        this.local = local;
    }

    public InternationalBankAccount getInternational() {
        return international;
    }

    public void setInternational(InternationalBankAccount international) {
        this.international = international;
    }

    @Override
    public String toString() {
        return "QrBankAccount{local=" + local + ", international=" + international + "}";
    }
}
