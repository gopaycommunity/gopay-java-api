package cz.gopay.api.v3.model.supercash;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SupercashBatchRequest {
	@XmlElement(name = "go_id", required = true)
	private Long goId;
	
	@XmlElement(name = "batch_completed_notification_url")
	private String batchCompletedNotificationUrl;
	
	@XmlElement(name = "defaults")
	private SupercashBatchItem defaults;
	
	@XmlElement(name = "coupons")
	@XmlList
	private List<SupercashBatchItem> coupons;
	
	public Long getGoId() {
		return goId;
	}
	
	public void setGoId(Long goId) {
		this.goId = goId;
	}
	
	public String getBatchCompletedNotificationUrl() {
		return batchCompletedNotificationUrl;
	}
	
	public void setBatchCompletedNotificationUrl(String batchCompletedNotificationUrl) {
		this.batchCompletedNotificationUrl = batchCompletedNotificationUrl;
	}
	
	public SupercashBatchItem getDefaults() {
		return defaults;
	}
	
	public void setDefaults(SupercashBatchItem defaults) {
		this.defaults = defaults;
	}
	
	public List<SupercashBatchItem> getCoupons() {
		return coupons;
	}
	
	public void setCoupons(List<SupercashBatchItem> coupons) {
		this.coupons = coupons;
	}
}
