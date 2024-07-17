package cz.gopay.api.v3.model.payment;

import javax.xml.bind.annotation.XmlElement;

public class Card {
	
	public enum CardStatus {
		ACTIVE,
		DELETED
	}
	
	@XmlElement(name = "card_id")
	private Long cardId;
	
	@XmlElement(name = "card_number")
	private String cardNumber;
	
	@XmlElement(name = "card_expiration")
	private String cardExpiration;
	
	@XmlElement(name = "card_brand")
	private String cardBrand;
	
	@XmlElement(name = "card_issuer_country")
	private String cardIssuerCountry;
	
	@XmlElement(name = "card_issuer_bank")
	private String cardIssuerBank;
	
	@XmlElement(name = "card_fingerprint")
	private String cardFingerprint;
	
	@XmlElement(name = "card_token")
	private String cardToken;
	
	@XmlElement(name = "status")
	private CardStatus status;
	
	@XmlElement(name = "real_masked_pan")
	private String realMaskedPan;
	
	@XmlElement(name = "card_art_url")
	private String cardArtUrl;
	
	public Long getCardId() {
		return cardId;
	}
	
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	
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
	
	public String getCardFingerprint() {
		return cardFingerprint;
	}
	
	public void setCardFingerprint(String cardFingerprint) {
		this.cardFingerprint = cardFingerprint;
	}
	
	public String getCardToken() {
		return cardToken;
	}
	
	public void setCardToken(String cardToken) {
		this.cardToken = cardToken;
	}
	
	public CardStatus getStatus() {
		return status;
	}
	
	public void setStatus(CardStatus status) {
		this.status = status;
	}
	
	public String getRealMaskedPan() {
		return realMaskedPan;
	}
	
	public void setRealMaskedPan(String realMaskedPan) {
		this.realMaskedPan = realMaskedPan;
	}
	
	public String getCardArtUrl() {
		return cardArtUrl;
	}
	
	public void setCardArtUrl(String cardArtUrl) {
		this.cardArtUrl = cardArtUrl;
	}
	
	@Override
	public String toString() {
		return "Card{" +
				"cardId=" + cardId +
				", cardNumber='" + cardNumber + '\'' +
				", cardExpiration='" + cardExpiration + '\'' +
				", cardBrand='" + cardBrand + '\'' +
				", cardIssuerCounry='" + cardIssuerCountry + '\'' +
				", cardIssuerBank='" + cardIssuerBank + '\'' +
				", cardFingerprint='" + cardFingerprint + '\'' +
				", cardToken='" + cardToken + '\'' +
				", status=" + status +
				", realMaskedPan='" + realMaskedPan + '\'' +
				", cardArtUrl='" + cardArtUrl + '\'' +
				'}';
	}
}
