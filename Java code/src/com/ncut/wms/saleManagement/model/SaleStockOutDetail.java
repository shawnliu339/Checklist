package com.ncut.wms.saleManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_stockout_detail")
public class SaleStockOutDetail {

	private Integer sodId;
	private String sotId;
	private Integer sdId;

	@Id
	@GeneratedValue
	public Integer getSodId() {
		return sodId;
	}

	public void setSodId(Integer sodId) {
		this.sodId = sodId;
	}

	public String getSotId() {
		return sotId;
	}

	public void setSotId(String sotId) {
		this.sotId = sotId;
	}

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}

}
