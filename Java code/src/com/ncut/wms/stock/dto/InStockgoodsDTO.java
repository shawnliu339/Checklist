package com.ncut.wms.stock.dto;

public class InStockgoodsDTO {

	private Integer inStockgoodsId;
	private String inStockId;
	private Integer purchasegoodsId;
	private Integer commodityId;
	private String commodityName;
	private Double price = 0.0;
	private Integer amount = 0;
	private Integer returnedAmount = 0;
	private Double totalPrice = 0.0;
	private Integer storageId;
	private String storageName;
	private Integer shelfId;
	private String shelfName;
	private String coordinate;

	/* =====datagrid属性====== */
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;

	public Integer getInStockgoodsId() {
		return inStockgoodsId;
	}

	public void setInStockgoodsId(Integer inStockgoodsId) {
		this.inStockgoodsId = inStockgoodsId;
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

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(Integer returnedAmount) {
		this.returnedAmount = returnedAmount;
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

	public Integer getStorageId() {
		return storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

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

	public Integer getPurchasegoodsId() {
		return purchasegoodsId;
	}

	public void setPurchasegoodsId(Integer purchasegoodsId) {
		this.purchasegoodsId = purchasegoodsId;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	
}
