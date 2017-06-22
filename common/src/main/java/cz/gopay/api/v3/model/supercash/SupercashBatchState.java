package cz.gopay.api.v3.model.supercash;

import javax.xml.bind.annotation.XmlElement;

public class SupercashBatchState {
	
	public enum SupercashState {
		COMPLETED,
		FAILED,
		RUNNING,
		CREATED,
		STOPPED,
		QUEUED
	}
	
	@XmlElement(name = "state")
	private SupercashState supercashState;
	
	@XmlElement(name = "batch_completed")
	private Boolean batchCompleted;
	
	@XmlElement(name = "items_finished")
	private Long itemsFinished;
	
	@XmlElement(name = "items_count")
	private Long itemsCount;
	
	public SupercashState getSupercashState() {
		return supercashState;
	}
	
	public void setSupercashState(SupercashState supercashState) {
		this.supercashState = supercashState;
	}
	
	public Boolean getBatchCompleted() {
		return batchCompleted;
	}
	
	public void setBatchCompleted(Boolean batchCompleted) {
		this.batchCompleted = batchCompleted;
	}
	
	public Long getItemsFinished() {
		return itemsFinished;
	}
	
	public void setItemsFinished(Long itemsFinished) {
		this.itemsFinished = itemsFinished;
	}
	
	public Long getItemsCount() {
		return itemsCount;
	}
	
	public void setItemsCount(Long itemsCount) {
		this.itemsCount = itemsCount;
	}
	
	@Override
	public String toString() {
		return "SupercashBatchState{" +
				"supercashState=" + supercashState +
				", batchCompleted=" + batchCompleted +
				", itemsFinished=" + itemsFinished +
				", itemsCount=" + itemsCount +
				'}';
	}
}
