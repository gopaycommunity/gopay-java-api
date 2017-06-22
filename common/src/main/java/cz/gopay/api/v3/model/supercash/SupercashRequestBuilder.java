package cz.gopay.api.v3.model.supercash;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupercashRequestBuilder {
	
	private Long goId;
	private SubType subType;
	private Long amount;
	private String customId;
	private String orderNumber;
	private String orderDescription;
	private String buyerEmail;
	private String buyerPhone;
	private Date dateValidTo;
	private String notificationUrl;
	
	private List<Long> amounts = new ArrayList<>();
	
	private String batchCompletedNotificationUrl;
	private SupercashBatchItem defaults;
	private List<SupercashBatchItem> coupons = new ArrayList<>();
	
	public SupercashCouponRequest buildCouponRequest() {
		SupercashCouponRequest couponRequest = new SupercashCouponRequest();
		couponRequest.setGoId(goId);
		couponRequest.setSubType(subType);
		couponRequest.setAmount(amount);
		couponRequest.setCustomId(customId);
		couponRequest.setOrderNumber(orderNumber);
		couponRequest.setOrderDescription(orderDescription);
		couponRequest.setBuyerEmail(buyerEmail);
		couponRequest.setBuyerPhone(buyerPhone);
		couponRequest.setDateValidTo(dateValidTo);
		couponRequest.setNotificationUrl(notificationUrl);
		return couponRequest;
	}
	
	public SupercashBatchItem buildBatchItem() {
		SupercashBatchItem batchItem = new SupercashBatchItem();
		batchItem.setSubType(subType);
		batchItem.setAmounts(amounts);
		batchItem.setCustomId(customId);
		batchItem.setOrderNumber(orderNumber);
		batchItem.setOrderDescription(orderDescription);
		batchItem.setBuyerEmail(buyerEmail);
		batchItem.setBuyerPhone(buyerPhone);
		batchItem.setDateValidTo(dateValidTo);
		batchItem.setNotificationUrl(notificationUrl);
		return batchItem;
	}
	
	public SupercashBatchRequest buildBatchRequest() {
		SupercashBatchRequest batchRequest = new SupercashBatchRequest();
		batchRequest.setGoId(goId);
		batchRequest.setBatchCompletedNotificationUrl(batchCompletedNotificationUrl);
		batchRequest.setDefaults(defaults);
		batchRequest.setCoupons(coupons);
		return batchRequest;
	}
	
	public SupercashRequestBuilder forGoId(Long goId) {
		this.goId = goId;
		return this;
	}
	
	public SupercashRequestBuilder subType(SubType subType) {
		this.subType = subType;
		return this;
	}
	
	public SupercashRequestBuilder withAmount(Long amount) {
		this.amount = amount;
		return this;
	}
	
	public SupercashRequestBuilder withAmounts(List<Long> amounts) {
		this.amounts = amounts;
		return this;
	}
	
	public SupercashRequestBuilder addAmount(Long amount) {
		this.amounts.add(amount);
		return this;
	}
	
	public SupercashRequestBuilder customId(String customId) {
		this.customId = customId;
		return this;
	}
	
	public SupercashRequestBuilder orderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}
	
	public SupercashRequestBuilder orderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
		return this;
	}
	
	public SupercashRequestBuilder withBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
		return this;
	}
	
	public SupercashRequestBuilder withBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
		return this;
	}
	
	public SupercashRequestBuilder validTo(Date dateValidTo) {
		this.dateValidTo = dateValidTo;
		return this;
	}
	
	public SupercashRequestBuilder notificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
		return this;
	}
	
	public SupercashRequestBuilder batchNotificationUrl(String batchNotificationUrl) {
		this.batchCompletedNotificationUrl = batchNotificationUrl;
		return this;
	}
	
	public SupercashRequestBuilder withDefaults(SupercashBatchItem defaults) {
		this.defaults = defaults;
		return this;
	}
	
	public SupercashRequestBuilder withCoupons(List<SupercashBatchItem> coupons) {
		this.coupons = coupons;
		return this;
	}
	
	public SupercashRequestBuilder addCoupon(SupercashBatchItem coupon){
		this.coupons.add(coupon);
		return this;
	}
	
	public SupercashRequestBuilder reset(){
		this.goId = null;
		this.subType = null;
		this.amount = null;
		this.customId = null;
		this.orderNumber = null;
		this.orderDescription = null;
		this.buyerEmail = null;
		this.buyerPhone = null;
		this.dateValidTo = null;
		this.notificationUrl = null;
		this.amounts = new ArrayList<Long>();
		this.batchCompletedNotificationUrl = null;
		this.defaults = null;
		this.coupons = new ArrayList<SupercashBatchItem>();
		return this;
	}
	
}
