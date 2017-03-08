package cz.gopay.api.v3.model.common;

import cz.gopay.api.v3.model.payment.support.ContentType;

public enum StatementGeneratingFormat {
	XLS_A(ContentType.TYPE_XLS),
	XLS_B(ContentType.TYPE_XLS),
	XLS_C(ContentType.TYPE_XLS),
	CSV_A(ContentType.TYPE_CSV),
	CSV_B(ContentType.TYPE_CSV),
	CSV_C(ContentType.TYPE_CSV),
	CSV_D(ContentType.TYPE_CSV),
	ABO_A(ContentType.TYPE_ABO);
	
	private final String contentType;
	
	private StatementGeneratingFormat(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentType() {
		return contentType;
	}
}
