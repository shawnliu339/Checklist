package com.ncut.wms.purchase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.purchase.dao.PurchaseDAO;
import com.ncut.wms.purchase.dao.PurchasegoodsDAO;
import com.ncut.wms.purchase.dto.PurchaseDTO;
import com.ncut.wms.purchase.model.Purchase;
import com.ncut.wms.purchase.model.Purchasegoods;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.model.TotalStock;
import com.ncut.wms.supplier.dao.SupplierDAO;
import com.ncut.wms.supplier.model.Supplier;
import com.ncut.wms.user.dao.UserDAO;
import com.ncut.wms.user.model.User;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.system.Tools;

@Service("PurchaseService")
public class PurchaseService {

	/* ======以下业务逻辑======== */
	
	/**
	 * 生成订单编号
	 * @param date 当前时间
	 * @return
	 */
	public String getOrderCode(String date) {
		String head = "JHDD";
		String code = date.replaceAll("-", "");
		String hql = "select max(t.purchaseId) from Purchase as t where t.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<Purchase> list = pDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	public DataGrid<PurchaseDTO> datagrid(PurchaseDTO pDTO) {
		DataGrid<PurchaseDTO> dg = new DataGrid<PurchaseDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Purchase p where 1=1 and p.stockState=0";
		
		if(pDTO.getBeginDate()!=null && !"".equals(pDTO.getBeginDate().trim())){
			hql+=" and p.createDate between :beginDate and :endDate";
			map.put("beginDate", pDTO.getBeginDate().trim());
			map.put("endDate", pDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(pDTO.getSort()!=null){
			hql+=" order by "+pDTO.getSort()+" "+pDTO.getOrder();
		}
		List<Purchase> ps = pDAO.list(hql, map, pDTO.getPage(), pDTO.getRows());
		List<PurchaseDTO> pDTOs = new ArrayList<PurchaseDTO>();
		for(Purchase p:ps){
			PurchaseDTO purchaseDTO = new PurchaseDTO();
			BeanUtils.copyProperties(p, purchaseDTO);
			
			//插入一些需要的数据
			Supplier s = sDAO.load(purchaseDTO.getSupplierId());
			purchaseDTO.setSupplierName(s.getSupplierName());
			
			User u = uDAO.load(purchaseDTO.getUserId());
			purchaseDTO.setUserName(u.getUsername());
			
			pDTOs.add(purchaseDTO);
		}
		dg.setTotal(pDAO.count(totalHql, map));
		dg.setRows(pDTOs);
		return dg;
	}
	
	public DataGrid<PurchaseDTO> querygrid(PurchaseDTO pDTO) {
		DataGrid<PurchaseDTO> dg = new DataGrid<PurchaseDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		String head = "select p ";
		String hql = "from Purchase p, Supplier s where 1=1 and p.supplierId = s.supplierId";
		
		if(pDTO.getBeginDate()!=null && !"".equals(pDTO.getBeginDate().trim())){
			hql+=" and p.createDate between :beginDate and :endDate";
			map.put("beginDate", pDTO.getBeginDate().trim());
			map.put("endDate", pDTO.getEndDate().trim());
		}
		
		if(pDTO.getSupplierName()!=null && !"".equals(pDTO.getSupplierName().trim())){
			hql+=" and s.supplierName like :supplierName";
			map.put("supplierName", "%"+pDTO.getSupplierName().trim()+"%");
		}
		
		if(pDTO.getStockState()!=null){
			hql+=" and p.stockState = :stockState";
			map.put("stockState", pDTO.getStockState());
		}
		
		String totalHql = "select count(p) "+hql;
		hql = head + hql;
		//实现排序
		if(pDTO.getSort()!=null){
			hql+=" order by "+pDTO.getSort()+" "+pDTO.getOrder();
		}
		List<Purchase> ps = pDAO.list(hql, map, pDTO.getPage(), pDTO.getRows());
		List<PurchaseDTO> pDTOs = new ArrayList<PurchaseDTO>();
		for(Purchase p:ps){
			PurchaseDTO purchaseDTO = new PurchaseDTO();
			BeanUtils.copyProperties(p, purchaseDTO);
			
			//插入一些需要的数据
			Supplier s = sDAO.load(purchaseDTO.getSupplierId());
			purchaseDTO.setSupplierName(s.getSupplierName());
			
			User u = uDAO.load(purchaseDTO.getUserId());
			purchaseDTO.setUserName(u.getUsername());
			
			pDTOs.add(purchaseDTO);
		}
		dg.setTotal(pDAO.count(totalHql, map));
		dg.setRows(pDTOs);
		return dg;
	}
	
	public void add(Purchase p) {
		pDAO.add(p);
		
	}
	
	public void saveOrder(PurchaseDTO pDTO) {

		JSONArray jArr = JSONArray.fromObject(pDTO.getPgs());
		List<Purchasegoods> pgList = new ArrayList<Purchasegoods>();
		//格式化前台数据
		for(int i=0; i<jArr.size(); i++) {
			
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			
			Purchasegoods pg = new Purchasegoods();
			//对商品详单赋值
			pg.setPurchaseId(jObj.getString("purchaseId"));
			pg.setCommodityId(jObj.getInt("commodityId"));
			if(jObj.getString("price") == null || "".equals(jObj.getString("price"))) {
				pg.setPrice(0.0);
			} else {
				pg.setPrice(jObj.getDouble("price"));
			}
			if(jObj.getString("amount") == null || "".equals(jObj.getString("amount"))) {
				pg.setAmount(0);
			} else {
				pg.setAmount(jObj.getInt("amount"));
			}
			pg.setReturnedAmount(0);
			pg.setTotalPrice(jObj.getDouble("totalPrice"));
			pgList.add(pg);
		}
		
		Purchase p = new Purchase();
		BeanUtils.copyProperties(pDTO, p);
		pDAO.add(p);
		pgDAO.add(pgList);
		
		//添加库存总表中的预定量
		for(Purchasegoods pg : pgList) {
			TotalStock ts = tsDAO.findByCommodityId(pg.getCommodityId());
			if(ts != null) {
				Integer purchaseAmount = ts.getPurchase();
				purchaseAmount += pg.getAmount();
				ts.setPurchase(purchaseAmount);
				tsDAO.update(ts);
			} else {
				ts = new TotalStock();
				ts.setCommodityId(pg.getCommodityId());
				ts.setPurchase(pg.getAmount());
				tsDAO.add(ts);
			}
		}
	}

	/* ======以下声明======== */
	private PurchaseDAO pDAO;
	private PurchasegoodsDAO pgDAO;
	private SupplierDAO sDAO;
	private UserDAO uDAO;
	private TotalStockDAO tsDAO;

	@Resource
	public void setpDAO(PurchaseDAO pDAO) {
		this.pDAO = pDAO;
	}

	@Resource
	public void setPgDAO(PurchasegoodsDAO pgDAO) {
		this.pgDAO = pgDAO;
	}

	@Resource
	public void setsDAO(SupplierDAO sDAO) {
		this.sDAO = sDAO;
	}

	@Resource
	public void setuDAO(UserDAO uDAO) {
		this.uDAO = uDAO;
	}

	@Resource
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
	}

	

	
}
