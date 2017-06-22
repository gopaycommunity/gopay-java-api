package cz.gopay.api.v3.model.supercash;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlList;

@XmlRootElement
public class SupercashBatch {
	
	@XmlElement(name = "coupons")
	@XmlList
	private List<SupercashCoupon> coupons;
	
	public SupercashBatch() {}
	
	public SupercashBatch(List<SupercashCoupon> coupons) {
		this.coupons = coupons;
	}
	
	public List<SupercashCoupon> getCoupons() {
		return coupons;
	}
	
	public void setCoupons(List<SupercashCoupon> coupons) {
		this.coupons = coupons;
	}
	
	@Override
	public String toString() {
		String output = "";
		for (SupercashCoupon coupon : coupons) {
			output += coupon.toString() + "\n";
		}
		return "SupercashBatch{\n" + output + '}';
	}
}
