package cz.gopay.api.v3.model.supercash;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlList;

import cz.gopay.api.v3.util.GPDateAdapter;

@XmlRootElement
public class SupercashBatchItem {
	
	@XmlElement(name = "sub_type")
	private SubType subType;
	
	@XmlElement(name = "amounts")
	@XmlList
	private List<Long> amounts;
	
	@XmlElement(name = "buyer_email")
	private String buyerEmail;
	
	@XmlElement(name = "date_valid_to")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateValidTo;
	
	@XmlElement(name = "order_number")
	private String orderNumber;
	
	@XmlElement(name = "order_description")
	private String orderDescription;
	
	@XmlElement(name = "buyer_phone")
	private String buyerPhone;
	
	@XmlElement(name = "custom_id")
	private String customId;
	
	@XmlElement(name = "notification_url")
	private String notificationUrl;
	
	public SubType getSubType() {
		return subType;
	}
	
	public void setSubType(SubType subType) {
		this.subType = subType;
	}
	
	public List<Long> getAmounts() {
		return amounts;
	}
	
	public void setAmounts(List<Long> amounts) {
		this.amounts = amounts;
	}
	
	public String getBuyerEmail() {
		return buyerEmail;
	}
	
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	
	public Date getDateValidTo() {
		return dateValidTo;
	}
	
	public void setDateValidTo(Date dateValidTo) {
		this.dateValidTo = dateValidTo;
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
	
	public String getBuyerPhone() {
		return buyerPhone;
	}
	
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	
	public String getCustomId() {
		return customId;
	}
	
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	
	public String getNotificationUrl() {
		return notificationUrl;
	}
	
	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}
}
