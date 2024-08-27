package cz.gopay.api.v3.model.payment.support;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class BnplType {
	private String bnplType;
	private Map<Locale, String> label;
	private Image image;
	
	public BnplType() {
	
	}
	
	public static BnplType init(String swift) {
		return new BnplType(swift);
	}
	
	public BnplType(String bnplType) {
		this.bnplType = bnplType;
	}
	
	public String getBnplType() {
		return bnplType;
	}
	
	public Map<Locale, String> getLabel() {
		return label;
	}
	
	public void setLabel(Map<Locale, String> label) {
		this.label = label;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public BnplType addLabel(String label, Locale locale) {
		
		if (this.label == null) {
			this.label = new LinkedHashMap<>();
		}
		
		this.label.put(locale, label);
		return this;
	}
	
	public BnplType withImage(Image image) {
		this.image = image;
		return this;
	}
	
	@Override
	public String toString() {
		return "BnplType{" +
				"bnplType='" + bnplType + '\'' +
				", label=" + label +
				", image=" + image +
				'}';
	}
}
