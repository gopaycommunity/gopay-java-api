package cz.gopay.api.v3.model.supercash;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlList;

import cz.gopay.api.v3.util.GPDateAdapter;
import cz.gopay.api.v3.util.GPTimestampAdapter;

@XmlRootElement
public class SupercashCoupon {
	
	@XmlElement(name = "supercash_coupon_id")
	private Long supercashCouponId;
	
	@XmlElement(name = "custom_id")
	private String customId;
	
	@XmlElement(name = "barcode_image_url")
	private String barcodeImageUrl;
	
	@XmlElement(name = "supercash_number")
	private String supercashNumber;
	
	@XmlElement(name = "barcode")
	private String barcode;
	
	@XmlElement(name = "date_created")
	@XmlJavaTypeAdapter(GPTimestampAdapter.class)
	private Date dateCreated;
	
	@XmlElement(name = "date_valid_to")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateValidTo;
	
	@XmlElement(name = "payment_session_id_list")
	@XmlList
	private List<Long> paymentSessionIdList;
	
	public Long getSupercashCouponId() {
		return supercashCouponId;
	}
	
	public void setSupercashCouponId(Long supercashCouponId) {
		this.supercashCouponId = supercashCouponId;
	}
	
	public String getCustomId() {
		return customId;
	}
	
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	
	public String getBarcodeImageUrl() {
		return barcodeImageUrl;
	}
	
	public void setBarcodeImageUrl(String barcodeImageUrl) {
		this.barcodeImageUrl = barcodeImageUrl;
	}
	
	public String getSupercashNumber() {
		return supercashNumber;
	}
	
	public void setSupercashNumber(String supercashNumber) {
		this.supercashNumber = supercashNumber;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getDateValidTo() {
		return dateValidTo;
	}
	
	public void setDateValidTo(Date dateValidTo) {
		this.dateValidTo = dateValidTo;
	}
	
	public List<Long> getPaymentSessionIdList() {
		return paymentSessionIdList;
	}
	
	public void setPaymentSessionIdList(List<Long> paymentSessionIdList) {
		this.paymentSessionIdList = paymentSessionIdList;
	}
	
	@Override
	public String toString() {
		return "SupercashCoupon{\n" +
				" supercashCouponId=" + supercashCouponId + ",\n"+
				" barcodeImageUrl='" + barcodeImageUrl + '\'' + ",\n" +
				" supercashNumber='" + supercashNumber + '\'' + ",\n" +
				" barcode='" + barcode + '\'' + ",\n" +
				" paymentSessionIdList=" + paymentSessionIdList + "\n" +
				'}';
	}
}
