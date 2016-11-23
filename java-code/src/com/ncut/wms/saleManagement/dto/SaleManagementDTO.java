package com.ncut.wms.saleManagement.dto;

public class SaleManagementDTO {

	/* =====销售总单实体属性====== */
	private String orderId;
	private Integer clientId;
	private String createDate;
	private String sendDate;
	private Double payablePrice;
	private Double realPrice;
	private Integer payState;
	private Integer stockState = 0;
	private Integer userId;
	private String remark;

	/* =====退货总单实体属性====== */
	private String returnedDate;
	private String receivedDate;
	private Double returnedPrice;

	/* =====详单实体属性====== */
	private Integer detailId;
	private Integer commodityId;
	private Double price;
	private Integer amount;
	private Double totalPrice;
	private Integer returnedAmount;
	private Integer storageId;
	private Integer shelfId;

	/* =====页面展示属性====== */
	private String beginDate;
	private String endDate;
	private Double payablePrice1;
	private Double payablePrice2;
	private Double realPrice1;
	private Double realPrice2;
	private String userName;
	private String clientName;
	// 以下商品信息
	private String commodityName;
	private String commodityType;
	private String categoryName;
	private String unitName;
	private Double salePrice;
	private Double vip1Price;
	private Double vip2Price;
	private Double vip3Price;
	//仓库货架信息
	private String storageName;
	private String shelfName;

	private Integer visibleStock = 0;
	private Integer visibleRemain = 0;
	
	private String stateStr;

	// 存储详单字符串
	private String detailOrder;

	/* =====datagrid属性====== */
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
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

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public Integer getStockState() {
		return stockState;
	}

	public void setStockState(Integer stockState) {
		this.stockState = stockState;
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

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(Integer returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getDetailOrder() {
		return detailOrder;
	}

	public void setDetailOrder(String detailOrder) {
		this.detailOrder = detailOrder;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getVip1Price() {
		return vip1Price;
	}

	public void setVip1Price(Double vip1Price) {
		this.vip1Price = vip1Price;
	}

	public Double getVip2Price() {
		return vip2Price;
	}

	public void setVip2Price(Double vip2Price) {
		this.vip2Price = vip2Price;
	}

	public Double getVip3Price() {
		return vip3Price;
	}

	public void setVip3Price(Double vip3Price) {
		this.vip3Price = vip3Price;
	}

	public Integer getVisibleStock() {
		return visibleStock;
	}

	public void setVisibleStock(Integer visibleStock) {
		this.visibleStock = visibleStock;
	}

	public Double getReturnedPrice() {
		return returnedPrice;
	}

	public void setReturnedPrice(Double returnedPrice) {
		this.returnedPrice = returnedPrice;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public Integer getVisibleRemain() {
		return visibleRemain;
	}

	public void setVisibleRemain(Integer visibleRemain) {
		this.visibleRemain = visibleRemain;
	}

	public Integer getStorageId() {
		return storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public Integer getShelfId() {
		return shelfId;
	}

	public void setShelfId(Integer shelfId) {
		this.shelfId = shelfId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public Double getPayablePrice1() {
		return payablePrice1;
	}

	public void setPayablePrice1(Double payablePrice1) {
		this.payablePrice1 = payablePrice1;
	}

	public Double getPayablePrice2() {
		return payablePrice2;
	}

	public void setPayablePrice2(Double payablePrice2) {
		this.payablePrice2 = payablePrice2;
	}

	public Double getRealPrice1() {
		return realPrice1;
	}

	public void setRealPrice1(Double realPrice1) {
		this.realPrice1 = realPrice1;
	}

	public Double getRealPrice2() {
		return realPrice2;
	}

	public void setRealPrice2(Double realPrice2) {
		this.realPrice2 = realPrice2;
	}
}
