package com.ncut.wms.purchase.dto;

import java.util.Arrays;

public class PurchaseDTO {

	/* ======订单属性======== */
	private String purchaseId;
	private Integer supplierId;
	private String supplierName;
	private String createDate;
	private String receivedDate;
	private Double payablePrice;
	private Double realPrice;
	private Integer state;
	private Integer stockState;
	private Integer userId;
	private String userName;
	private String remark;
	private String beginDate;
	private String endDate;

	/* ======详单属性======== */
	private String pgs;

	/* =====datagrid属性====== */
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
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

	public Integer getUserID() {
		return userId;
	}

	public void setUserID(Integer userID) {
		this.userId = userID;
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

	public String getPgs() {
		return pgs;
	}

	public void setPgs(String pgs) {
		this.pgs = pgs;
	}

	@Override
	public String toString() {
		return "PurchaseDTO [pgs=" + pgs + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStockState() {
		return stockState;
	}

	public void setStockState(Integer stockState) {
		this.stockState = stockState;
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

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

}
