package cz.gopay.api.v3.model.supercash;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.gopay.api.v3.util.GPDateAdapter;

@XmlRootElement
public class SupercashCouponRequest {
	
	@XmlElement(name = "go_id")
	private Long goId;
	
	@XmlElement(name = "sub_type")
	private SubType subType;
	
	@XmlElement(name = "amount")
	private Long amount;
	
	/**
	 * ID z eshopu, pro parovani.
	 */
	@XmlElement(name = "custom_id")
	private String customId;
	
	@XmlElement(name = "order_number")
	private String orderNumber;
	
	@XmlElement(name = "order_description")
	private String orderDescription;
	
	@XmlElement(name = "buyer_email")
	private String buyerEmail;
	
	@XmlElement(name = "buyer_phone")
	private String buyerPhone;
	
	@XmlElement(name = "date_valid_to")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateValidTo;
	
	@XmlElement(name = "notification_url")
	private String notificationUrl;
	
	public Long getGoId() {
		return goId;
	}
	
	public void setGoId(Long goId) {
		this.goId = goId;
	}
	
	public SubType getSubType() {
		return subType;
	}
	
	public void setSubType(SubType subType) {
		this.subType = subType;
	}
	
	public Long getAmount() {
		return amount;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public String getCustomId() {
		return customId;
	}
	
	public void setCustomId(String customId) {
		this.customId = customId;
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
	
	public String getBuyerEmail() {
		return buyerEmail;
	}
	
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	
	public String getBuyerPhone() {
		return buyerPhone;
	}
	
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	
	public Date getDateValidTo() {
		return dateValidTo;
	}
	
	public void setDateValidTo(Date dateValidTo) {
		this.dateValidTo = dateValidTo;
	}
	
	public String getNotificationUrl() {
		return notificationUrl;
	}
	
	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}
}
