package com.ncut.wms.saleManagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.client.dao.impl.ClientDAO;
import com.ncut.wms.client.model.Client;
import com.ncut.wms.commodity.dao.CommodityCategoryDAO;
import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.commodity.model.CommodityCategory;
import com.ncut.wms.saleManagement.dao.ReturnStockInDetailDAO;
import com.ncut.wms.saleManagement.dao.ReturnStockInTotalDAO;
import com.ncut.wms.saleManagement.dao.SaleDetailDAO;
import com.ncut.wms.saleManagement.dao.SaleDetailSourceDAO;
import com.ncut.wms.saleManagement.dao.SaleReturnDetailDAO;
import com.ncut.wms.saleManagement.dao.SaleReturnTotalDAO;
import com.ncut.wms.saleManagement.dao.SaleStockOutDetailDAO;
import com.ncut.wms.saleManagement.dao.SaleStockOutTotalDAO;
import com.ncut.wms.saleManagement.dao.SaleTotalDAO;
import com.ncut.wms.saleManagement.dto.SaleManagementDTO;
import com.ncut.wms.saleManagement.model.ReturnStockInDetail;
import com.ncut.wms.saleManagement.model.ReturnStockInTotal;
import com.ncut.wms.saleManagement.model.SaleDetail;
import com.ncut.wms.saleManagement.model.SaleDetailSource;
import com.ncut.wms.saleManagement.model.SaleReturnDetail;
import com.ncut.wms.saleManagement.model.SaleReturnTotal;
import com.ncut.wms.saleManagement.model.SaleStockOutDetail;
import com.ncut.wms.saleManagement.model.SaleStockOutTotal;
import com.ncut.wms.saleManagement.model.SaleTotal;
import com.ncut.wms.shelf.dao.ShelfDAO;
import com.ncut.wms.stock.dao.InStockgoodsDAO;
import com.ncut.wms.stock.dao.ShelfRemainDAO;
import com.ncut.wms.stock.dao.StockDAO;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.model.InStockgoods;
import com.ncut.wms.stock.model.ShelfRemain;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.model.TotalStock;
import com.ncut.wms.storage.dao.StorageDAO;
import com.ncut.wms.unit.dao.UnitDAO;
import com.ncut.wms.unit.model.Unit;
import com.ncut.wms.user.dao.UserDAO;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.system.Tools;



@Service("saleManagementService")
public class SaleManagementService {

	/* ======以下业务逻辑======== */
	/**
	 * 获得对应的商品信息和库存信息
	 * @param smDTO
	 * @return
	 */
	public SaleManagementDTO getCommodityAndStock(SaleManagementDTO smDTO) {

		Commodity commodity = commodityDAO.load(smDTO.getCommodityId());
		CommodityCategory cc = ccDAO.load(commodity.getCategoryId());
		Unit unit = unitDAO.load(commodity.getUnitId());
		TotalStock ts = tsDAO.findByCommodityId(commodity.getCommodityId());
		Client client = clientDAO.load(smDTO.getClientId());

		BeanUtils.copyProperties(commodity, smDTO);
		BeanUtils.copyProperties(ts, smDTO);

		// 设定对应客户级别的售价
		if (client.getLevel() == 0) {
			smDTO.setPrice(commodity.getSalePrice());
		}
		if (client.getLevel() == 1) {
			smDTO.setPrice(commodity.getVip1Price());
		}
		if (client.getLevel() == 2) {
			smDTO.setPrice(commodity.getVip2Price());
		}
		if (client.getLevel() == 3) {
			smDTO.setPrice(commodity.getVip3Price());
		}

		// 设置类别
		smDTO.setCategoryName(cc.getCname());
		// 设置计量单位
		smDTO.setUnitName(unit.getUnitName());

		return smDTO;
	}

	/**
	 * 获得销售单号
	 * @param date
	 * @return
	 */
	public String getSaleCode(String date) {
		String head = "XSDD";
		String code = date.replaceAll("-", "");
		String hql = "select max(st.stId) from SaleTotal as st where st.createDate between '"
				+ date + " 00:00:00' and '" + date + " 23:59:59'";
		List<SaleTotal> list = stDAO.list(hql);
		Object obj = list.get(0);
		if (obj != null)
			return head + code + Tools.formatCode(obj.toString());
		return head + code + "0001";
	}
	
