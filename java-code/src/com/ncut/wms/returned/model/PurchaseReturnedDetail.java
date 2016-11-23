package com.ncut.wms.returned.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_returned_detail")
public class PurchaseReturnedDetail {

	private Integer prdId;
	private String prtId;
	private Integer inStockgoodsId;
	private Integer commodityId;
	private Double returnedPrice;
	private Integer returnedAmount;
	private Double totalPrice;

	@Id
	@GeneratedValue
	public Integer getPrdId() {
		return prdId;
	}

	public void setPrdId(Integer prdId) {
		this.prdId = prdId;
	}

	public String getPrtId() {
		return prtId;
	}

	public void setPrtId(String prtId) {
		this.prtId = prtId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Double getReturnedPrice() {
		return returnedPrice;
	}

	public void setReturnedPrice(Double returnedPrice) {
		this.returnedPrice = returnedPrice;
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

	public Integer getInStockgoodsId() {
		return inStockgoodsId;
	}

	public void setInStockgoodsId(Integer inStockgoodsId) {
		this.inStockgoodsId = inStockgoodsId;
	}

}
