package com.ncut.wms.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TotalStock {

	private Integer totalStockId;
	private Integer commodityId;
	private Integer purchase = 0;
	private Integer inStock = 0;
	private Integer outStock = 0;
	private Integer visibleStock = 0;
	private Integer stockAmount = 0;

	@Id
	@GeneratedValue
	public Integer getTotalStockId() {
		return totalStockId;
	}

	public void setTotalStockId(Integer totalStockId) {
		this.totalStockId = totalStockId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getPurchase() {
		return purchase;
	}

	public void setPurchase(Integer purchase) {
		this.purchase = purchase;
	}

	public Integer getInStock() {
		return inStock;
	}

	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

	public Integer getOutStock() {
		return outStock;
	}

	public void setOutStock(Integer outStock) {
		this.outStock = outStock;
	}

	public Integer getVisibleStock() {
		return visibleStock;
	}

	public void setVisibleStock(Integer visibleStock) {
		this.visibleStock = visibleStock;
	}

	public Integer getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(Integer stockAmount) {
		this.stockAmount = stockAmount;
	}
}
