package com.ncut.wms.storage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.saleManagement.dto.SaleManagementDTO;
import com.ncut.wms.saleManagement.model.SaleStockOutTotal;
import com.ncut.wms.storage.dao.StorageDAO;
import com.ncut.wms.storage.dto.StorageDTO;
import com.ncut.wms.storage.model.Storage;
import com.ncut.wms.util.easyui.DataGrid;

@Service("storageService")
public class StorageService {
	
	/* ======以下业务逻辑======== */
	public void add(Storage storage) {
		sDAO.add(storage);
	}
	
	public void delete(int storageId) {
		sDAO.delete(storageId);
	}
	
	public void update(Storage storage) {
		sDAO.update(storage);
	}
	
	public List<Storage> getStorageList() {
		return sDAO.list("from Storage");
	}
	
	public DataGrid<StorageDTO> getTotalGrid(StorageDTO storageDTO) {
		DataGrid<StorageDTO> dg = new DataGrid<StorageDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Storage storage where 1=1";
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(storageDTO.getSort()!=null){
			hql+=" order by "+storageDTO.getSort()+" "+storageDTO.getOrder();
		}
		List<Storage> storageList = sDAO.list(hql, map, storageDTO.getPage(), storageDTO.getRows());
		List<StorageDTO> storageDTOList = new ArrayList<StorageDTO>();
		for(Storage storage : storageList){
			StorageDTO storageDTO2 = new StorageDTO();
			BeanUtils.copyProperties(storage, storageDTO2);
			
			storageDTOList.add(storageDTO2);
		}
		dg.setTotal(sDAO.count(totalHql, map));
		dg.setRows(storageDTOList);
		return dg;
	}
	
	/* ======以下声明======== */
	private StorageDAO sDAO;

	@Resource
	public void setsDAO(StorageDAO sDAO) {
		this.sDAO = sDAO;
	}

}
