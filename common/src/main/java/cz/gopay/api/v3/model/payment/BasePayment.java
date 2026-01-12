package cz.gopay.api.v3.model.payment;

import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EET;
import cz.gopay.api.v3.model.payment.support.AdditionalParam;
import cz.gopay.api.v3.model.payment.support.Callback;
import cz.gopay.api.v3.model.payment.support.OrderItem;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.Recurrence;
import cz.gopay.api.v3.model.payment.support.Target;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
public class BasePayment {

    @XmlElement(name = "payer")
    private Payer payer;

    @XmlElement(name = "target")
    private Target target;

    @XmlElement(name = "amount")
    private Long amount;

    @XmlElement(name = "currency")
    private Currency currency;

    @XmlElement(name = "order_number")
    private String orderNumber;

    @XmlElement(name = "order_description")
    private String orderDescription;

    @XmlElement(name = "items")
    private List<OrderItem> items;

    @XmlElement(name = "callback")
    private Callback callback;

    @XmlElement(name = "recurrence")
    private Recurrence recurrence;

    @XmlElement(name = "preauthorization")
    private Boolean preAuthorization;

    @XmlElement(name = "additional_params")
    private List<AdditionalParam> additionalParams;

    @XmlElement(name = "lang")
    private String lang;

    @XmlElement(name = "eet")
    private EET eet;
    
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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public List<AdditionalParam> getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(List<AdditionalParam> aditionalParams) {
        this.additionalParams = aditionalParams;
    }

    public Boolean getPreAuthorization() {
        return preAuthorization;
    }

    public void setPreAuthorization(Boolean preAuthorization) {
        this.preAuthorization = preAuthorization;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    
    
    public EET getEet() {
        return eet;
    }
    
    public void setEet(EET eet) {
        this.eet = eet;
    }
    
    @Override
    public String toString() {
        return String.format(
                "BasePayment [orderNumber=%s, payer=%s, target=%s, amount=%s, currency=%s, callback=%s, recurrence=%s, preAuthorization=%s, lang=%s]",
                orderNumber, payer, target, amount, currency, callback, recurrence, preAuthorization, lang);
    }
}
