package cz.gopay.api.v3.model.payment.support;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
public class OrderItem {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "amount")
    private Long amount;

    @XmlElement(name = "fee")
    private Long fee;

    @XmlElement(name = "count")
    private Long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("ROrderItem [name=%s, amount=%s, fee=%s, count=%s]", name, amount, fee, count);
    }

    public static OrderItem of(String name, Long amount, Long fee, Long count) {
        OrderItem result = new OrderItem();
        result.setName(name);
        result.setAmount(amount);
        result.setFee(fee);
        result.setCount(count);
        return result;
    }
}
