package cz.gopay.api.v3.model.payment.support;

public class Image {
	
	private String normal;
	private String large;
	
	public static Image init() {
		return new Image();
	}
	
	public String getNormal() {
		return normal;
	}
	
	public Image setNormal(String normal) {
		this.normal = normal;
		return this;
	}
	
	public String getLarge() {
		return large;
	}
	
	public Image setLarge(String large) {
		this.large = large;
		return this;
	}
	
}
