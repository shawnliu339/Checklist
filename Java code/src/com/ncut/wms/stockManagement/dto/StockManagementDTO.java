package com.ncut.wms.stockManagement.dto;

public class StockManagementDTO {

	/* =====总单实体属性====== */
	private String orderId;
	private Integer userId;
	private String userName;
	private String remark;
	private String createDate;
	private String outDate;
	private String returnedDate;
	private Integer stockState;
	
	/* =====详单实体属性====== */
	private Integer detailId;
	private Integer amount;
	private Integer returnedAmount;
	
	/* =====库存总单属性====== */
	private Integer totalStockId;
	private Integer commodityId;
	private String commodityName;
	private Integer purchase = 0;
	private Integer inStock = 0;
	private Integer outStock = 0;
	private Integer visibleStock = 0;
	private Integer stockAmount = 0;
	
	//页面属性
	private String stateStr;
	private String beginDate;
	private String endDate;

	// 存储详单字符串
	private String detailOrder;

	/* =====datagrid属性====== */
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;

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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getDetailOrder() {
		return detailOrder;
	}

	public void setDetailOrder(String detailOrder) {
		this.detailOrder = detailOrder;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getTotalStockId() {
		return totalStockId;
	}

	public void setTotalStockId(Integer totalStockId) {
		this.totalStockId = totalStockId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStockState() {
		return stockState;
	}

	public void setStockState(Integer stockState) {
		this.stockState = stockState;
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
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

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
}
