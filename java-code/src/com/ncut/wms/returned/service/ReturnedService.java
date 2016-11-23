package com.ncut.wms.returned.service;

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
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.purchase.dao.PurchasegoodsDAO;
import com.ncut.wms.purchase.model.Purchasegoods;
import com.ncut.wms.returned.dao.PurchaseReturnedDetailDAO;
import com.ncut.wms.returned.dao.PurchaseReturnedTotalDAO;
import com.ncut.wms.returned.dto.ReturnedDTO;
import com.ncut.wms.returned.model.PurchaseReturnedDetail;
import com.ncut.wms.returned.model.PurchaseReturnedTotal;
import com.ncut.wms.stock.dao.InStockDAO;
import com.ncut.wms.stock.dao.InStockgoodsDAO;
import com.ncut.wms.stock.dao.ShelfRemainDAO;
import com.ncut.wms.stock.dao.StockDAO;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.model.InStock;
import com.ncut.wms.stock.model.InStockgoods;
import com.ncut.wms.stock.model.ShelfRemain;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.model.TotalStock;
import com.ncut.wms.user.dao.UserDAO;
import com.ncut.wms.user.model.User;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.system.Tools;

@Service("returnedService")
public class ReturnedService {

	/* ======以下业务逻辑======== */
	/**
	 * 获得退货单号
	 * @param date
	 * @return
	 */
	public String getOrderCode(String date) {
		String head = "THJH";
		String code = date.replaceAll("-", "");
		String hql = "select max(prt.prtId) from PurchaseReturnedTotal as prt where prt.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<PurchaseReturnedTotal> list = prtDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	/**
	 * 存储进货退货单
	 * @param rDTO
	 */
	public void savePurchaseReturn(ReturnedDTO rDTO) {
		//对退货总单进行赋值
		String inStockId = rDTO.getOrderId();
		InStock inStock = inStockDAO.findById(inStockId);
		PurchaseReturnedTotal prt = new PurchaseReturnedTotal();
		BeanUtils.copyProperties(rDTO, prt);
		
		String date = rDTO.getCreateDate().substring(0, 10);
		String prtId = this.getOrderCode(date);
		prt.setPrtId(prtId);
		prt.setInStockId(inStock.getInStockId());
		
		//对退货详单进行赋值
		//对前台字符串进行转化
		JSONArray jArr = JSONArray.fromObject(rDTO.getDetailOrder());
		List<PurchaseReturnedDetail> prdList = new ArrayList<PurchaseReturnedDetail>();
		for(int i=0; i<jArr.size(); i++) {
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			Integer returnedAmount = jObj.getInt("returnedAmount");
			if(returnedAmount != 0) {
				Integer inStockgoodsId = jObj.getInt("detailId");
				Integer commodityId = jObj.getInt("commodityId");
				Double returnedPrice = jObj.getDouble("price");
				Double totalPrice = returnedPrice * returnedAmount;
				
				PurchaseReturnedDetail prd = new PurchaseReturnedDetail();
				prd.setPrtId(prtId);
				prd.setInStockgoodsId(inStockgoodsId);
				prd.setCommodityId(commodityId);
				prd.setReturnedPrice(returnedPrice);
				prd.setReturnedAmount(returnedAmount);
				prd.setTotalPrice(totalPrice);
				prdList.add(prd);
			}
		}
		
		//对总单和详单进行存储
		prtDAO.add(prt);
		for(PurchaseReturnedDetail prd:prdList) {
			prdDAO.add(prd);
			
			//入库单的退货量进行修改
			InStockgoods ig = igDAO.load(prd.getInStockgoodsId());
			Integer returnedAmount = ig.getReturnedAmount() + prd.getReturnedAmount();
			ig.setReturnedAmount(returnedAmount);
			igDAO.update(ig);
			
			//进货单的退货量进行修改
			Purchasegoods pg = pgDAO.load(ig.getPurchasegoodsId());
			returnedAmount = pg.getReturnedAmount() + prd.getReturnedAmount();
			pg.setReturnedAmount(returnedAmount);
			pgDAO.update(pg);
			
			//中间表的退货量进行修改
			ShelfRemain sr = srDAO.findByOrderIdAndDetailId(prt.getInStockId(),prd.getInStockgoodsId());
			Integer visibleStock = sr.getVisibleRemain() - prd.getReturnedAmount();
			sr.setVisibleRemain(visibleStock);
			srDAO.update(sr);
			
			//库存总单的退货量进行修改
			TotalStock ts = tsDAO.findByCommodityId(prd.getCommodityId());
			visibleStock = ts.getVisibleStock() - prd.getReturnedAmount();
			ts.setVisibleStock(visibleStock);
			tsDAO.update(ts);
			
			//库存详单的退货量进行修改
			Stock stock = stockDAO.findByCommodityAndStorage(ig.getCommodityId(), ig.getStorageId());
			visibleStock = stock.getVisibleStock() - prd.getReturnedAmount();
			stock.setVisibleStock(visibleStock);
			stockDAO.update(stock);
			
		}
		
	}
	
	/**
	 * easyui datagrid展示退货总表
	 * @param rDTO
	 * @return
	 */
	public DataGrid<ReturnedDTO> purchaseReturnTotalGrid(ReturnedDTO rDTO) {
		DataGrid<ReturnedDTO> dg = new DataGrid<ReturnedDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from PurchaseReturnedTotal prt where 1=1 and stockState=0";
		
		if(rDTO.getBeginDate()!=null && !"".equals(rDTO.getBeginDate().trim())){
			hql+=" and prt.createDate between :beginDate and :endDate";
			map.put("beginDate", rDTO.getBeginDate().trim());
			map.put("endDate", rDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(rDTO.getSort()!=null){
			hql+=" order by "+rDTO.getSort()+" "+rDTO.getOrder();
		}
		List<PurchaseReturnedTotal> prtList = prtDAO.list(hql, map, rDTO.getPage(), rDTO.getRows());
		List<ReturnedDTO> prtDTOList = new ArrayList<ReturnedDTO>();
		for(PurchaseReturnedTotal prt:prtList){
			ReturnedDTO prtDTO = new ReturnedDTO();
			BeanUtils.copyProperties(prt, prtDTO);
			prtDTO.setOrderId(prt.getPrtId());
			
			//插入一些需要的数据
			User u = uDAO.load(prt.getUserId());
			prtDTO.setUserName(u.getUsername());
			
			prtDTOList.add(prtDTO);
		}
		dg.setTotal(prtDAO.count(totalHql, map));
		dg.setRows(prtDTOList);
		return dg;
	}
	
	public DataGrid<ReturnedDTO> purchaseReturnQueryGrid(ReturnedDTO rDTO) {
		DataGrid<ReturnedDTO> dg = new DataGrid<ReturnedDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from PurchaseReturnedTotal prt where 1=1";
		
		if(rDTO.getBeginDate()!=null && !"".equals(rDTO.getBeginDate().trim())){
			hql+=" and prt.createDate between :beginDate and :endDate";
			map.put("beginDate", rDTO.getBeginDate().trim());
			map.put("endDate", rDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(rDTO.getSort()!=null){
			hql+=" order by "+rDTO.getSort()+" "+rDTO.getOrder();
		}
		List<PurchaseReturnedTotal> prtList = prtDAO.list(hql, map, rDTO.getPage(), rDTO.getRows());
		List<ReturnedDTO> prtDTOList = new ArrayList<ReturnedDTO>();
		for(PurchaseReturnedTotal prt:prtList){
			ReturnedDTO prtDTO = new ReturnedDTO();
			BeanUtils.copyProperties(prt, prtDTO);
			prtDTO.setOrderId(prt.getPrtId());
			
			//插入一些需要的数据
			User u = uDAO.load(prt.getUserId());
			prtDTO.setUserName(u.getUsername());
			
			prtDTOList.add(prtDTO);
		}
		dg.setTotal(prtDAO.count(totalHql, map));
		dg.setRows(prtDTOList);
		return dg;
	}
	
	public DataGrid<ReturnedDTO> purchaseReturnDetailGrid(ReturnedDTO rDTO) {
		DataGrid<ReturnedDTO> dg = new DataGrid<ReturnedDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from PurchaseReturnedDetail prd where 1=1";
		
		if(rDTO.getOrderId()!=null && !"".equals(rDTO.getOrderId().trim())){
			hql+=" and prd.prtId = :prtId";
			map.put("prtId", rDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(rDTO.getSort()!=null){
			hql+=" order by "+rDTO.getSort()+" "+rDTO.getOrder();
		}
		List<PurchaseReturnedDetail> prdList = prdDAO.list(hql, map, rDTO.getPage(), rDTO.getRows());
		List<ReturnedDTO> prdDTOList = new ArrayList<ReturnedDTO>();
		for(PurchaseReturnedDetail prd:prdList){
			ReturnedDTO prdDTO = new ReturnedDTO();
			BeanUtils.copyProperties(prd, prdDTO);
			prdDTO.setOrderId(prd.getPrtId());
			prdDTO.setDetailId(prd.getPrdId());
			prdDTO.setDetailPrice(prd.getReturnedPrice());
			
			//插入一些需要的数据
			Commodity c = cDAO.load(prd.getCommodityId());
			prdDTO.setCommodityName(c.getCommodityName());
			
			prdDTOList.add(prdDTO);
		}
		dg.setTotal(prtDAO.count(totalHql, map));
		dg.setRows(prdDTOList);
		return dg;
	}
	
	public DataGrid<ReturnedDTO> purchaseReturnDetailQuery(ReturnedDTO rDTO) {
		DataGrid<ReturnedDTO> dg = new DataGrid<ReturnedDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from PurchaseReturnedDetail prd where 1=1";
		
		if(rDTO.getOrderId()!=null && !"".equals(rDTO.getOrderId().trim())){
			hql+=" and prd.prtId = :prtId";
			map.put("prtId", rDTO.getOrderId().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(rDTO.getSort()!=null){
			hql+=" order by "+rDTO.getSort()+" "+rDTO.getOrder();
		}
		List<PurchaseReturnedDetail> prdList = prdDAO.list(hql, map, rDTO.getPage(), rDTO.getRows());
		List<ReturnedDTO> prdDTOList = new ArrayList<ReturnedDTO>();
		for(PurchaseReturnedDetail prd:prdList){
			ReturnedDTO prdDTO = new ReturnedDTO();
			BeanUtils.copyProperties(prd, prdDTO);
			prdDTO.setOrderId(prd.getPrtId());
			prdDTO.setDetailId(prd.getPrdId());
			prdDTO.setDetailPrice(prd.getReturnedPrice());
			
			//插入一些需要的数据
			Commodity c = cDAO.load(prd.getCommodityId());
			prdDTO.setCommodityName(c.getCommodityName());
			
			prdDTOList.add(prdDTO);
		}
		dg.setTotal(prtDAO.count(totalHql, map));
		dg.setRows(prdDTOList);
		return dg;
	}

	
	
	/* ======以下声明======== */
	private PurchaseReturnedTotalDAO prtDAO;
	private PurchaseReturnedDetailDAO prdDAO;
	private InStockDAO inStockDAO;
	private InStockgoodsDAO igDAO;
	private PurchasegoodsDAO pgDAO;
	private ShelfRemainDAO srDAO;
	private StockDAO stockDAO;
	private TotalStockDAO tsDAO;
	private UserDAO uDAO;
	private CommodityDAO cDAO;

	@Resource
	public void setPrtDAO(PurchaseReturnedTotalDAO prtDAO) {
		this.prtDAO = prtDAO;
	}

	@Resource
	public void setInStockDAO(InStockDAO inStockDAO) {
		this.inStockDAO = inStockDAO;
	}

	@Resource
	public void setPrdDAO(PurchaseReturnedDetailDAO prdDAO) {
		this.prdDAO = prdDAO;
	}

	@Resource
	public void setIgDAO(InStockgoodsDAO igDAO) {
		this.igDAO = igDAO;
	}

	@Resource
	public void setPgDAO(PurchasegoodsDAO pgDAO) {
		this.pgDAO = pgDAO;
	}

	@Resource
	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	@Resource
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
	}

	@Resource
	public void setSrDAO(ShelfRemainDAO srDAO) {
		this.srDAO = srDAO;
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
