package cz.gopay.api.v3.model.payment.support;

import cz.gopay.api.v3.model.common.CheckoutGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class EnabledPaymentInstrument {
	
	private PaymentInstrument paymentInstrument;
	private Map<Locale, String> label;
	private Image image;
	private String group;
	private List<Swift> enabledSwifts;
	private List<Bnpl> enabledBnplTypes;
	
	/*
	* Default constructor to enable instantiation from JSON object
	* */
	private EnabledPaymentInstrument(){
	}
	
	private EnabledPaymentInstrument(PaymentInstrument paymentInstrument) {
		this.paymentInstrument = paymentInstrument;
	}
	
	public static EnabledPaymentInstrument init(PaymentInstrument paymentInstrument) {
		return new EnabledPaymentInstrument(paymentInstrument);
	}
	
	public PaymentInstrument getPaymentInstrument() {
		return paymentInstrument;
	}
	
	public Map<Locale, String> getLabel() {
		return label;
	}
	
	public EnabledPaymentInstrument addLabel(String label, Locale locale) {
		
		if (this.label == null) {
			this.label = new LinkedHashMap<>();
		}
		
		this.label.put(locale, label);
		return this;
	}
	
	public Image getImage() {
		return image;
	}
	
	public EnabledPaymentInstrument withImage(Image image) {
		this.image = image;
		return this;
	}
	
	public String getGroup() {
		return group;
	}
	
	public EnabledPaymentInstrument withGroup(CheckoutGroup coGroup) {
		this.group = coGroup.getName();
		return this;
	}
	
	public List<Swift> getEnabledSwifts() {
		return enabledSwifts;
	}
	
	public EnabledPaymentInstrument addEnabledSwift(Swift swift) {
		
		if (this.enabledSwifts == null) {
			this.enabledSwifts = new ArrayList<>();
		}
		
		this.enabledSwifts.add(swift);
		return this;
	}
	
	public List<Bnpl> getEnabledBnplTypes() {
		return enabledBnplTypes;
	}
	
	public EnabledPaymentInstrument addEnabledBnplType(Bnpl bnplType) {
		if (this.enabledBnplTypes == null) {
			this.enabledBnplTypes = new ArrayList<>();
		}
		
		this.enabledBnplTypes.add(bnplType);
		return this;
	}
	
	public void setEnabledBnplTypes(List<Bnpl> enabledBnplTypes) {
		this.enabledBnplTypes = enabledBnplTypes;
	}
	
	@Override
	public String toString() {
		return String.format(
				"EnabledPaymentInstrument: [paymentInstrument=%s, label=%s, image=%s, group=%s, enabledSwifts=%s, enabledBnplTypes=%s]\n",
				paymentInstrument, label, image, group, enabledSwifts, enabledBnplTypes);
	}
}
