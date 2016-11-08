package com.ncut.wms.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.stock.dao.StockDAO;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.dto.StockDTO;
import com.ncut.wms.stock.dto.TotalStockDTO;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.model.TotalStock;
import com.ncut.wms.storage.dao.StorageDAO;
import com.ncut.wms.util.easyui.DataGrid;

@Service("stockService")
public class StockService {

	/* ======以下业务逻辑======== */
	public DataGrid<StockDTO> detailgrid(TotalStockDTO tsDTO) {
		DataGrid<StockDTO> dg = new DataGrid<StockDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Stock stock where 1=1";
		
		if(tsDTO.getCommodityId()!=null){
			hql+=" and stock.commodityId = :commodityId";
			map.put("commodityId", tsDTO.getCommodityId());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(tsDTO.getSort()!=null){
			hql+=" order by "+tsDTO.getSort()+" "+tsDTO.getOrder();
		}
		List<Stock> sList = sDAO.list(hql, map, tsDTO.getPage(), tsDTO.getRows());
		List<StockDTO> sDTOList = new ArrayList<StockDTO>();
		for(Stock s:sList){
			StockDTO stockDTO = new StockDTO();
			BeanUtils.copyProperties(s, stockDTO);
			
			stockDTO.setCommodityName(cDAO.load(stockDTO.getCommodityId()).getCommodityName());
			stockDTO.setStorageName(storageDAO.load(stockDTO.getStorageId()).getStorageName());
			
			sDTOList.add(stockDTO);
		}
		dg.setTotal(sDAO.count(totalHql, map));
		dg.setRows(sDTOList);
		return dg;
	}
	
	public DataGrid<TotalStockDTO> totalgrid(TotalStockDTO tsDTO) {
		DataGrid<TotalStockDTO> dg = new DataGrid<TotalStockDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String head = "select ts ";
		String hql = "from TotalStock ts, Commodity c where 1=1 and ts.commodityId = c.commodityId";
		if(tsDTO.getCommodityName()!=null && !"".equals(tsDTO.getCommodityName().trim())){
			hql+=" and c.commodityName like :commodityName";
			map.put("commodityName", "%"+tsDTO.getCommodityName().trim()+"%");
		}
		String totalHql = "select count(ts) "+hql;
		hql = head + hql;
		//实现排序
		if(tsDTO.getSort()!=null){
			hql+=" order by "+tsDTO.getSort()+" "+tsDTO.getOrder();
		}
		List<TotalStock> tsList = tsDAO.list(hql, map, tsDTO.getPage(), tsDTO.getRows());
		List<TotalStockDTO> tsDTOList = new ArrayList<TotalStockDTO>();
		for(TotalStock ts:tsList){
			TotalStockDTO totalStockDTO = new TotalStockDTO();
			BeanUtils.copyProperties(ts, totalStockDTO);
			
			totalStockDTO.setCommodityName(cDAO.load(totalStockDTO.getCommodityId()).getCommodityName());
			
			tsDTOList.add(totalStockDTO);
		}
		dg.setTotal(tsDAO.count(totalHql, map));
		dg.setRows(tsDTOList);
		return dg;
	}
	
	/* ======以下声明======== */
	private StockDAO sDAO;
	private TotalStockDAO tsDAO;
	private CommodityDAO cDAO;
	private StorageDAO storageDAO;

	@Resource
	public void setsDAO(StockDAO sDAO) {
		this.sDAO = sDAO;
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
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
	}

}
