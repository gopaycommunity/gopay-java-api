package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Recipient info in QR payment response.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class QrPaymentRecipient {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "bank_account")
    private QrBankAccount bankAccount;

    @XmlElement(name = "address")
    private RecipientAddress address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QrBankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(QrBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public RecipientAddress getAddress() {
        return address;
    }

    public void setAddress(RecipientAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "QrPaymentRecipient{name=" + name + ", bankAccount=" + bankAccount + ", address=" + address + "}";
    }
}
