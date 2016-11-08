package com.ncut.wms.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.shelf.dao.ShelfDAO;
import com.ncut.wms.stock.dao.InStockgoodsDAO;
import com.ncut.wms.stock.dao.ShelfRemainDAO;
import com.ncut.wms.stock.dto.InStockgoodsDTO;
import com.ncut.wms.stock.dto.ShelfRemainDTO;
import com.ncut.wms.stock.model.InStockgoods;
import com.ncut.wms.stock.model.ShelfRemain;
import com.ncut.wms.storage.dao.StorageDAO;
import com.ncut.wms.util.easyui.DataGrid;

@Service("shelfRemainService")
public class ShelfRemainService {

	/* ======以下业务逻辑======== */
	public DataGrid<ShelfRemainDTO> datagrid(ShelfRemainDTO srDTO) {
		
		DataGrid<ShelfRemainDTO> dg = new DataGrid<ShelfRemainDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from ShelfRemain sr where 1=1";
		
		if(srDTO.getOrderId()!=null && !"".equals(srDTO.getOrderId().trim())){
			hql+=" and sr.orderId = :orderId";
			map.put("orderId", srDTO.getOrderId().trim());
		}
		
		if(srDTO.getDetailId()!=null){
			hql+=" and sr.detailId = :detailId";
			map.put("detailId", srDTO.getDetailId());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(srDTO.getSort()!=null){
			hql+=" order by "+srDTO.getSort()+" "+srDTO.getOrder();
		}
		List<ShelfRemain> srList = srDAO.list(hql, map, srDTO.getPage(), srDTO.getRows());
		List<ShelfRemainDTO> srDTOList = new ArrayList<ShelfRemainDTO>();
		for(ShelfRemain sr:srList){
			ShelfRemainDTO sRemainDTO = new ShelfRemainDTO();
			BeanUtils.copyProperties(sr, sRemainDTO);
			
			//插入一些需要的数据
			InStockgoods ig =  igDAO.load(sRemainDTO.getDetailId());
			InStockgoodsDTO igDTO = new InStockgoodsDTO();
			BeanUtils.copyProperties(ig, igDTO);
			igDTO.setCommodityName(cDAO.load(igDTO.getCommodityId()).getCommodityName());
			igDTO.setStorageName(storageDAO.load(igDTO.getStorageId()).getStorageName());
			igDTO.setShelfName(shelfDAO.load(igDTO.getShelfId()).getShelfName());
			BeanUtils.copyProperties(igDTO, sRemainDTO);
			
			srDTOList.add(sRemainDTO);
		}
		dg.setTotal(srDAO.count(totalHql, map));
		dg.setRows(srDTOList);
		return dg;
	}
	
public DataGrid<ShelfRemainDTO> returnGrid(ShelfRemainDTO srDTO) {
		
		DataGrid<ShelfRemainDTO> dg = new DataGrid<ShelfRemainDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from ShelfRemain sr where 1=1";
		
		if(srDTO.getOrderId()!=null && !"".equals(srDTO.getOrderId().trim())){
			hql+=" and sr.orderId = :orderId";
			map.put("orderId", srDTO.getOrderId().trim());
		}
		
		if(srDTO.getDetailId()!=null){
			hql+=" and sr.detailId = :detailId";
			map.put("detailId", srDTO.getDetailId());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(srDTO.getSort()!=null){
			hql+=" order by "+srDTO.getSort()+" "+srDTO.getOrder();
		}
		List<ShelfRemain> srList = srDAO.list(hql, map, srDTO.getPage(), srDTO.getRows());
		List<ShelfRemainDTO> srDTOList = new ArrayList<ShelfRemainDTO>();
		for(ShelfRemain sr:srList){
			ShelfRemainDTO sRemainDTO = new ShelfRemainDTO();
			BeanUtils.copyProperties(sr, sRemainDTO);
			
			//插入一些需要的数据
			InStockgoods ig =  igDAO.load(sRemainDTO.getDetailId());
			InStockgoodsDTO igDTO = new InStockgoodsDTO();
			BeanUtils.copyProperties(ig, igDTO);
			igDTO.setCommodityName(cDAO.load(igDTO.getCommodityId()).getCommodityName());
			igDTO.setStorageName(storageDAO.load(igDTO.getStorageId()).getStorageName());
			igDTO.setShelfName(shelfDAO.load(igDTO.getShelfId()).getShelfName());
			BeanUtils.copyProperties(igDTO, sRemainDTO);
			sRemainDTO.setReturnedAmount(0);
			
			srDTOList.add(sRemainDTO);
		}
		dg.setTotal(srDAO.count(totalHql, map));
		dg.setRows(srDTOList);
		return dg;
	}
	
	/* ======以下声明======== */
	private ShelfRemainDAO srDAO;
	private InStockgoodsDAO igDAO;
	private CommodityDAO cDAO;
	private StorageDAO storageDAO;
	private ShelfDAO shelfDAO;

	@Resource
	public void setSrDAO(ShelfRemainDAO srDAO) {
		this.srDAO = srDAO;
	}

	@Resource
	public void setIgDAO(InStockgoodsDAO igDAO) {
		this.igDAO = igDAO;
	}

	@Resource
	public void setcDAO(CommodityDAO cDAO) {
		this.cDAO = cDAO;
	}

	@Resource
	public void setStorageDAO(StorageDAO storageDAO) {
		this.storageDAO = storageDAO;
	}

	@Resource
	public void setShelfDAO(ShelfDAO shelfDAO) {
		this.shelfDAO = shelfDAO;
	}
}
