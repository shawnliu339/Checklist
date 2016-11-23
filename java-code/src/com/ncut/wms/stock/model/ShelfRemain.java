package com.ncut.wms.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShelfRemain {

	private Integer shelfRemainId;
	private String orderId;
	private Integer detailId;
	private Integer commodityId;
	private Integer visibleRemain = 0;
	private Integer realRemain = 0;

	@Id
	@GeneratedValue
	public Integer getShelfRemainId() {
		return shelfRemainId;
	}

	public void setShelfRemainId(Integer shelfRemainId) {
		this.shelfRemainId = shelfRemainId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getVisibleRemain() {
		return visibleRemain;
	}

	public void setVisibleRemain(Integer visibleRemain) {
		this.visibleRemain = visibleRemain;
	}

	public Integer getRealRemain() {
		return realRemain;
	}

	public void setRealRemain(Integer realRemain) {
		this.realRemain = realRemain;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

}
