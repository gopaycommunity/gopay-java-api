package cz.gopay.api.v3.model.payment.support;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Group {
	
	private Map<Locale, String> label;
	
	public static Group init() {
		return new Group();
	}
	
	public Map<Locale, String> getLabel() {
		return label;
	}
	
	public Group addLabel(String label, Locale locale) {
		
		if (this.label == null) {
			this.label = new HashMap<>();
		}
		
		this.label.put(locale, label);
		return this;
	}
	
	public String toString(){
		String output ="";
		for (Map.Entry<Locale,String> entry : label.entrySet()){
			output += String.format("Group [locale=%s, description=%s]\n", entry.getKey().toString(), entry.getValue());
		}
		return output;
	}
}
