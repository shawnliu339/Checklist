package com.ncut.wms.saleManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_detail_source")
public class SaleDetailSource {

	private Integer sdsId;
	private String stId;
	private Integer sdId;
	private Integer ssrId;
	private Integer amount;

	@Id
	@GeneratedValue
	public Integer getSdsId() {
		return sdsId;
	}

	public void setSdsId(Integer sdsId) {
		this.sdsId = sdsId;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getSsrId() {
		return ssrId;
	}

	public void setSsrId(Integer ssrId) {
		this.ssrId = ssrId;
	}

}
