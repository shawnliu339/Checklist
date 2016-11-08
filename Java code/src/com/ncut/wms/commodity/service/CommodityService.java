package com.ncut.wms.commodity.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dao.CommodityCategoryDAO;
import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.commodity.dto.CommodityDTO;
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.commodity.model.CommodityCategory;
import com.ncut.wms.supplier.dto.SupplierDTO;
import com.ncut.wms.unit.dao.UnitDAO;
import com.ncut.wms.unit.model.Unit;
import com.ncut.wms.util.easyui.DataGrid;

@Service("commodityService")
public class CommodityService {

	/* ======以下声明======== */
	private CommodityDAO commodityDAO;
	private UnitDAO unitDAO;
	private CommodityCategoryDAO cCDAO;

	@Resource
	public void setCommodityDAO(CommodityDAO commodityDAO) {
		this.commodityDAO = commodityDAO;
	}

	@Resource
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}

	@Resource
	public void setcCDAO(CommodityCategoryDAO cCDAO) {
		this.cCDAO = cCDAO;
	}

	/* ======以下业务逻辑======== */
	/**
	 * 获取商品 datagrid
	 * 
	 * @param commodityDTO
	 * @return 商品datagrid
	 */
	public DataGrid<CommodityDTO> datagrid(CommodityDTO commodityDTO) {
		DataGrid<CommodityDTO> dg = new DataGrid<CommodityDTO>();
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Commodity commodity where 1=1";
		if (commodityDTO.getCommodityId() != null) {
			hql += " and commodity.commodityId = :commodityId";
			map.put("commodityId", commodityDTO.getCommodityId());
		}
		if (commodityDTO.getCommodityName() != null
				&& !"".equals(commodityDTO.getCommodityName().trim())) {
			hql += " and commodity.commodityName like :commodityName";
			map.put("commodityName", "%"
					+ commodityDTO.getCommodityName().trim() + "%");
		}
		String totalHql = "select count(*) " + hql;
		// 实现排序
		if (commodityDTO.getSort() != null) {
			hql += " order by " + commodityDTO.getSort() + " "
					+ commodityDTO.getOrder();
		}
		List<Commodity> commodities = commodityDAO.list(hql, map,
				commodityDTO.getPage(), commodityDTO.getRows());
		List<CommodityDTO> cmdtrDTOs = new ArrayList<CommodityDTO>();
		for (Commodity cmdt : commodities) {
			CommodityDTO cmdtDTO = new CommodityDTO();
			BeanUtils.copyProperties(cmdt, cmdtDTO);
			// 设置分类和单位名称
			if (cmdt.getUnitId() != null && cmdt.getUnitId() != 0) {
				Integer unitId = cmdt.getUnitId();
				Unit unit = unitDAO.findById(unitId);
				cmdtDTO.setUnitName(unit.getUnitName());
			}
			if (cmdt.getCategoryId() != null && cmdt.getCategoryId() != 0) {
				CommodityCategory cc = cCDAO.load(cmdt.getCategoryId());
				cmdtDTO.setCategoryName(cc.getCname());
			}

			cmdtrDTOs.add(cmdtDTO);
		}
		dg.setTotal(commodityDAO.count(totalHql, map));
		dg.setRows(cmdtrDTOs);
		return dg;
	}

	public void add(Commodity commodity) {
		commodityDAO.save(commodity);
	}

	public void update(Commodity commodity) {
		commodityDAO.update(commodity);

	}
	
	public CommodityDTO findById(Integer id) {
		
		CommodityDTO cDTO = new CommodityDTO();
		Commodity commodity = commodityDAO.findById(id);
		if(commodity.getUnitId()!=null) {
			String unitName = unitDAO.findById(commodity.getUnitId()).getUnitName();
			cDTO.setUnitName(unitName);
		}
		
		BeanUtils.copyProperties(commodity, cDTO);
		
		return cDTO;
	}

	public void delete(CommodityDTO commodityDTO) {

		String ids[] = commodityDTO.getIds().split(",");

		for (int i = 0; i < ids.length; i++) {
			commodityDAO.delete(Integer.valueOf(ids[i]));
		}

	}

	public void delete(Integer id) {
		commodityDAO.delete(id);
	}

	public List<Commodity> getCommodityList() {
		List<Commodity> commodityList = commodityDAO.list("from Commodity");
		return commodityList;
	}

}
