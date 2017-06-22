package cz.gopay.api.v3.model.supercash;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SupercashBatchResult {
	
	@XmlElement(name = "batch_request_id")
	private Long batchRequestId;
	
	public Long getBatchRequestId() {
		return batchRequestId;
	}
	
	public void setBatchRequestId(Long batchRequestId) {
		this.batchRequestId = batchRequestId;
	}
	
	@Override
	public String toString() {
		return "SupercashBatchResult{" +
				"batchRequestId=" + batchRequestId +
				'}';
	}
}
