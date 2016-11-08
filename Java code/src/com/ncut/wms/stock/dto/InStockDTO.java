package com.ncut.wms.stock.dto;

public class InStockDTO {

	/* ======订单属性======== */
	private String inStockId;
	private String purchaseId;
	private Integer supplierId;
	private String supplierName;
	private String createDate;
	private Double payablePrice = 0.0;
	private Double realPrice = 0.0;
	private Integer state = 0;
	private Integer userId;
	private String userName;
	private String remark;
	
	/* ======页面展示属性======== */
	private String beginDate;
	private String endDate;

	/* ======详单属性======== */
	private String inStockgoods;

	/* =====datagrid属性====== */
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;

	public String getInStockId() {
		return inStockId;
	}

	public void setInStockId(String inStockId) {
		this.inStockId = inStockId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Double getPayablePrice() {
		return payablePrice;
	}

	public void setPayablePrice(Double payablePrice) {
		this.payablePrice = payablePrice;
	}

	public Double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getInStockgoods() {
		return inStockgoods;
	}

	public void setInStockgoods(String inStockgoods) {
		this.inStockgoods = inStockgoods;
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

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

}
