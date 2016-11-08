package com.ncut.wms.stock.service;

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
import com.ncut.wms.purchase.model.Purchase;
import com.ncut.wms.purchase.model.Purchasegoods;
import com.ncut.wms.stock.dao.InStockDAO;
import com.ncut.wms.stock.dao.ShelfRemainDAO;
import com.ncut.wms.stock.dao.InStockgoodsDAO;
import com.ncut.wms.stock.dao.StockDAO;
import com.ncut.wms.stock.dao.TotalStockDAO;
import com.ncut.wms.stock.dto.InStockDTO;
import com.ncut.wms.stock.model.InStock;
import com.ncut.wms.stock.model.ShelfRemain;
import com.ncut.wms.stock.model.InStockgoods;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.model.TotalStock;
import com.ncut.wms.supplier.dao.SupplierDAO;
import com.ncut.wms.supplier.model.Supplier;
import com.ncut.wms.user.dao.UserDAO;
import com.ncut.wms.user.model.User;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.system.Tools;

@Service("inStockService")
public class InStockService {

	/* ======以下业务逻辑======== */
	public String getOrderCode(String date) {
		String head = "RKJH";
		String code = date.replaceAll("-", "");
		String hql = "select max(t.inStockId) from InStock as t where t.createDate between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		List<Purchase> list = pDAO.list(hql);
		Object obj = list.get(0);
		if(obj!=null)
			return head+code+Tools.formatCode(obj.toString());
		return head+code+"0001";
	}
	
	public void savePurchase(InStockDTO iDTO) {
		
		//对入库总单进行赋值
		String purchaseId = iDTO.getIds();
		Purchase purchase = pDAO.findById(purchaseId);
		InStock inStock = new InStock();
		BeanUtils.copyProperties(purchase, inStock);
		//获得订单号并赋值
		String date = iDTO.getCreateDate().substring(0, 10);
		String inStockId = this.getOrderCode(date);
		inStock.setInStockId(inStockId);
		inStock.setCreateDate(iDTO.getCreateDate());
		
		//对入库详单进行赋值
		//获取前台详单的json字符串并转换成对象
		JSONArray jArr = JSONArray.fromObject(iDTO.getInStockgoods());
		List<InStockgoods> igList = new ArrayList<InStockgoods>();
		for(int i=0; i<jArr.size(); i++) {
			//根据前台的详单ID查找对应的进货详单
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			Integer purchasegoodsId = jObj.getInt("id");
			Purchasegoods pg = pgDAO.load(purchasegoodsId);
			
			//将进货详单的内容赋值给入库详单
			//赋值库存总表ID
			//赋值仓库ID
			//赋值架位
			InStockgoods ig = new InStockgoods();
			BeanUtils.copyProperties(pg, ig);
			ig.setInStockId(inStockId);
			ig.setPurchasegoodsId(purchasegoodsId);
			ig.setStorageId(jObj.getInt("storageId"));
			ig.setShelfId(jObj.getInt("shelfId"));
			igList.add(ig);
			
		}
				
		//对总单和详单进行存储
		iDAO.add(inStock);
		for(InStockgoods ig : igList) {
			//对入库表进行存储
			igDAO.add(ig);
			//对中间表进行存储
			ShelfRemain sr = new ShelfRemain();
			sr.setOrderId(ig.getInStockId());
			sr.setDetailId(ig.getInStockgoodsId());
			sr.setVisibleRemain(ig.getAmount());
			sr.setRealRemain(ig.getAmount());
			srDAO.add(sr);
			
			//对库存信息进行修改:首先搜索商品编号和仓库编号，如果有则修改该库存量，
			//如果没有则添加该库存信息。同时注意实际库存量应该为入库数量减去退货数量
			Stock stock = sDAO.findByCommodityAndStorage(ig.getCommodityId(), ig.getStorageId());
			if(stock != null) {
				//有则修改原表
				Integer inStockAmount = stock.getInStock() + ig.getAmount();
				Integer visibleStockAmount = stock.getVisibleStock() + ig.getAmount();
				Integer stockAmount = stock.getStockAmount() + ig.getAmount();
				
				stock.setInStock(inStockAmount);
				stock.setVisibleStock(visibleStockAmount);
				stock.setStockAmount(stockAmount);
				sDAO.update(stock);
				
				//对库存总表中的数据进行修改
				TotalStock ts = tsDAO.findByCommodityId(ig.getCommodityId());
				//减少进货量
				Integer purchaseAmount = ts.getPurchase() - ig.getAmount();
				//增加入货、可销售、总量数据
				inStockAmount = ts.getInStock() + ig.getAmount();
				visibleStockAmount = ts.getVisibleStock() + ig.getAmount();
				stockAmount = ts.getStockAmount() + ig.getAmount();
				
				ts.setPurchase(purchaseAmount);
				ts.setInStock(inStockAmount);
				ts.setVisibleStock(visibleStockAmount);
				ts.setStockAmount(stockAmount);
				tsDAO.update(ts);
				
			} else {
				//没有则添加一张新表
				Integer commodityId = ig.getCommodityId();
				Integer inStockAmount = ig.getAmount();
				Integer storageId = ig.getStorageId();
				
				stock = new Stock();
				stock.setCommodityId(commodityId);
				stock.setInStock(inStockAmount);
				stock.setOutStock(0);
				stock.setVisibleStock(inStockAmount);
				stock.setStockAmount(inStockAmount);
				stock.setStorageId(storageId);
				sDAO.add(stock);
				
				//对库存总表中的数据进行修改
				TotalStock ts = tsDAO.findByCommodityId(commodityId);
				//减少进货量
				Integer purchaseAmount = ts.getPurchase() - ig.getAmount();
				//增加入货、可销售、总量数据
				inStockAmount = ts.getInStock() + ig.getAmount();
				Integer visibleStockAmount = ts.getVisibleStock() + ig.getAmount();
				Integer stockAmount = ts.getStockAmount() + ig.getAmount();
				
				ts.setPurchase(purchaseAmount);
				ts.setInStock(inStockAmount);
				ts.setVisibleStock(visibleStockAmount);
				ts.setStockAmount(stockAmount);
				tsDAO.update(ts);
			}
			
			
		}
		
		//进货总单的库存状态进行修改和存储
		purchase.setStockState(1);
		pDAO.update(purchase);
		
		
	}
	
