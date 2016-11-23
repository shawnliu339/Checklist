package com.ncut.wms.purchase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.purchase.dao.PurchaseDAO;
import com.ncut.wms.purchase.dao.PurchasegoodsDAO;
import com.ncut.wms.purchase.dto.PurchaseDTO;
import com.ncut.wms.purchase.dto.PurchasegoodsDTO;
import com.ncut.wms.purchase.model.Purchasegoods;
import com.ncut.wms.util.easyui.DataGrid;

@Service("purchasegoodsService")
public class PurchasegoodsService {

	/* ======以下业务逻辑======== */
	public void add(List<Purchasegoods> pgList) {
		for(Purchasegoods pg : pgList) {
			pgDAO.add(pg);
		}
	}
	
	public Purchasegoods findById(String purchaseId) {

		return pgDAO.load(Integer.parseInt(purchaseId));
	}
	
	public DataGrid<PurchasegoodsDTO> datagrid(PurchaseDTO pDTO) {
		DataGrid<PurchasegoodsDTO> dg = new DataGrid<PurchasegoodsDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Purchasegoods pg";
		if(pDTO.getPurchaseId()!=null && !"".equals(pDTO.getPurchaseId().trim())){
			hql+=" where pg.purchaseId = :purchaseId";
			map.put("purchaseId", pDTO.getPurchaseId().trim());
		}
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(pDTO.getSort()!=null){
			hql+=" order by "+pDTO.getSort()+" "+pDTO.getOrder();
		}
		List<Purchasegoods> pgList = pgDAO.list(hql, map, pDTO.getPage(), pDTO.getRows());
		List<PurchasegoodsDTO> pgDTOList = new ArrayList<PurchasegoodsDTO>();
		for(Purchasegoods pg:pgList){
			PurchasegoodsDTO pgoodsDTO = new PurchasegoodsDTO();
			BeanUtils.copyProperties(pg, pgoodsDTO);
			
			//插入一些需要的数据
			pgoodsDTO.setCommodityName(cDAO.load(pgoodsDTO.getCommodityId()).getCommodityName());
			
			pgDTOList.add(pgoodsDTO);
		}
		dg.setTotal(pgDAO.count(totalHql, map));
		dg.setRows(pgDTOList);
		return dg;
	}
	
	/* ======以下声明======== */
	private PurchaseDAO pDAO;
	private PurchasegoodsDAO pgDAO;
	private CommodityDAO cDAO;

	@Resource
	public void setpDAO(PurchaseDAO pDAO) {
		this.pDAO = pDAO;
	}

	@Resource
	public void setPgDAO(PurchasegoodsDAO pgDAO) {
		this.pgDAO = pgDAO;
	}

	@Resource
	public void setcDAO(CommodityDAO cDAO) {
		this.cDAO = cDAO;
	}

}
