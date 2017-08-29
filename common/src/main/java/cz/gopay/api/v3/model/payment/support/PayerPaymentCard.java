package cz.gopay.api.v3.model.payment.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pavel Valenta (pavel.valenta@gopay.cz)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PayerPaymentCard {
    
    @XmlElement(name="card_number")
    private String cardNumber;
    
    @XmlElement(name="card_expiration")
    private String cardExpiration;
    
    @XmlElement(name="card_brand")
    private String cardBrand;
    
    @XmlElement(name="card_issuer_country")
    private String cardIssuerCountry;
    
    @XmlElement(name="card_issuer_bank")
    private String cardIssuerBank;
    
    @XmlElement(name="card_token")
    private String cardToken;
    
    @XmlElement(name="3ds_result")
    private String threeDResult;
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getCardExpiration() {
        return cardExpiration;
    }
    
    public void setCardExpiration(String cardExpiration) {
        this.cardExpiration = cardExpiration;
    }
    
    public String getCardBrand() {
        return cardBrand;
    }
    
    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }
    
    public String getCardIssuerCountry() {
        return cardIssuerCountry;
    }
    
    public void setCardIssuerCountry(String cardIssuerCountry) {
        this.cardIssuerCountry = cardIssuerCountry;
    }
    
    public String getCardIssuerBank() {
        return cardIssuerBank;
    }
    
    public void setCardIssuerBank(String cardIssuerBank) {
        this.cardIssuerBank = cardIssuerBank;
    }
    
    public String getCardToken() {
        return cardToken;
    }
    
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }
    
    public String getThreeDResult() {
        return threeDResult;
    }
    
    public void setThreeDResult(String threeDResult) {
        this.threeDResult = threeDResult;
    }
    
    @Override
    public String toString() {
        return String
                .format("PayerPaymentCard [cardNumber=%s, cardExpiration=%s, cardBrand=%s, cardIssuerCountry=%s, cardIssuerBank=%s, cardToken=%s, threeDResult=%s]",
                        cardNumber, cardExpiration, cardBrand, cardIssuerCountry, cardIssuerBank, cardToken, threeDResult);
    }
    
    
    public PayerPaymentCard build(String cardNumber, String cardExpiration, String cardBrand, String cardIssuerCountry,  String cardIssuerBank) {
        PayerPaymentCard payerPaymentCard = new PayerPaymentCard();
        payerPaymentCard.setCardNumber(cardNumber);
        payerPaymentCard.setCardExpiration(cardExpiration);
        payerPaymentCard.setCardBrand(cardBrand);
        payerPaymentCard.setCardIssuerCountry(cardIssuerCountry);
        payerPaymentCard.setCardIssuerBank(cardIssuerBank);
        
        return this;
    }
    
}