	public DataGrid<InStockDTO> querygrid(InStockDTO iDTO) {
		
		DataGrid<InStockDTO> dg = new DataGrid<InStockDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from InStock inStock where 1=1";
		
		if(iDTO.getBeginDate()!=null && !"".equals(iDTO.getBeginDate().trim())){
			hql+=" and inStock.createDate between :beginDate and :endDate";
			map.put("beginDate", iDTO.getBeginDate().trim());
			map.put("endDate", iDTO.getEndDate().trim());
		}
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(iDTO.getSort()!=null){
			hql+=" order by "+iDTO.getSort()+" "+iDTO.getOrder();
		}
		List<InStock> iList = iDAO.list(hql, map, iDTO.getPage(), iDTO.getRows());
		List<InStockDTO> iDTOList = new ArrayList<InStockDTO>();
		for(InStock i:iList){
			InStockDTO inStockDTO = new InStockDTO();
			BeanUtils.copyProperties(i, inStockDTO);
			
			//插入一些需要的数据
			Supplier s = supplierDAO.load(inStockDTO.getSupplierId());
			inStockDTO.setSupplierName(s.getSupplierName());
			
			User u = uDAO.load(inStockDTO.getUserId());
			inStockDTO.setUserName(u.getUsername());
			
			iDTOList.add(inStockDTO);
		}
		dg.setTotal(iDAO.count(totalHql, map));
		dg.setRows(iDTOList);
		return dg;
	}
	
	/* ======以下声明======== */
	private InStockDAO iDAO;
	private InStockgoodsDAO igDAO;
	private ShelfRemainDAO srDAO;
	private TotalStockDAO tsDAO;
	private PurchaseDAO pDAO;
	private PurchasegoodsDAO pgDAO;
	private StockDAO sDAO;
	private SupplierDAO supplierDAO;
	private UserDAO uDAO;
	
	@Resource
	public void setiDAO(InStockDAO iDAO) {
		this.iDAO = iDAO;
	}
	
	@Resource
	public void setIgDAO(InStockgoodsDAO igDAO) {
		this.igDAO = igDAO;
	}

	@Resource
	public void setpDAO(PurchaseDAO pDAO) {
		this.pDAO = pDAO;
	}

	@Resource
	public void setPgDAO(PurchasegoodsDAO pgDAO) {
		this.pgDAO = pgDAO;
	}

	@Resource
	public void setsDAO(StockDAO sDAO) {
		this.sDAO = sDAO;
	}

	@Resource
	public void setSupplierDAO(SupplierDAO supplierDAO) {
		this.supplierDAO = supplierDAO;
	}

	@Resource
	public void setuDAO(UserDAO uDAO) {
		this.uDAO = uDAO;
	}

	@Resource
	public void setTsDAO(TotalStockDAO tsDAO) {
		this.tsDAO = tsDAO;
	}

	@Resource
	public void setSrDAO(ShelfRemainDAO srDAO) {
		this.srDAO = srDAO;
	}

	

	
}
