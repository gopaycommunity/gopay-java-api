package cz.gopay.api.v3.model.payment;

import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.common.RefundState;
import cz.gopay.api.v3.util.GPDateAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Refund {
	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "state")
	private RefundState refundState;
	
	@XmlElement(name = "amount")
	private Long amount;
	
	@XmlElement(name = "currency")
	private Currency currency;
	
	@XmlElement(name = "date_requested")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateRequested;
	
	@XmlElement(name = "date_last_change")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateLastChange;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public RefundState getRefundState() {
		return refundState;
	}
	
	public void setRefundState(RefundState refundState) {
		this.refundState = refundState;
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
	
	public Date getDateRequested() {
		return dateRequested;
	}
	
	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}
	
	public Date getDateLastChange() {
		return dateLastChange;
	}
	
	public void setDateLastChange(Date dateLastChange) {
		this.dateLastChange = dateLastChange;
	}
	
	@Override
	public String toString() {
		return "Refund{" +
				"id=" + id +
				", refundState=" + refundState +
				", amount=" + amount +
				", currency=" + currency +
				", dateRequested=" + dateRequested +
				", dateLastChange=" + dateLastChange +
				'}';
	}
}
