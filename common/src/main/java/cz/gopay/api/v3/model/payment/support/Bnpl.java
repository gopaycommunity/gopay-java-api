package cz.gopay.api.v3.model.payment.support;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Bnpl {
	private BnplType bnplType;
	private Map<Locale, String> label;
	private Image image;
	
	public Bnpl() {
	}
	
	public static Bnpl init(BnplType bnplType) {
		return new Bnpl(bnplType);
	}
	
	public Bnpl(BnplType bnplType) {
		this.bnplType = bnplType;
	}
	
	public BnplType getBnplType() {
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
	
	public Bnpl addLabel(String label, Locale locale) {
		
		if (this.label == null) {
			this.label = new LinkedHashMap<>();
		}
		
		this.label.put(locale, label);
		return this;
	}
	
	public Bnpl withImage(Image image) {
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
