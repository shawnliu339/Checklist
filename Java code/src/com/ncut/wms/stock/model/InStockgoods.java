package com.ncut.wms.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InStockgoods {

	private Integer inStockgoodsId;
	private String inStockId;
	private Integer purchasegoodsId;
	private Integer commodityId;
	private Double price = 0.0;
	private Integer amount = 0;
	private Integer returnedAmount = 0;
	private Double totalPrice = 0.0;
	private Integer storageId;
	private Integer shelfId;

	@Id
	@GeneratedValue
	public Integer getInStockgoodsId() {
		return inStockgoodsId;
	}

	public void setInStockgoodsId(Integer id) {
		this.inStockgoodsId = id;
	}

	public String getInStockId() {
		return inStockId;
	}

	public void setInStockId(String inStockId) {
		this.inStockId = inStockId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public Integer getPurchasegoodsId() {
		return purchasegoodsId;
	}

	public void setPurchasegoodsId(Integer purchasegoodsId) {
		this.purchasegoodsId = purchasegoodsId;
	}


}
