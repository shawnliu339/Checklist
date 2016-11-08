package com.ncut.wms.stockManagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.saleManagement.dao.ReturnStockInDetailDAO;
import com.ncut.wms.saleManagement.dao.ReturnStockInTotalDAO;
import com.ncut.wms.saleManagement.model.ReturnStockInDetail;
import com.ncut.wms.stock.dao.ShelfRemainDAO;
import com.ncut.wms.stock.dao.StockDAO;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.model.ShelfRemain;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.model.TotalStock;
import com.ncut.wms.stockManagement.dao.BreakDetailDAO;
import com.ncut.wms.stockManagement.dao.BreakStockOutTotalDAO;
import com.ncut.wms.stockManagement.dao.BreakTotalDAO;
import com.ncut.wms.stockManagement.dto.StockManagementDTO;
import com.ncut.wms.stockManagement.model.BreakDetail;
import com.ncut.wms.stockManagement.model.BreakStockOutDetail;
import com.ncut.wms.stockManagement.model.BreakStockOutTotal;
import com.ncut.wms.stockManagement.model.BreakTotal;
import com.ncut.wms.user.dao.UserDAO;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.system.Tools;

@Service("stockManagementService")
public class StockManagementService {

	/* ======以下业务逻辑======== */
	public String getReportBreakCode(String date) {
		String head = "BSTH";
		String code = date.replaceAll("-", "");
		String hql = "select max(bt.btId) from BreakTotal as bt where bt.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<BreakTotal> list = btDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	public String getReportBreakStockOutCode(String date) {
		String head = "CKBS";
		String code = date.replaceAll("-", "");
		String hql = "select max(bot.botId) from BreakStockOutTotal as bot where bot.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<BreakStockOutTotal> list = botDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	public void saveReportBeakStockOut(StockManagementDTO smDTO) {
		//对退货总单进行赋值
		BreakStockOutTotal bot = new BreakStockOutTotal();
		BeanUtils.copyProperties(smDTO, bot);
		bot.setBtId(smDTO.getOrderId());
		String date = smDTO.getCreateDate().substring(0, 10);
		String botId = this.getReportBreakStockOutCode(date);
		bot.setBotId(botId);
		
		//对退货详单进行赋值
		//对前台字符串进行转化
		JSONArray jArr = JSONArray.fromObject(smDTO.getDetailOrder());
		List<BreakStockOutDetail> bodList = new ArrayList<BreakStockOutDetail>();
		for(int i=0; i<jArr.size(); i++) {
			
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			BreakStockOutDetail bod = new BreakStockOutDetail();
			bod.setBotId(botId);
			bod.setBdId(jObj.getInt("detailId"));
				
			bodList.add(bod);
		}
		
		//对总单和详单进行存储
		botDAO.add(bot);
		BreakTotal bt = btDAO.load(bot.getBtId());
		for(BreakStockOutDetail bod : bodList) {
			bodDAO.add(bod);
			
			BreakDetail bd = bdDAO.load(bod.getBdId());
			
			//中间表的退货量进行修改
			ShelfRemain sr = srDAO.findByOrderIdAndDetailId(bt.getRitId(),bd.getRidId());
			Integer realRemain = sr.getRealRemain() - bd.getReturnedAmount();
			if(realRemain > 0) {
				sr.setRealRemain(realRemain);
				srDAO.update(sr);
			}
			if(realRemain == 0) {
				srDAO.delete(sr.getShelfRemainId());
			}
			
			//库存总单的退货量进行修改
			TotalStock ts = tsDAO.findByCommodityId(bd.getCommodityId());
			realRemain = ts.getStockAmount() - bd.getReturnedAmount();
			Integer outAmount = ts.getOutStock() + bd.getReturnedAmount();
			ts.setStockAmount(realRemain);
			ts.setOutStock(outAmount);
			tsDAO.update(ts);
			
			//库存详单的退货量进行修改
			ReturnStockInDetail rid = ridDAO.load(bd.getRidId());
			Stock stock = stockDAO.findByCommodityAndStorage(rid.getCommodityId(), rid.getStorageId());
			realRemain = stock.getStockAmount() - bd.getReturnedAmount();
			outAmount = stock.getOutStock() + bd.getReturnedAmount();
			stock.setStockAmount(realRemain);
			stock.setOutStock(outAmount);
			stockDAO.update(stock);
		}
		
		bt.setStockState(1);
		btDAO.update(bt);
	}
	
