package cz.gopay.api.v3.model.payment.support;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Swift {
	
	private String swift;
	private Map<Locale, String> label;
	private Image image;
	private Boolean isOnline;
	
	
	private Swift(){
	}
	
	private Swift(String swift) {
		this.swift = swift;
	}
	
	public static Swift init(String swift) {
		return new Swift(swift);
	}
	
	public String getSwift() {
		return swift;
	}
	
	public Map<Locale, String> getLabel() {
		return label;
	}
	
	public Swift addLabel(String label, Locale locale) {
		
		if (this.label == null) {
			this.label = new LinkedHashMap<>();
		}
		
		this.label.put(locale, label);
		return this;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Swift withImage(Image image) {
		this.image = image;
		return this;
	}
	
	public Boolean getIsOnline() {
		return isOnline;
	}
	
	public Swift withIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
		return this;
	}
	
}
