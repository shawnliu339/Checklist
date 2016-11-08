package com.ncut.wms.purchaseManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="return_stockout_detail")
public class ReturnStockOutDetail {

	private Integer rodId;
	private String rotId;
	private Integer prdId;

	@Id
	@GeneratedValue
	public Integer getRodId() {
		return rodId;
	}

	public void setRodId(Integer rodId) {
		this.rodId = rodId;
	}

	public String getRotId() {
		return rotId;
	}

	public void setRotId(String rotId) {
		this.rotId = rotId;
	}

	public Integer getPrdId() {
		return prdId;
	}

	public void setPrdId(Integer prdId) {
		this.prdId = prdId;
	}

}
