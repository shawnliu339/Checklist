package com.ncut.wms.stockManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "break_stockout_detail")
public class BreakStockOutDetail {

	private Integer bodId;
	private String botId;
	private Integer bdId;

	@Id
	@GeneratedValue
	public Integer getBodId() {
		return bodId;
	}

	public void setBodId(Integer bodId) {
		this.bodId = bodId;
	}

	public String getBotId() {
		return botId;
	}

	public void setBotId(String botId) {
		this.botId = botId;
	}

	public Integer getBdId() {
		return bdId;
	}

	public void setBdId(Integer bdId) {
		this.bdId = bdId;
	}

}
