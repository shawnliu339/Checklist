package com.ncut.wms.shelf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shelf {

	private Integer shelfId;
	private String shelfName;
	private String coordinate;

	@Id
	@GeneratedValue
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

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

}
