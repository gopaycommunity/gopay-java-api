package cz.gopay.api.v3.model.payment;

import cz.gopay.api.v3.model.eet.EET;
import cz.gopay.api.v3.model.payment.support.OrderItem;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by František Sichinger on 1.2.17.
 */
@XmlRootElement
public class RefundPayment {
	
	@XmlElement(name = "amount")
	private Long amount;
	
	@XmlElement(name = "items")
	private List<OrderItem> items;
	
	@XmlElement(name = "eet")
	private EET eet;
	
	public Long getAmount() {
		return amount;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}
	
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public EET getEet() {
		return eet;
	}
	
	public void setEet(EET eet) {
		this.eet = eet;
	}
}
