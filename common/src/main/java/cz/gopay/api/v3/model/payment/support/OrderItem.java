package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author Zbynek Novak novak.zbynek@gmail.com
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItem {
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "amount")
	private Long amount;
	
	@XmlElement(name = "count")
	private Long count;
	
	@XmlElement(name = "vat_rate")
	private Integer vatRate;
	
	@XmlElement(name = "type")
	private ItemType itemType;
	
	@XmlElement(name = "product_url")
	private String productURL;
	
	@XmlElement(name = "ean")
	private String ean;
	
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
	
	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}
	
	public Integer getVatRate() {
		return vatRate;
	}
	
	public void setVatRate(Integer vatRate) {
		this.vatRate = vatRate;
	}
	
	public ItemType getItemType() {
		return itemType;
	}
	
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	public String getProductURL() {
		return productURL;
	}
	
	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}
	
	public String getEan() {
		return ean;
	}
	
	public void setEan(String ean) {
		this.ean = ean;
	}
	
	
	@Override
	public String toString() {
		return String.format("OrderItem [name=%s, amount=%s, count=%s, vatRate=%s, type=%s, ean=%s, url=%s]",
				name, amount, count, vatRate, itemType, ean, productURL);
	}
	
	public static OrderItem of(String name, Long amount, Long count) {
		OrderItem result = new OrderItem();
		result.setName(name);
		result.setAmount(amount);
		result.setCount(count);
		return result;
	}
	public static OrderItem of(String name, Long amount, Long count, Integer vatRate, ItemType type, String ean, String url) {
		OrderItem result = of(name, amount, count);
		result.setName(name);
		result.setAmount(amount);
		result.setCount(count);
		result.setVatRate(vatRate);
		result.setItemType(type);
		result.setEan(ean);
		result.setProductURL(url);
		return result;
	}
}
