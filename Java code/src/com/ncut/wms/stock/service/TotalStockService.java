package com.ncut.wms.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ncut.wms.stock.dao.StockDAO;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.model.TotalStock;

@Service("totalStockService")
public class TotalStockService {

	/* ======以下业务逻辑======== */
	public void addTest(Integer id) {
		String hql = "from Stock s where s.commodityId = :cid";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cid",id);
		List<Stock> sList = sDAO.list(hql,map,1,100);
		Integer inStock = 0;
		Integer outStock = 0;
		Integer visibleStock = 0;
		Integer stockAmount = 0;
		for(Stock s:sList){
			
			inStock += s.getInStock();
			outStock += s.getOutStock();
			visibleStock += s.getVisibleStock();
			stockAmount += s.getStockAmount();
		}
		TotalStock ts = new TotalStock();
		ts.setCommodityId(id);
		ts.setInStock(inStock);
		ts.setOutStock(outStock);
		ts.setStockAmount(stockAmount);
		ts.setVisibleStock(visibleStock);
		tsDAO.add(ts);
		
	}
	
	
	/* ======以下声明======== */
	private TotalStockDAO tsDAO;
	private StockDAO sDAO;

	@Resource
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
	}

	@Resource
	public void setsDAO(StockDAO sDAO) {
		this.sDAO = sDAO;
	}
	
}
