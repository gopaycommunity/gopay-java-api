package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Local bank account details (used in QR payment recipient).
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalBankAccount {

    @XmlElement(name = "prefix")
    private String prefix;

    @XmlElement(name = "account_number")
    private String accountNumber;

    @XmlElement(name = "bank_code")
    private String bankCode;

    @XmlElement(name = "variable_symbol")
    private String variableSymbol;

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

    public String getVariableSymbol() {
        return variableSymbol;
    }

    public void setVariableSymbol(String variableSymbol) {
        this.variableSymbol = variableSymbol;
    }

    @Override
    public String toString() {
        return "LocalBankAccount{prefix=" + prefix + ", accountNumber=" + accountNumber
                + ", bankCode=" + bankCode + ", variableSymbol=" + variableSymbol + "}";
    }
}
