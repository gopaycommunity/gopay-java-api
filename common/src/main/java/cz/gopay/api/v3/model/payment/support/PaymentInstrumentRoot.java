package cz.gopay.api.v3.model.payment.support;

import cz.gopay.api.v3.model.common.CheckoutGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentInstrumentRoot {
	
	@XmlElement(name = "groups")
	private Map<CheckoutGroup, Group> groups;
	
	@XmlElement(name = "enabledPaymentInstruments")
	private List<EnabledPaymentInstrument> enabledPaymentInstruments;
	
	public Map<CheckoutGroup, Group> getGroups() {
		return groups;
	}
	
	public PaymentInstrumentRoot addGroup(CheckoutGroup coGroup, Group groupData) {
		
		if (this.groups == null) {
			this.groups = new LinkedHashMap<>();
		}
		
		this.groups.put(coGroup, groupData);
		return this;
	}
	
	public List<EnabledPaymentInstrument> getEnabledPaymentInstruments() {
		return enabledPaymentInstruments;
	}
	
	public PaymentInstrumentRoot addEnabledPaymentInstrument(EnabledPaymentInstrument instrument) {
		
		if (this.enabledPaymentInstruments == null) {
			this.enabledPaymentInstruments = new ArrayList<>();
		}
		
		this.enabledPaymentInstruments.add(instrument);
		return this;
	}
	
}
