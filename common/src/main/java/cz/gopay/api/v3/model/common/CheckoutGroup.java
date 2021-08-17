package cz.gopay.api.v3.model.common;

import cz.gopay.api.v3.model.payment.support.PaymentInstrument;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum CheckoutGroup {
	
	@XmlEnumValue("card-payment")
	CARD_PAYMENT("card-payment", EnumSet.of(PaymentInstrument.PAYMENT_CARD)),
	
	@XmlEnumValue("bank-transfer")
	BANK_TRANSFER("bank-transfer", EnumSet.of(PaymentInstrument.BANK_ACCOUNT)),
	
	@XmlEnumValue("wallet")
	WALLET("wallet", EnumSet.of(
			PaymentInstrument.GOPAY,
			PaymentInstrument.BITCOIN,
			PaymentInstrument.PAYPAL)),
	
	@XmlEnumValue("others")
	OTHERS("others", EnumSet.of(
			PaymentInstrument.PRSMS,
			PaymentInstrument.MPAYMENT,
			PaymentInstrument.PAYSAFECARD,
			PaymentInstrument.MASTERPASS))
	;
	
	private final String name;
	private final Set<PaymentInstrument> paymentInstruments;
	
	private CheckoutGroup(String name, Set<PaymentInstrument> paymentInstruments) {
		this.name = name;
		this.paymentInstruments = paymentInstruments;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<PaymentInstrument> getPaymentInstruments() {
		return paymentInstruments;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static CheckoutGroup findByInstrument(PaymentInstrument instrument) {
		
		for (CheckoutGroup group : CheckoutGroup.values()) {
			
			if (group.getPaymentInstruments().contains(instrument)) {
				return group;
			}
		}
		return null;
	}
	
	public static CheckoutGroup getByStringName(String stringName) {
		CheckoutGroup result = null;
		for (CheckoutGroup item : EnumSet.allOf(CheckoutGroup.class)) {
			if (String.valueOf(item).equals(stringName)) {
				result = item;
				break;
			}
		}
		return result;
	}
	
}
