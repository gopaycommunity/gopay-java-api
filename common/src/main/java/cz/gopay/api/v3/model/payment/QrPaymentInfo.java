package cz.gopay.api.v3.model.payment;

import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.payment.support.QrPaymentRecipient;
import cz.gopay.api.v3.model.payment.support.RQrCodes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Response model for GET /api/payments/payment/{id}/qr-payment endpoint.
 * Contains payment amount, currency, recipient info and the actual QR code(s).
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QrPaymentInfo {

    @XmlElement(name = "amount")
    private Long amount;

    @XmlElement(name = "currency")
    private Currency currency;

    @XmlElement(name = "recipient")
    private QrPaymentRecipient recipient;

    @XmlElement(name = "qr_code")
    private RQrCodes qrCode;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public QrPaymentRecipient getRecipient() {
        return recipient;
    }

    public void setRecipient(QrPaymentRecipient recipient) {
        this.recipient = recipient;
    }

    public RQrCodes getQrCode() {
        return qrCode;
    }

    public void setQrCode(RQrCodes qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "QrPaymentInfo{amount=" + amount + ", currency=" + currency
                + ", recipient=" + recipient + ", qrCode=" + qrCode + "}";
    }
}
