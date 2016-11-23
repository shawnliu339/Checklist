package com.ncut.wms.purchaseManagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.purchaseManagement.dao.ReturnStockOutDetailDAO;
import com.ncut.wms.purchaseManagement.dao.ReturnStockOutTotalDAO;
import com.ncut.wms.purchaseManagement.dto.PurchaseManagementDTO;
import com.ncut.wms.purchaseManagement.model.ReturnStockOutDetail;
import com.ncut.wms.purchaseManagement.model.ReturnStockOutTotal;
import com.ncut.wms.returned.dao.PurchaseReturnedDetailDAO;
import com.ncut.wms.returned.dao.PurchaseReturnedTotalDAO;
import com.ncut.wms.returned.model.PurchaseReturnedDetail;
import com.ncut.wms.returned.model.PurchaseReturnedTotal;
import com.ncut.wms.stock.dao.InStockgoodsDAO;
import com.ncut.wms.stock.dao.ShelfRemainDAO;
import com.ncut.wms.stock.dao.StockDAO;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.model.InStockgoods;
import com.ncut.wms.stock.model.ShelfRemain;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.model.TotalStock;
import com.ncut.wms.user.dao.UserDAO;
import com.ncut.wms.user.model.User;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.system.Tools;

@Service("purchaseManagementService")
public class PurchaseManagementService {