	public void saveReportBreak(StockManagementDTO smDTO) {
		//对退货总单进行赋值
		BreakTotal bt = new BreakTotal();
		BeanUtils.copyProperties(smDTO, bt);
		bt.setRitId(smDTO.getOrderId());
		String date = smDTO.getCreateDate().substring(0, 10);
		String btId = this.getReportBreakCode(date);
		bt.setBtId(btId);
		
		//对退货详单进行赋值
		//对前台字符串进行转化
		JSONArray jArr = JSONArray.fromObject(smDTO.getDetailOrder());
		List<BreakDetail> bdList = new ArrayList<BreakDetail>();
		for(int i=0; i<jArr.size(); i++) {
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			Integer returnedAmount = jObj.getInt("returnedAmount");
			if(returnedAmount != 0) {
				Integer ridId = jObj.getInt("detailId");
				Integer commodityId = jObj.getInt("commodityId");
				
				BreakDetail bd = new BreakDetail();
				bd.setBtId(btId);
				bd.setRidId(ridId);
				bd.setCommodityId(commodityId);
				bd.setReturnedAmount(returnedAmount);
				
				bdList.add(bd);
			}
		}
		
		//对总单和详单进行存储
		btDAO.add(bt);
		for(BreakDetail bd : bdList) {
			bdDAO.add(bd);
			
			//入库单的退货量进行修改
			ReturnStockInDetail rid = ridDAO.load(bd.getRidId());
			Integer returnedAmount = rid.getReturnedAmount() + bd.getReturnedAmount();
			rid.setReturnedAmount(returnedAmount);
			ridDAO.update(rid);
			
			//中间表的退货量进行修改
			ShelfRemain sr = srDAO.findByOrderIdAndDetailId(bt.getRitId(),bd.getRidId());
			Integer visibleStock = sr.getVisibleRemain() - bd.getReturnedAmount();
			sr.setVisibleRemain(visibleStock);
			srDAO.update(sr);
			
			//库存总单的退货量进行修改
			TotalStock ts = tsDAO.findByCommodityId(bd.getCommodityId());
			visibleStock = ts.getVisibleStock() - bd.getReturnedAmount();
			ts.setVisibleStock(visibleStock);
			tsDAO.update(ts);
			
			//库存详单的退货量进行修改
			Stock stock = stockDAO.findByCommodityAndStorage(rid.getCommodityId(), rid.getStorageId());
			visibleStock = stock.getVisibleStock() - bd.getReturnedAmount();
			stock.setVisibleStock(visibleStock);
			stockDAO.update(stock);
		}
		
	}
	
	public DataGrid<StockManagementDTO> getStockWarningTotalGrid(StockManagementDTO smDTO) {
		DataGrid<StockManagementDTO> dg = new DataGrid<StockManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String head = "select ts ";
		String hql = "from TotalStock ts, Commodity c where 1=1 and ts.commodityId = c.commodityId and ts.stockAmount < c.minimum";
		if(smDTO.getCommodityName()!=null && !"".equals(smDTO.getCommodityName().trim())){
			hql+=" and c.commodityName like :commodityName";
			map.put("commodityName", "%"+smDTO.getCommodityName().trim()+"%");
		}
		String totalHql = "select count(ts) "+hql;
		hql = head + hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<TotalStock> tsList = tsDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<StockManagementDTO> tsDTOList = new ArrayList<StockManagementDTO>();
		for(TotalStock ts:tsList){
			StockManagementDTO totalStockDTO = new StockManagementDTO();
			BeanUtils.copyProperties(ts, totalStockDTO);
			
			totalStockDTO.setCommodityName(commodityDAO.load(totalStockDTO.getCommodityId()).getCommodityName());
			
			tsDTOList.add(totalStockDTO);
		}
		dg.setTotal(tsDAO.count(totalHql, map));
		dg.setRows(tsDTOList);
		return dg;
	}
	
