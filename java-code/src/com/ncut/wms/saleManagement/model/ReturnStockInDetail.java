package com.ncut.wms.saleManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "return_stockin_detail")
public class ReturnStockInDetail {

	private Integer ridId;
	private String ritId;
	private Integer srdId;
	private Integer commodityId;
	private Integer amount = 0;
	private Integer returnedAmount = 0;
	private Integer storageId;
	private Integer shelfId;

	@Id
	@GeneratedValue
	public Integer getRidId() {
		return ridId;
	}

	public void setRidId(Integer ridId) {
		this.ridId = ridId;
	}

	public String getRitId() {
		return ritId;
	}

	public void setRitId(String ritId) {
		this.ritId = ritId;
	}

	public Integer getSrdId() {
		return srdId;
	}

	public void setSrdId(Integer srdId) {
		this.srdId = srdId;
	}

	public Integer getStorageId() {
		return storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public Integer getShelfId() {
		return shelfId;
	}

	public void setShelfId(Integer shelfId) {
		this.shelfId = shelfId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(Integer returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

}
