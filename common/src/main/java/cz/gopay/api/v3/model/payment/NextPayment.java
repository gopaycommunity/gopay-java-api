package cz.gopay.api.v3.model.payment;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.payment.support.AdditionalParam;
import cz.gopay.api.v3.model.eet.EET;
import cz.gopay.api.v3.model.payment.support.OrderItem;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
public class NextPayment {

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

    @XmlElement(name = "additional_params")
    private List<AdditionalParam> additionalParams;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public List<AdditionalParam> getAdditionalParams() {
        return additionalParams;
    }
    
    public EET getEet() {
        return eet;
    }
    
    public void setEet(EET eet) {
        this.eet = eet;
    }
    
    public void setAdditionalParams(List<AdditionalParam> additionalParams) {
        this.additionalParams = additionalParams;
    }

    @Override
    public String toString() {
        return String.format("CreateNextPayment [amount=%s, currency=%s, orderNumber=%s, orderDescription=%s]",
                amount, currency, orderNumber,
                orderDescription);
    }

    public static NextPayment create() {
        NextPayment result = new NextPayment();
        return result;
    }
    
}