	public DataGrid<StockManagementDTO> getBreakTotalGrid(StockManagementDTO smDTO) {
		DataGrid<StockManagementDTO> dg = new DataGrid<StockManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from BreakTotal bt where 1=1";
		
		if(smDTO.getStateStr() != null && !"".equals(smDTO.getStateStr().trim())){
			hql+=" and bt.stockState " + smDTO.getStateStr();
		}
		
		if(smDTO.getBeginDate()!=null && !"".equals(smDTO.getBeginDate().trim())){
			hql+=" and bt.createDate between :beginDate and :endDate";
			map.put("beginDate", smDTO.getBeginDate().trim());
			map.put("endDate", smDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<BreakTotal> btList = btDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<StockManagementDTO> btDTOList = new ArrayList<StockManagementDTO>();
		for(BreakTotal bt : btList){
			StockManagementDTO btDTO = new StockManagementDTO();
			BeanUtils.copyProperties(bt, btDTO);
			btDTO.setOrderId(bt.getBtId());
			
			//插入一些需要的数据
			btDTO.setUserName(userDAO.load(bt.getUserId()).getUsername());
			
			btDTOList.add(btDTO);
		}
		dg.setTotal(btDAO.count(totalHql, map));
		dg.setRows(btDTOList);
		return dg;
	}
	
	public DataGrid<StockManagementDTO> getBreakDetailGrid(StockManagementDTO smDTO) {
		DataGrid<StockManagementDTO> dg = new DataGrid<StockManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from BreakDetail bd where 1=1";
		
		if(smDTO.getOrderId()!=null && !"".equals(smDTO.getOrderId().trim())){
			hql+=" and bd.btId = :btId";
			map.put("btId", smDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<BreakDetail> bdList = bdDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<StockManagementDTO> bdDTOList = new ArrayList<StockManagementDTO>();
		for(BreakDetail bd : bdList){
			StockManagementDTO bdDTO = new StockManagementDTO();
			BeanUtils.copyProperties(bd, bdDTO);
			bdDTO.setDetailId(bd.getBdId());
			bdDTO.setOrderId(bd.getBtId());
			
			//插入一些需要的数据
			bdDTO.setCommodityName(commodityDAO.load(bd.getCommodityId()).getCommodityName());
			
			bdDTOList.add(bdDTO);
		}
		dg.setTotal(bdDAO.count(totalHql, map));
		dg.setRows(bdDTOList);
		return dg;
	}
	
	public DataGrid<StockManagementDTO> getBreakStockOutTotalGrid(
			StockManagementDTO smDTO) {
		DataGrid<StockManagementDTO> dg = new DataGrid<StockManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from BreakStockOutTotal bot where 1=1";
		
		if(smDTO.getBeginDate()!=null && !"".equals(smDTO.getBeginDate().trim())){
			hql+=" and bot.createDate between :beginDate and :endDate";
			map.put("beginDate", smDTO.getBeginDate().trim());
			map.put("endDate", smDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<BreakStockOutTotal> botList = botDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<StockManagementDTO> botDTOList = new ArrayList<StockManagementDTO>();
		for(BreakStockOutTotal bot : botList){
			StockManagementDTO botDTO = new StockManagementDTO();
			BeanUtils.copyProperties(bot, botDTO);
			botDTO.setOrderId(bot.getBotId());
			
			//插入一些需要的数据
			botDTO.setUserName(userDAO.load(bot.getUserId()).getUsername());
			
			botDTOList.add(botDTO);
		}
		dg.setTotal(botDAO.count(totalHql, map));
		dg.setRows(botDTOList);
		return dg;
	}
	
	public DataGrid<StockManagementDTO> getBreakStockOutDetailGrid(StockManagementDTO smDTO) {
		DataGrid<StockManagementDTO> dg = new DataGrid<StockManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from BreakStockOutDetail bod where 1=1";
		
		if(smDTO.getOrderId()!=null && !"".equals(smDTO.getOrderId().trim())){
			hql+=" and bod.botId = :botId";
			map.put("botId", smDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<BreakStockOutDetail> bodList = bodDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<StockManagementDTO> bodDTOList = new ArrayList<StockManagementDTO>();
		for(BreakStockOutDetail bod : bodList){
			StockManagementDTO bodDTO = new StockManagementDTO();
			BeanUtils.copyProperties(bod, bodDTO);
			bodDTO.setDetailId(bod.getBodId());
			bodDTO.setOrderId(bod.getBotId());
			
			//插入一些需要的数据
			BreakDetail bd = bdDAO.load(bod.getBdId());
			BeanUtils.copyProperties(bd, bodDTO);
			bodDTO.setCommodityName(commodityDAO.load(bd.getCommodityId()).getCommodityName());
			
			bodDTOList.add(bodDTO);
		}
		dg.setTotal(bodDAO.count(totalHql, map));
		dg.setRows(bodDTOList);
		return dg;
	}
	
	/* ======以下声明======== */
	private BreakTotalDAO btDAO;
	private BreakDetailDAO bdDAO;
	private BreakStockOutTotalDAO botDAO;
	private BreakStockOutDetailDAO bodDAO;
	
	private ReturnStockInTotalDAO ritDAO;
	private ReturnStockInDetailDAO ridDAO;
	
	private ShelfRemainDAO srDAO;
	private TotalStockDAO tsDAO;
	private StockDAO stockDAO;
	
	private UserDAO userDAO;
	private CommodityDAO commodityDAO;

	@Resource
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
	}

	@Resource
	public void setCommodityDAO(CommodityDAO commodityDAO) {
		this.commodityDAO = commodityDAO;
	}

	@Resource
	public void setRitDAO(ReturnStockInTotalDAO rstDAO) {
		this.ritDAO = rstDAO;
	}

	@Resource
	public void setBtDAO(BreakTotalDAO btDAO) {
		this.btDAO = btDAO;
	}

	@Resource
	public void setBdDAO(BreakDetailDAO bdDAO) {
		this.bdDAO = bdDAO;
	}

	@Resource
	public void setRidDAO(ReturnStockInDetailDAO ridDAO) {
		this.ridDAO = ridDAO;
	}

	@Resource
	public void setSrDAO(ShelfRemainDAO srDAO) {
		this.srDAO = srDAO;
	}

	@Resource
	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	@Resource
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Resource
	public void setBotDAO(BreakStockOutTotalDAO botDAO) {
		this.botDAO = botDAO;
	}

	@Resource
	public void setBodDAO(BreakStockOutDetailDAO bodDAO) {
		this.bodDAO = bodDAO;
	}

}