	/**
	 * 获得销售出库单号
	 * @param date
	 * @return
	 */
	public String getSaleStockOutCode(String date) {
		String head = "CKXS";
		String code = date.replaceAll("-", "");
		String hql = "select max(sot.sotId) from SaleStockOutTotal as sot where sot.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<SaleStockOutTotal> list = sotDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	/**
	 * 获得销售退货单据
	 * @param date
	 * @return
	 */
	public String getSaleReturnCode(String date) {
		String head = "THXS";
		String code = date.replaceAll("-", "");
		String hql = "select max(srt.srtId) from SaleReturnTotal as srt where srt.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<SaleReturnTotal> list = srtDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	public String getReturnStockInCode(String date) {
		String head = "RKTH";
		String code = date.replaceAll("-", "");
		String hql = "select max(rit.ritId) from ReturnStockInTotal as rit where rit.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<ReturnStockInTotal> list = ritDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	/**
	 * 保存销售单据
	 * @param smDTO
	 */
	public void saveSale(SaleManagementDTO smDTO) {
		
		//对总单进行赋值
		SaleTotal st = new SaleTotal();
		BeanUtils.copyProperties(smDTO, st);
		st.setStId(smDTO.getOrderId());
		
		//对详单进行赋值
		JSONArray jArr = JSONArray.fromObject(smDTO.getDetailOrder());
		List<SaleDetail> sdList = new ArrayList<SaleDetail>();
		//格式化前台数据
		for(int i=0; i<jArr.size(); i++) {
			
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			
			SaleDetail sd = new SaleDetail();
			//对商品详单赋值
			sd.setStId(jObj.getString("orderId"));
			sd.setCommodityId(jObj.getInt("commodityId"));
			if(jObj.getString("price") == null || "".equals(jObj.getString("price"))) {
				sd.setPrice(0.0);
			} else {
				sd.setPrice(jObj.getDouble("price"));
			}
			if(jObj.getString("amount") == null || "".equals(jObj.getString("amount"))) {
				sd.setAmount(0);
			} else {
				sd.setAmount(jObj.getInt("amount"));
			}
			sd.setReturnedAmount(0);
			sd.setTotalPrice(jObj.getDouble("totalPrice"));
			sdList.add(sd);
		}
		
		//对总单和详单进行存储
		stDAO.add(st);
		for(SaleDetail sd : sdList) {
			sdDAO.add(sd);
			
			//中间表进行修改
			//在入库详单中把有该商品的行找出来，然后找中间表对应的行
			//根据订单中的数量减少中间表的可见剩余量
			//将该数据变化存储到销售来源表
			List<InStockgoods> igList = igDAO.findByCommodity(sd.getCommodityId());
			Integer needAmount = sd.getAmount();
			for(InStockgoods ig : igList) {
				ShelfRemain sr = srDAO.findByOrderIdAndDetailId(ig.getInStockId(), ig.getInStockgoodsId());
				if(sr == null) {
					continue;
				}
				Integer visibleRemain = sr.getVisibleRemain();
				SaleDetailSource sds = new SaleDetailSource();
				
				//该货架的商品量大于等于需求量则直接减少剩余量跳出
				//小于则减掉该货架的全部剩余量，并标识need量下次循环继续操作
				if(visibleRemain >= needAmount) {
					visibleRemain -= needAmount;
					
					//添加来源表的商品来源
					sds.setStId(st.getStId());
					sds.setSdId(sd.getSdId());
					sds.setSsrId(sr.getShelfRemainId());
					sds.setAmount(needAmount);
					
					//减少需要商品的数量
					needAmount -= sr.getVisibleRemain();
					//修改中间表商品剩余量
					sr.setVisibleRemain(visibleRemain);
					
					srDAO.update(sr);
					sdsDAO.add(sds);
					
					//库存详单进行修改
					Stock stock = stockDAO.findByCommodityAndStorage(ig.getCommodityId(), ig.getStorageId());
					Integer visibleStock = stock.getVisibleStock() - needAmount;
					stock.setVisibleStock(visibleStock);
					stockDAO.update(stock);
					break;
				} 
				if(visibleRemain < needAmount && visibleRemain > 0) {
					needAmount -= visibleRemain;
					
					//修改中间表商品剩余量
					sr.setVisibleRemain(0);
					
					//添加来源表的商品来源
					sds.setStId(st.getStId());
					sds.setSdId(sd.getSdId());
					sds.setSsrId(sr.getShelfRemainId());
					sds.setAmount(visibleRemain);
					
					srDAO.update(sr);
					sdsDAO.add(sds);
					
					//库存详单进行修改
					Stock stock = stockDAO.findByCommodityAndStorage(ig.getCommodityId(), ig.getStorageId());
					Integer visibleStock = stock.getVisibleStock() - visibleRemain;
					stock.setVisibleStock(visibleStock);
					stockDAO.update(stock);
				}
				
			}
			
			if(needAmount != 0) {
				//在退货入库表中查找可销售的商品
				List<ReturnStockInDetail> ridList = ridDAO.findByCommodityId(sd.getCommodityId());
				for(ReturnStockInDetail rid : ridList) {
					ShelfRemain sr = srDAO.findByOrderIdAndDetailId(rid.getRitId(), rid.getRidId());
					if(sr == null) {
						continue;
					}
					Integer visibleRemain = sr.getVisibleRemain();
					SaleDetailSource sds = new SaleDetailSource();

					//该货架的商品量大于等于需求量则直接减少剩余量跳出
					//小于则减掉该货架的全部剩余量，并标识need量下次循环继续操作
					if(visibleRemain >= needAmount) {
						visibleRemain -= needAmount;
						
						//添加来源表的商品来源
						sds.setStId(st.getStId());
						sds.setSdId(sd.getSdId());
						sds.setSsrId(sr.getShelfRemainId());
						sds.setAmount(needAmount);
						
						//修改中间表商品剩余量
						sr.setVisibleRemain(visibleRemain);
						
						srDAO.update(sr);
						sdsDAO.add(sds);
						
						//库存详单进行修改
						Stock stock = stockDAO.findByCommodityAndStorage(rid.getCommodityId(), rid.getStorageId());
						Integer visibleStock = stock.getVisibleStock() - needAmount;
						stock.setVisibleStock(visibleStock);
						stockDAO.update(stock);
						
						//减少需要商品的数量
						needAmount -= sr.getVisibleRemain();
						
						break;
					} else {
						needAmount -= visibleRemain;
						
						//修改中间表商品剩余量
						sr.setVisibleRemain(0);
						
						//添加来源表的商品来源
						sds.setStId(st.getStId());
						sds.setSdId(sd.getSdId());
						sds.setSsrId(sr.getShelfRemainId());
						sds.setAmount(visibleRemain);
						
						srDAO.update(sr);
						sdsDAO.add(sds);
						
						//库存详单进行修改
						Stock stock = stockDAO.findByCommodityAndStorage(rid.getCommodityId(), rid.getStorageId());
						Integer visibleStock = stock.getVisibleStock() - visibleRemain;
						stock.setVisibleStock(visibleStock);
						stockDAO.update(stock);
					}
				}
			}
			
			//库存总单进行修改
			TotalStock ts = tsDAO.findByCommodityId(sd.getCommodityId());
			Integer visibleStock = ts.getVisibleStock() - sd.getAmount();
			ts.setVisibleStock(visibleStock);
			tsDAO.update(ts);
			
		}
		
	}
	
	/**
	 * 保存销售出库单据
	 * @param smDTO
	 */
	public void saveSaleStockOut(SaleManagementDTO smDTO) {
		//对总单进行赋值
		SaleStockOutTotal sot = new SaleStockOutTotal();
		BeanUtils.copyProperties(smDTO, sot);
		sot.setStId(smDTO.getOrderId());
		String saleStockOutCode = this.getSaleStockOutCode(smDTO.getCreateDate().substring(0, 10));
		sot.setSotId(saleStockOutCode);
		
		//对详单进行赋值
		List<SaleDetail> sdList = sdDAO.findBySaleTotal(smDTO.getOrderId());
		List<SaleStockOutDetail> sodList = new ArrayList<SaleStockOutDetail>();
		for(SaleDetail sd : sdList) {
			SaleStockOutDetail sod = new SaleStockOutDetail();
			BeanUtils.copyProperties(sd, sod);
			sod.setSotId(sot.getSotId());
			
			sodList.add(sod);
		}
		
		//对总单和详单进行存储
		sotDAO.add(sot);
		for(SaleStockOutDetail sod : sodList) {
			sodDAO.add(sod);
			
			//对中间表进行操作
			//首先通过销售总单ID查找销售来源表中的数据
			//然后通过入库总单ID和详单ID查找相应的货架剩余单
			List<SaleDetailSource> sdsList =  sdsDAO.findBySaleDetail(sod.getSdId());
			for(SaleDetailSource sds : sdsList) {
				//中间表修改
				ShelfRemain sr =  srDAO.load(sds.getSsrId());
				Integer realRemain = sr.getRealRemain() - sds.getAmount();
				if(realRemain == 0 ) {
					srDAO.delete(sr.getShelfRemainId());
				} else {
					sr.setRealRemain(realRemain);
					srDAO.update(sr);
				}
				
				//库存详单修改
				String orderId = sr.getOrderId();
				Integer commodityId = 0;
				Integer storageId = 0;
				if(orderId.substring(0, 4).equals("RKJH")) {
					InStockgoods ig = new InStockgoods();
					ig = igDAO.load(sr.getDetailId());
					commodityId = ig.getCommodityId();
					storageId = ig.getStorageId();
				}
				if(orderId.substring(0, 4).equals("RKTH")) {
					ReturnStockInDetail rid = new ReturnStockInDetail();
					rid = ridDAO.load(sr.getDetailId());
					commodityId = rid.getCommodityId();
					storageId = rid.getStorageId();
				}
				Stock stock = stockDAO.findByCommodityAndStorage(commodityId, storageId);
				realRemain = stock.getStockAmount() - sds.getAmount();
				Integer outAmount = stock.getOutStock() + sds.getAmount();
				stock.setStockAmount(realRemain);
				stock.setOutStock(outAmount);
				
				stockDAO.update(stock);
			}
			
			//库存总单进行修改
			SaleDetail sd = sdDAO.load(sod.getSdId());
			TotalStock ts = tsDAO.findByCommodityId(sd.getCommodityId());
			Integer stockAmount = ts.getStockAmount() - sd.getAmount();
			Integer outAmount = ts.getOutStock() + sd.getAmount();
			ts.setStockAmount(stockAmount);
			ts.setOutStock(outAmount);
			tsDAO.update(ts);
		}
		
		//对销售总单库存状态进行修改
		SaleTotal st = stDAO.load(sot.getStId());
		st.setSendDate(smDTO.getSendDate());
		st.setStockState(1);
		stDAO.update(st);
			
	}
	
	/**
	 * 保存销售退货单据
	 * @param smDTO
	 */
	public void saveSaleReturn(SaleManagementDTO smDTO) {
		//对总单进行赋值
		SaleReturnTotal srt = new SaleReturnTotal();
		BeanUtils.copyProperties(smDTO, srt);
		srt.setStId(smDTO.getOrderId());
		String saleReturnCode = this.getSaleReturnCode(smDTO.getCreateDate().substring(0, 10));
		srt.setSrtId(saleReturnCode);
		
		//对详单进行赋值
		JSONArray jArr = JSONArray.fromObject(smDTO.getDetailOrder());
		List<SaleReturnDetail> srdList = new ArrayList<SaleReturnDetail>();
		//格式化前台数据
		for(int i=0; i<jArr.size(); i++) {
			
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			SaleReturnDetail srd = new SaleReturnDetail();
			Integer returnedAmount = jObj.getInt("returnedAmount");
			//对详单赋值
			if(returnedAmount != 0) {
				srd.setSrtId(saleReturnCode);
				srd.setSdId(jObj.getInt("detailId"));
				srd.setPrice(jObj.getDouble("price"));
				srd.setReturnedAmount(jObj.getInt("returnedAmount"));
				Double totalPrice = jObj.getDouble("price") * jObj.getInt("returnedAmount");
				srd.setTotalPrice(totalPrice);
				srdList.add(srd);
			}
		}
		
		//对总单和详单进行存储
		srtDAO.add(srt);
		for(SaleReturnDetail srd : srdList) {
			srdDAO.add(srd);
			
			//销售退货
			//销售单的退货量进行修改
			SaleDetail sd = sdDAO.load(srd.getSdId());
			Integer returnedAmount = sd.getReturnedAmount() + srd.getReturnedAmount();
			sd.setReturnedAmount(returnedAmount);
			sdDAO.update(sd);
			
		}
		
		//取消销售订单
		SaleTotal st = stDAO.load(srt.getStId());
		if(st.getStockState() == 0) {
			
			//退货单库存状态修改
			srt.setStockState(1);
			srtDAO.update(srt);
			
			//对销售单库存状态进行修改
			st.setStockState(2);
			
			//中间表可见库存量恢复
			//根据销售来源查找对应中间表
			List<SaleDetailSource> sdsList = sdsDAO.findBySaleTotal(st.getStId());
			for(SaleDetailSource sds : sdsList) {
				ShelfRemain sr = srDAO.load(sds.getSsrId());
				Integer visibleRemain = sr.getVisibleRemain() + sds.getAmount();
				sr.setVisibleRemain(visibleRemain);
				srDAO.update(sr);
				
				//库存详单可见销售量恢复
				InStockgoods ig = igDAO.load(sr.getDetailId());
				Stock stock = stockDAO.findByCommodityAndStorage(ig.getCommodityId(), ig.getStorageId());
				visibleRemain = stock.getVisibleStock() + sds.getAmount();
				stock.setVisibleStock(visibleRemain);
				stockDAO.update(stock);
				
			}
			
			//库存总单可见销售量恢复
			List<SaleDetail> sdList = sdDAO.findBySaleTotal(st.getStId());
			for(SaleDetail sd : sdList) {
				TotalStock ts = tsDAO.findByCommodityId(sd.getCommodityId());
				Integer visibleStock = ts.getVisibleStock() + sd.getAmount();
				ts.setVisibleStock(visibleStock);
				tsDAO.update(ts);
			}
			
		}
		
	}
	
	/**
	 * 保存销售退货入库单据
	 * @param smDTO
	 */
	public void saveReturnStockIn(SaleManagementDTO smDTO) {
		
		//对总单进行赋值
		ReturnStockInTotal rit = new ReturnStockInTotal();
		BeanUtils.copyProperties(smDTO, rit);
		//获得订单号并赋值
		String date = smDTO.getCreateDate().substring(0, 10);
		String ritId = this.getReturnStockInCode(date);
		rit.setRitId(ritId);
		rit.setSrtId(smDTO.getOrderId());
		
		//对详单进行赋值
		JSONArray jArr = JSONArray.fromObject(smDTO.getDetailOrder());
		List<ReturnStockInDetail> ridList = new ArrayList<ReturnStockInDetail>();
		//格式化前台数据
		for(int i=0; i<jArr.size(); i++) {
			
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			
			
			ReturnStockInDetail rid = new ReturnStockInDetail();
			//对商品详单赋值
			rid.setRitId(ritId);
			rid.setSrdId(jObj.getInt("detailId"));
			rid.setCommodityId(jObj.getInt("commodityId"));
			rid.setAmount(jObj.getInt("returnedAmount"));
			rid.setStorageId(jObj.getInt("storageId"));
			rid.setShelfId(jObj.getInt("shelfId"));
			
			ridList.add(rid);
		}
		
		//对总单和详单进行存储
		ritDAO.add(rit);
		for(ReturnStockInDetail rid : ridList) {
			//对入库表进行存储
			ridDAO.add(rid);
			//对中间表进行存储
			ShelfRemain sr = new ShelfRemain();
			sr.setOrderId(rid.getRitId());
			sr.setDetailId(rid.getRidId());
			sr.setCommodityId(rid.getCommodityId());
			sr.setVisibleRemain(rid.getAmount());
			sr.setRealRemain(rid.getAmount());
			srDAO.add(sr);
			
			//对库存信息进行修改:首先搜索商品编号和仓库编号，如果有则修改该库存量，
			//如果没有则添加该库存信息。同时注意实际库存量应该为入库数量减去退货数量
			Stock stock = stockDAO.findByCommodityAndStorage(rid.getCommodityId(), rid.getStorageId());
			if(stock != null) {
				//有则修改原表
				Integer inStockAmount = stock.getInStock() + rid.getAmount();
				Integer visibleStockAmount = stock.getVisibleStock() + rid.getAmount();
				Integer stockAmount = stock.getStockAmount() + rid.getAmount();
				
				stock.setInStock(inStockAmount);
				stock.setVisibleStock(visibleStockAmount);
				stock.setStockAmount(stockAmount);
				stockDAO.update(stock);
				
				//对库存总表中的数据进行修改
				TotalStock ts = tsDAO.findByCommodityId(rid.getCommodityId());
				//增加入货、可销售、总量数据
				inStockAmount = ts.getInStock() + rid.getAmount();
				visibleStockAmount = ts.getVisibleStock() + rid.getAmount();
				stockAmount = ts.getStockAmount() + rid.getAmount();
				
				ts.setInStock(inStockAmount);
				ts.setVisibleStock(visibleStockAmount);
				ts.setStockAmount(stockAmount);
				tsDAO.update(ts);
				
			} else {
				//没有则添加一张新表
				Integer commodityId = rid.getCommodityId();
				Integer inStockAmount = rid.getAmount();
				Integer storageId = rid.getStorageId();
				
				stock = new Stock();
				stock.setCommodityId(commodityId);
				stock.setInStock(inStockAmount);
				stock.setOutStock(0);
				stock.setVisibleStock(inStockAmount);
				stock.setStockAmount(inStockAmount);
				stock.setStorageId(storageId);
				stockDAO.add(stock);
				
				//对库存总表中的数据进行修改
				TotalStock ts = tsDAO.findByCommodityId(commodityId);
				//减少进货量
				Integer purchaseAmount = ts.getPurchase() - rid.getAmount();
				//增加入货、可销售、总量数据
				inStockAmount = ts.getInStock() + rid.getAmount();
				Integer visibleStockAmount = ts.getVisibleStock() + rid.getAmount();
				Integer stockAmount = ts.getStockAmount() + rid.getAmount();
				
				ts.setPurchase(purchaseAmount);
				ts.setInStock(inStockAmount);
				ts.setVisibleStock(visibleStockAmount);
				ts.setStockAmount(stockAmount);
				tsDAO.update(ts);
			}
			
			
		}
		
		//退货总单的库存状态进行修改和存储
		SaleReturnTotal srt = srtDAO.load(rit.getSrtId());
		srt.setReceivedDate(rit.getReceivedDate());
		srt.setStockState(1);
		srtDAO.update(srt);
	}
	
	/**
	 * 获得销售单据总单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getSaleTotalGrid(SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String head = "select st ";
		String hql = "from SaleTotal st, Client c where 1=1 and st.clientId = c.clientId";
		
		if(smDTO.getStateStr() != null && !"".equals(smDTO.getStateStr().trim())){
			hql+=" and st.stockState " + smDTO.getStateStr();
		}
		
		if(smDTO.getBeginDate()!=null && !"".equals(smDTO.getBeginDate().trim())){
			hql+=" and st.createDate between :beginDate and :endDate";
			map.put("beginDate", smDTO.getBeginDate().trim());
			map.put("endDate", smDTO.getEndDate().trim());
		}
		
		if(smDTO.getClientName()!=null && !"".equals(smDTO.getClientName().trim())){
			hql+=" and c.clientName like :clientName";
			map.put("clientName", "%" + smDTO.getClientName().trim() + "%");
		}
		
		if(smDTO.getStockState()!=null) {
			hql += " and st.stockState = :stockState";
			map.put("stockState", smDTO.getStockState());
		}
		
		if(smDTO.getPayablePrice1()!=null) {
			hql += " and st.payablePrice between :payablePrice1 and :payablePrice2";
			map.put("payablePrice1", smDTO.getPayablePrice1());
			map.put("payablePrice2", smDTO.getPayablePrice2());
		}
		
		if(smDTO.getRealPrice1()!=null) {
			hql += " and st.realPrice between :realPrice1 and :realPrice2";
			map.put("realPrice1", smDTO.getRealPrice1());
			map.put("realPrice2", smDTO.getRealPrice2());
		}
		
		String totalHql = "select count(st) "+hql;
		hql = head + hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<SaleTotal> stList = stDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> stDTOList = new ArrayList<SaleManagementDTO>();
		for(SaleTotal st : stList){
			SaleManagementDTO stDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(st, stDTO);
			stDTO.setOrderId(st.getStId());
			
			//插入一些需要的数据
			stDTO.setClientName(clientDAO.load(st.getClientId()).getClientName());
			stDTO.setUserName(userDAO.load(st.getUserId()).getUsername());
			
			stDTOList.add(stDTO);
		}
		dg.setTotal(stDAO.count(totalHql, map));
		dg.setRows(stDTOList);
		return dg;
	}
	
	/**
	 * 获得销售单据详单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getSaleDetailGrid(SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from SaleDetail sd where 1=1";
		
		if(smDTO.getOrderId()!=null && !"".equals(smDTO.getOrderId().trim())){
			hql+=" and sd.stId = :stId";
			map.put("stId", smDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<SaleDetail> sdList = sdDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> sdDTOList = new ArrayList<SaleManagementDTO>();
		for(SaleDetail sd : sdList){
			SaleManagementDTO sdDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(sd, sdDTO);
			sdDTO.setOrderId(sd.getStId());
			sdDTO.setDetailId(sd.getSdId());
			//判断是否为查询展示，非查询情况计算可退货数量，退货数置为0
			if(smDTO.getStateStr() == null || !smDTO.getStateStr().equals("query")) {
				Integer visibleRemain = sd.getAmount() - sd.getReturnedAmount();
				sdDTO.setVisibleRemain(visibleRemain);
				sdDTO.setReturnedAmount(0);
			}
			
			
			//插入一些需要的数据
			sdDTO.setCommodityName(commodityDAO.load(sd.getCommodityId()).getCommodityName());
			
			sdDTOList.add(sdDTO);
		}
		dg.setTotal(stDAO.count(totalHql, map));
		dg.setRows(sdDTOList);
		return dg;
	}
	
	/**
	 * 获得退货总单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getSaleReturnTotalGrid(
			SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from SaleReturnTotal srt where 1=1";
		
		if(smDTO.getBeginDate()!=null && !"".equals(smDTO.getBeginDate().trim())){
			hql+=" and srt.createDate between :beginDate and :endDate";
			map.put("beginDate", smDTO.getBeginDate().trim());
			map.put("endDate", smDTO.getEndDate().trim());
		}
		
		if(smDTO.getStateStr() != null && !"".equals(smDTO.getStateStr().trim())){
			hql+=" and srt.stockState " + smDTO.getStateStr();
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<SaleReturnTotal> srtList = srtDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> srtDTOList = new ArrayList<SaleManagementDTO>();
		for(SaleReturnTotal srt : srtList){
			SaleManagementDTO stDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(srt, stDTO);
			stDTO.setOrderId(srt.getSrtId());
			
			//插入一些需要的数据
			stDTO.setUserName(userDAO.load(srt.getUserId()).getUsername());
			
			srtDTOList.add(stDTO);
		}
		dg.setTotal(stDAO.count(totalHql, map));
		dg.setRows(srtDTOList);
		return dg;
	}
	
	/**
	 * 获得退货详单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getSaleReturnDetailGrid(SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from SaleReturnDetail srd where 1=1";
		
		if(smDTO.getOrderId()!=null && !"".equals(smDTO.getOrderId().trim())){
			hql+=" and srd.srtId = :srtId";
			map.put("srtId", smDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<SaleReturnDetail> srdList = srdDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> srdDTOList = new ArrayList<SaleManagementDTO>();
		for(SaleReturnDetail srd : srdList){
			SaleManagementDTO srdDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(srd, srdDTO);
			srdDTO.setOrderId(srd.getSrtId());
			srdDTO.setDetailId(srd.getSrdId());
			
			//插入一些需要的数据
			SaleDetail sd = sdDAO.load(srd.getSdId());
			srdDTO.setCommodityId(sd.getCommodityId());
			srdDTO.setCommodityName(commodityDAO.load(sd.getCommodityId()).getCommodityName());
			
			srdDTOList.add(srdDTO);
		}
		dg.setTotal(srdDAO.count(totalHql, map));
		dg.setRows(srdDTOList);
		return dg;
	}
	
	/**
	 * 获得退货总单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getSaleStockOutTotalGrid(
			SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from SaleStockOutTotal sot where 1=1";
		
		if(smDTO.getBeginDate()!=null && !"".equals(smDTO.getBeginDate().trim())){
			hql+=" and sot.createDate between :beginDate and :endDate";
			map.put("beginDate", smDTO.getBeginDate().trim());
			map.put("endDate", smDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<SaleStockOutTotal> sotList = sotDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> sotDTOList = new ArrayList<SaleManagementDTO>();
		for(SaleStockOutTotal sot : sotList){
			SaleManagementDTO sotDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(sot, sotDTO);
			sotDTO.setOrderId(sot.getSotId());
			
			//插入一些需要的数据
			sotDTO.setUserName(userDAO.load(sot.getUserId()).getUsername());
			
			sotDTOList.add(sotDTO);
		}
		dg.setTotal(sotDAO.count(totalHql, map));
		dg.setRows(sotDTOList);
		return dg;
	}
	
	/**
	 * 获得销售出库详单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getSaleStockOutDetailGrid(SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from SaleStockOutDetail sod where 1=1";
		
		if(smDTO.getOrderId()!=null && !"".equals(smDTO.getOrderId().trim())){
			hql+=" and sod.sotId = :sotId";
			map.put("sotId", smDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<SaleStockOutDetail> sodList = sodDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> sodDTOList = new ArrayList<SaleManagementDTO>();
		for(SaleStockOutDetail sod : sodList){
			SaleManagementDTO sodDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(sod, sodDTO);
			sodDTO.setOrderId(sod.getSotId());
			sodDTO.setDetailId(sod.getSodId());
			
			//插入一些需要的数据
			SaleDetail sd = sdDAO.load(sod.getSdId());
			BeanUtils.copyProperties(sd, sodDTO);
			sodDTO.setCommodityName(commodityDAO.load(sd.getCommodityId()).getCommodityName());
			
			sodDTOList.add(sodDTO);
		}
		dg.setTotal(sodDAO.count(totalHql, map));
		dg.setRows(sodDTOList);
		return dg;
	}
	
	/**
	 * 获得退货入库总单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getReturnStockInTotalGrid(
			SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from ReturnStockInTotal rit where 1=1";
		
		if(smDTO.getBeginDate()!=null && !"".equals(smDTO.getBeginDate().trim())){
			hql+=" and rit.createDate between :beginDate and :endDate";
			map.put("beginDate", smDTO.getBeginDate().trim());
			map.put("endDate", smDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<ReturnStockInTotal> ritList = ritDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> ritDTOList = new ArrayList<SaleManagementDTO>();
		for(ReturnStockInTotal rit : ritList){
			SaleManagementDTO ritDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(rit, ritDTO);
			ritDTO.setOrderId(rit.getRitId());
			
			//插入一些需要的数据
			ritDTO.setUserName(userDAO.load(rit.getUserId()).getUsername());
			
			ritDTOList.add(ritDTO);
		}
		dg.setTotal(ritDAO.count(totalHql, map));
		dg.setRows(ritDTOList);
		return dg;
	}
	
	/**
	 * 获得退货入库详单表格(easyui)
	 * @param smDTO
	 * @return
	 */
	public DataGrid<SaleManagementDTO> getReturnStockInDetailGrid(SaleManagementDTO smDTO) {
		DataGrid<SaleManagementDTO> dg = new DataGrid<SaleManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from ReturnStockInDetail rid where 1=1";
		
		if(smDTO.getOrderId()!=null && !"".equals(smDTO.getOrderId().trim())){
			hql+=" and rid.ritId = :ritId";
			map.put("ritId", smDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(smDTO.getSort()!=null){
			hql+=" order by "+smDTO.getSort()+" "+smDTO.getOrder();
		}
		List<ReturnStockInDetail> ridList = ridDAO.list(hql, map, smDTO.getPage(), smDTO.getRows());
		List<SaleManagementDTO> ridDTOList = new ArrayList<SaleManagementDTO>();
		for(ReturnStockInDetail rid : ridList){
			SaleManagementDTO ridDTO = new SaleManagementDTO();
			BeanUtils.copyProperties(rid, ridDTO);
			ridDTO.setOrderId(rid.getRitId());
			ridDTO.setDetailId(rid.getRidId());
			
			//判断是否为查询展示，非查询情况计算可退货数量，退货数置为0
			if(smDTO.getStateStr() == null || !smDTO.getStateStr().equals("query")) {
				ShelfRemain sr = srDAO.findByOrderIdAndDetailId(rid.getRitId(), rid.getRidId());
				Integer visibleRemain = 0;
				if(sr != null) {
					visibleRemain = sr.getVisibleRemain();
				}
				ridDTO.setVisibleRemain(visibleRemain);
				ridDTO.setReturnedAmount(0);
			}
			
			//插入一些需要的数据
			ridDTO.setCommodityName(commodityDAO.load(rid.getCommodityId()).getCommodityName());
			ridDTO.setStorageName(storageDAO.load(rid.getShelfId()).getStorageName());
			ridDTO.setShelfName(shelfDAO.load(rid.getShelfId()).getShelfName());
			
			ridDTOList.add(ridDTO);
		}
		dg.setTotal(sodDAO.count(totalHql, map));
		dg.setRows(ridDTOList);
		return dg;
	}

	/* ======以下声明======== */
	private SaleTotalDAO stDAO;
	private SaleDetailDAO sdDAO;
	private SaleDetailSourceDAO sdsDAO;
	
	private SaleStockOutTotalDAO sotDAO;
	private SaleStockOutDetailDAO sodDAO;
	
	private SaleReturnTotalDAO srtDAO;
	private SaleReturnDetailDAO srdDAO;
	
	private ReturnStockInTotalDAO ritDAO;
	private ReturnStockInDetailDAO ridDAO;
	
	private CommodityDAO commodityDAO;
	private CommodityCategoryDAO ccDAO;
	private UnitDAO unitDAO;
	private ClientDAO clientDAO;
	private UserDAO userDAO;
	
	private StorageDAO storageDAO;
	private ShelfDAO shelfDAO;
	
	private ShelfRemainDAO srDAO;
	private TotalStockDAO tsDAO;
	private StockDAO stockDAO;
	private InStockgoodsDAO igDAO;

	@Resource
	public void setCommodityDAO(CommodityDAO cDAO) {
		this.commodityDAO = cDAO;
	}

	@Resource
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
	}

	@Resource
	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	@Resource
	public void setCcDAO(CommodityCategoryDAO ccDAO) {
		this.ccDAO = ccDAO;
	}

	@Resource
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}

	@Resource
	public void setStDAO(SaleTotalDAO stDAO) {
		this.stDAO = stDAO;
	}

	@Resource
	public void setSdDAO(SaleDetailDAO sdDAO) {
		this.sdDAO = sdDAO;
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
	public void setIgDAO(InStockgoodsDAO igDAO) {
		this.igDAO = igDAO;
	}

	@Resource
	public void setSdsDAO(SaleDetailSourceDAO sdsDAO) {
		this.sdsDAO = sdsDAO;
	}

	@Resource
	public void setSotDAO(SaleStockOutTotalDAO sotDAO) {
		this.sotDAO = sotDAO;
	}

	@Resource
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Resource
	public void setSodDAO(SaleStockOutDetailDAO sodDAO) {
		this.sodDAO = sodDAO;
	}

	@Resource
	public void setSrtDAO(SaleReturnTotalDAO srtDAO) {
		this.srtDAO = srtDAO;
	}

	@Resource
	public void setSrdDAO(SaleReturnDetailDAO srdDAO) {
		this.srdDAO = srdDAO;
	}

	@Resource
	public void setRitDAO(ReturnStockInTotalDAO ritDAO) {
		this.ritDAO = ritDAO;
	}

	@Resource
	public void setRidDAO(ReturnStockInDetailDAO ridDAO) {
		this.ridDAO = ridDAO;
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
