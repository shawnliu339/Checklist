package com.ncut.wms.unit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ncut.wms.commodity.model.Commodity;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class Unit {

	private Integer unitId;
	private String unitName;

	@Id
	@GeneratedValue
	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unit) {
		this.unitName = unit;
	}

	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitName=" + unitName + "]";
	}

}
