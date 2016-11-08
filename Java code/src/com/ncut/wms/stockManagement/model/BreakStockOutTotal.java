package com.ncut.wms.stockManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "break_stockout_total")
public class BreakStockOutTotal {

	private String botId;
	private String btId;
	private String createDate;
	private String outDate;
	private Integer userId;
	private String remark;

	@Id
	public String getBotId() {
		return botId;
	}

	public void setBotId(String botId) {
		this.botId = botId;
	}

	public String getBtId() {
		return btId;
	}

	public void setBtId(String btId) {
		this.btId = btId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
