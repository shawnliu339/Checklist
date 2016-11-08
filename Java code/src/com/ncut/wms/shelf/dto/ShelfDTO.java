package com.ncut.wms.shelf.dto;

public class ShelfDTO {

	private Integer shelfId;
	private String shelfName;
	private String shelfCoordinate;

	/* =====datagrid属性====== */
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;

	public Integer getShelfId() {
		return shelfId;
	}

	public void setShelfId(Integer shelfId) {
		this.shelfId = shelfId;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getShelfCoordinate() {
		return shelfCoordinate;
	}

	public void setShelfCoordinate(String shelfCoordinate) {
		this.shelfCoordinate = shelfCoordinate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
