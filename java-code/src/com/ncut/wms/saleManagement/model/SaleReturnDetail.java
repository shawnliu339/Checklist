package com.ncut.wms.saleManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_return_detail")
public class SaleReturnDetail {

	private Integer srdId;
	private String srtId;
	private Integer sdId;
	private Double price;
	private Integer returnedAmount;
	private Double totalPrice;

	@Id
	@GeneratedValue
	public Integer getSrdId() {
		return srdId;
	}

	public void setSrdId(Integer srdId) {
		this.srdId = srdId;
	}

	public String getSrtId() {
		return srtId;
	}

	public void setSrtId(String srtId) {
		this.srtId = srtId;
	}

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(Integer returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
