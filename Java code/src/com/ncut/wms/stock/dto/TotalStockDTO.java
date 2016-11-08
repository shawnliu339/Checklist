package com.ncut.wms.stock.dto;

public class TotalStockDTO {

	private Integer totalStockId;
	private Integer commodityId;
	private String commodityName;
	private Integer purchase = 0;
	private Integer inStock = 0;
	private Integer outStock = 0;
	private Integer visibleStock = 0;
	private Integer stockAmount = 0;
	
	/* =====datagrid属性====== */
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;

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

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
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
