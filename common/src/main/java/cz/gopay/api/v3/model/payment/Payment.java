package cz.gopay.api.v3.model.payment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.eet.EETCode;
import cz.gopay.api.v3.model.common.SessionSubState;
import cz.gopay.api.v3.model.payment.support.*;

@XmlRootElement
public class Payment {

    public enum SessionState {
        CREATED,
        PAYMENT_METHOD_CHOSEN,
        PAID,
        AUTHORIZED,
        CANCELED,
        TIMEOUTED,
        REFUNDED,
        PARTIALLY_REFUNDED
    }

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "parent_id")
    private Long parentId;

    @XmlElement(name = "order_number")
    private String orderNumber;

    @XmlElement(name = "state")
    private SessionState state;
    
    @XmlElement(name = "sub_state")
    private SessionSubState subState;

    @XmlElement(name = "payment_instrument")
    private PaymentInstrument paymentInstrument;

    @XmlElement(name = "amount")
    private Long amount;

    @XmlElement(name = "currency")
    private Currency currency;

    @XmlElement(name = "payer")
    private Payer payer;

    @XmlElement(name = "target")
    private Target target;

    @XmlElement(name = "recurrence")
    private Recurrence recurrence;

    @XmlElement(name = "preauthorization")
    private Preauthorization preAuthorization;

    @XmlElement(name = "additional_params")
    private List<AdditionalParam> additionalParams;

    @XmlElement(name = "lang")
    private String lang;

    @XmlElement(name = "gw_url")
    private String gwUrl;

    @XmlElement(name = "eet_code")
    private EETCode eetCode;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }
    
    public SessionSubState getSubState() {
        return subState;
    }
    
    public void setSubState(SessionSubState subState) {
        this.subState = subState;
    }
    
    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

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

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Preauthorization getPreAuthorization() {
        return preAuthorization;
    }

    public void setPreAuthorization(Preauthorization preAuthorization) {
        this.preAuthorization = preAuthorization;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getGwUrl() {
        return gwUrl;
    }

    public void setGwUrl(String gwUrl) {
        this.gwUrl = gwUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public EETCode getEetCode() {
        return eetCode;
    }
    
    public void setEetCode(EETCode eetCode) {
        this.eetCode = eetCode;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Payment [id=%s, parentId=%s, state=%s, paymentInstrument=%s, amount=%s, currency=%s, payer=%s, target=%s, recurrence=%s, aditionalParams=%s, preAuthorization=%s, lang=%s]",
                id, parentId, state, paymentInstrument, amount, currency, payer, target, recurrence, additionalParams,
                preAuthorization, lang);
    }

    
    
    public static Payment create(Long id, Long parentId, SessionState state) {
        Payment result = new Payment();
        result.setId(id);
        result.setParentId(parentId);
        result.setState(state);
        return result;
    }

    public Payment chosenPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
        return this;
    }

    public Payment withPrice(Long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
        return this;
    }

    public Payment identifiedBy(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public Payment withPayerParty(Payer payer) {
        this.payer = payer;
        return this;
    }

    public Payment withTargetParty(Target target) {
        this.target = target;
        return this;
    }

    public Payment withRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
        return this;
    }

    public Payment asChildOf(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Payment addAdditionalParam(String name, String value) {
        if (name == null || value == null) {
            return this;
        }
        if (additionalParams == null) {
            this.additionalParams = new ArrayList<>();
        }
        this.additionalParams.add(AdditionalParam.of(name, value));
        return this;
    }

    public Payment addAdditionalParam(AdditionalParam param) {
        if (param == null) {
            return this;
        }
        if (additionalParams == null) {
            this.additionalParams = new ArrayList<>();
        }
        this.additionalParams.add(param);
        return this;
    }

    public Payment withPreAuthorization(Preauthorization preAuthorization) {
        this.preAuthorization = preAuthorization;
        return this;
    }

    public Payment onURL(String url) {
        this.gwUrl = url;
        return this;
    }

    public Payment inLang(String lang) {
        this.lang = lang;
        return this;
    }
}
