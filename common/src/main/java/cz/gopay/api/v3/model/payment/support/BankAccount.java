package cz.gopay.api.v3.model.payment.support;

import cz.gopay.api.v3.model.common.CountryCode;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccount {
	
	@XmlElement(name="prefix")
	private String prefix;
	
	@XmlElement(name="account_number")
	private String accountNumber;
	
	@XmlElement(name="bank_code")
	private String bankCode;
	
	@XmlElement(name="iban")
	private String IBAN;
	
	@XmlElement(name="bic")
	private String BIC;
	
	@XmlElement(name="account_name")
	private String accountName;
	
	@XmlElement(name="country")
	private CountryCode country;
	
	@XmlElement(name="account_token")
	private String accountToken;
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getBankCode() {
		return bankCode;
	}
	
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	
	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}
	
	public String getBIC() {
		return BIC;
	}
	
	public void setBIC(String BIC) {
		this.BIC = BIC;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public CountryCode getCountry() {
		return country;
	}
	
	public void setCountry(CountryCode country) {
		this.country = country;
	}
	
	public String getAccountToken() {
		return accountToken;
	}
	
	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}
	
	@Override
	public String toString() {
		return String.format("BankAccount [prefix=%s, accountNumber=%s, bankCode=%s, IBAN=%s, BIC=%s, accountName=%s, country=%s, accountToken=%s]",
						prefix, accountNumber, bankCode, IBAN, BIC, accountName, country, accountToken);
	}
}