	/* ======以下业务逻辑======== */
	public String getReturnStockOutCode(String date) {
		String head = "CKTH";
		String code = date.replaceAll("-", "");
		String hql = "select max(rot.rotId) from ReturnStockOutTotal as rot where rot.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<ReturnStockOutTotal> list = rotDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	public DataGrid<PurchaseManagementDTO> getReturnStockOutTotalGrid(
			PurchaseManagementDTO pmDTO) {
		DataGrid<PurchaseManagementDTO> dg = new DataGrid<PurchaseManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from ReturnStockOutTotal rot where 1=1";
		
		if(pmDTO.getBeginDate()!=null && !"".equals(pmDTO.getBeginDate().trim())){
			hql+=" and rot.createDate between :beginDate and :endDate";
			map.put("beginDate", pmDTO.getBeginDate().trim());
			map.put("endDate", pmDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(pmDTO.getSort()!=null){
			hql+=" order by "+pmDTO.getSort()+" "+pmDTO.getOrder();
		}
		List<ReturnStockOutTotal> rotList = rotDAO.list(hql, map, pmDTO.getPage(), pmDTO.getRows());
		List<PurchaseManagementDTO> rotDTOList = new ArrayList<PurchaseManagementDTO>();
		for(ReturnStockOutTotal rot : rotList){
			PurchaseManagementDTO rotDTO = new PurchaseManagementDTO();
			BeanUtils.copyProperties(rot, rotDTO);
			
			//插入一些需要的数据
			rotDTO.setOrderId(rot.getRotId());
			PurchaseReturnedTotal prt = prtDAO.findById(rot.getPrtId());
			rotDTO.setReturnedPrice(prt.getReturnedPrice());
			User u = uDAO.load(rot.getUserId());
			rotDTO.setUserName(u.getUsername());
			
			rotDTOList.add(rotDTO);
		}
		dg.setTotal(prtDAO.count(totalHql, map));
		dg.setRows(rotDTOList);
		return dg;
	}
	
	public DataGrid<PurchaseManagementDTO> getReturnStockOutDetailGrid(
			PurchaseManagementDTO pmDTO) {
		
		DataGrid<PurchaseManagementDTO> dg = new DataGrid<PurchaseManagementDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from ReturnStockOutDetail rod where 1=1";
		
		if(pmDTO.getOrderId()!=null && !"".equals(pmDTO.getOrderId().trim())){
			hql+=" and rod.rotId = :rotId";
			map.put("rotId", pmDTO.getOrderId().trim());
		}
		
		if(pmDTO.getBeginDate()!=null && !"".equals(pmDTO.getBeginDate().trim())){
			hql+=" and rot.createDate between :beginDate and :endDate";
			map.put("beginDate", pmDTO.getBeginDate().trim());
			map.put("endDate", pmDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(pmDTO.getSort()!=null){
			hql+=" order by "+pmDTO.getSort()+" "+pmDTO.getOrder();
		}
		List<ReturnStockOutDetail> rodList = rodDAO.list(hql, map, pmDTO.getPage(), pmDTO.getRows());
		List<PurchaseManagementDTO> rodDTOList = new ArrayList<PurchaseManagementDTO>();
		for(ReturnStockOutDetail rod : rodList){
			PurchaseManagementDTO rodDTO = new PurchaseManagementDTO();
			BeanUtils.copyProperties(rod, rodDTO);
			
			//插入一些需要的数据
			PurchaseReturnedDetail prd = prdDAO.load(rod.getPrdId());
			Commodity commodity = cDAO.load(prd.getCommodityId());
			rodDTO.setDetailId(rod.getRodId());
			rodDTO.setCommodityId(commodity.getCommodityId());
			rodDTO.setCommodityName(commodity.getCommodityName());
			rodDTO.setDetailPrice(prd.getReturnedPrice());
			rodDTO.setReturnedAmount(prd.getReturnedAmount());
			rodDTO.setTotalPrice(prd.getTotalPrice());
			
			rodDTOList.add(rodDTO);
		}
		dg.setTotal(prtDAO.count(totalHql, map));
		dg.setRows(rodDTOList);
		return dg;
	}
	
	/**
	 * 保存进货退货单
	 * @param pmDTO
	 */
	public void saveReturnStockOut(PurchaseManagementDTO pmDTO) {

		//对出库总单进行赋值
		String prtId = pmDTO.getOrderId();
		ReturnStockOutTotal rot = new ReturnStockOutTotal();
		String date = pmDTO.getCreateDate().substring(0, 10);
		String rotId = this.getReturnStockOutCode(date);
		rot.setRotId(rotId);
		rot.setPrtId(prtId);
		rot.setCreateDate(pmDTO.getCreateDate());
		rot.setUserId(pmDTO.getUserId());
		rot.setRemark(pmDTO.getRemark());
		
		//对出库详单进行赋值
		List<ReturnStockOutDetail> rodList = new ArrayList<ReturnStockOutDetail>();
		List<PurchaseReturnedDetail> prdList =  prdDAO.list("from PurchaseReturnedDetail prd where prd.prtId =  '" + prtId +"'");
		for(PurchaseReturnedDetail prd : prdList) {
			ReturnStockOutDetail rod = new ReturnStockOutDetail();
			rod.setRotId(rotId);
			rod.setPrdId(prd.getPrdId());
			rodList.add(rod);
		}
		
		//对总单和详单进行存储
		PurchaseReturnedTotal prt = prtDAO.findById(prtId);
		rotDAO.add(rot);
		for(ReturnStockOutDetail rod : rodList) {
			rodDAO.add(rod);
			
			PurchaseReturnedDetail prd = prdDAO.load(rod.getPrdId());
			//中间表的退货量进行修改
			ShelfRemain sr = srDAO.findByOrderIdAndDetailId(prt.getInStockId(),prd.getInStockgoodsId());
			Integer realRemain = sr.getRealRemain() - prd.getReturnedAmount();
			if(realRemain == 0) {
				srDAO.delete(sr.getShelfRemainId());
			} else {
				sr.setRealRemain(realRemain);
				srDAO.update(sr);
			}
			
			//库存总单的退货量进行修改
			TotalStock ts = tsDAO.findByCommodityId(prd.getCommodityId());
			Integer outStock = ts.getOutStock() + prd.getReturnedAmount();
			Integer stockAmount = ts.getStockAmount() - prd.getReturnedAmount();
			ts.setOutStock(outStock);
			ts.setStockAmount(stockAmount);
			tsDAO.update(ts);
			
			//库存详单的退货量进行修改
			InStockgoods ig = igDAO.load(prd.getInStockgoodsId());
			Stock stock = stockDAO.findByCommodityAndStorage(ig.getCommodityId(), ig.getStorageId());
			outStock = stock.getOutStock() + prd.getReturnedAmount();
			stockAmount = stock.getStockAmount() - prd.getReturnedAmount();
			stock.setOutStock(outStock);
			stock.setStockAmount(stockAmount);
			stockDAO.update(stock);
			
		}
		
		//对出库申请的库存状态进行修改
		prt.setStockState(1);
		prtDAO.update(prt);
		
		
	}

	/* ======以下声明======== */
	private PurchaseReturnedTotalDAO prtDAO;
	private PurchaseReturnedDetailDAO prdDAO;
	private ReturnStockOutTotalDAO rotDAO;
	private ReturnStockOutDetailDAO rodDAO;
	private ShelfRemainDAO srDAO;
	private TotalStockDAO tsDAO;
	private StockDAO stockDAO;
	private InStockgoodsDAO igDAO;
	private UserDAO uDAO;
	private CommodityDAO cDAO;

	@Resource
	public void setPrtDAO(PurchaseReturnedTotalDAO prtDAO) {
		this.prtDAO = prtDAO;
	}

	@Resource
	public void setRotDAO(ReturnStockOutTotalDAO rotDAO) {
		this.rotDAO = rotDAO;
	}

	@Resource
	public void setPrdDAO(PurchaseReturnedDetailDAO prdDAO) {
		this.prdDAO = prdDAO;
	}

	@Resource
	public void setRodDAO(ReturnStockOutDetailDAO rodDAO) {
		this.rodDAO = rodDAO;
	}

	@Resource
	public void setSrDAO(ShelfRemainDAO srDAO) {
		this.srDAO = srDAO;
	}

	@Resource
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
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
	public void setuDAO(UserDAO uDAO) {
		this.uDAO = uDAO;
	}

	@Resource
	public void setcDAO(CommodityDAO cDAO) {
		this.cDAO = cDAO;
	}

	

	
}
