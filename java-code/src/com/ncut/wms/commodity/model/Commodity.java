package com.ncut.wms.commodity.model;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.context.annotation.Lazy;

import com.ncut.wms.unit.model.Unit;

@Entity
public class Commodity {

	private Integer commodityId;
	private String commodityName;
	private String commodityType;
	private Integer categoryId;
	private Integer unitId;
	private Double salePrice;
	private Double vip1Price;
	private Double vip2Price;
	private Double vip3Price;
	private Integer minimum;
	private Integer state;
	private String remark;
	private String coordinate;

	@Id
	@GeneratedValue
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

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

	public Integer getMinimum() {
		return minimum;
	}

	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

}
