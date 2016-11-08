package com.ncut.wms.storage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Storage {

	private Integer storageId;
	private String storageName;
	private String address;

	@Id
	@GeneratedValue
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
