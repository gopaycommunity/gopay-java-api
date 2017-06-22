package cz.gopay.api.v3.model.supercash;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlList;

import cz.gopay.api.v3.model.payment.Payment;

@XmlRootElement
public class SupercashPayment {
	
	@XmlElement(name = "coupon")
	private SupercashCoupon coupon;
	
	@XmlElement(name = "payments")
	@XmlList
	private List<Payment> payments;
	
	public SupercashCoupon getCoupon() {
		return coupon;
	}
	
	public void setCoupon(SupercashCoupon coupon) {
		this.coupon = coupon;
	}
	
	public List<Payment> getPayments() {
		return payments;
	}
	
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public void addPayment(Payment payment) {
		if (payments == null) {
			payments = new ArrayList<>();
		}
		payments.add(payment);
	}
	
	@Override
	public String toString() {
		return "SupercashPayment{" + coupon.toString() + '}';
	}
}
