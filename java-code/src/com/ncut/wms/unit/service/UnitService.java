package com.ncut.wms.unit.service;

import java.util.*;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dto.CommodityDTO;
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.unit.dao.UnitDAO;
import com.ncut.wms.unit.model.Unit;

@Service("unitService")
public class UnitService {
	
	/* ======以下业务逻辑======== */
	public void addUnit(Unit unit) {
		unitDAO.add(unit);
	}
	
	public void deleteUnit(Unit unit) {
		unitDAO.delete(unit.getUnitId());
	}

	public void editUnit(Unit unit) {
		unitDAO.update(unit);
	}
	
	public String getUnitList() {
		return null;
	}
	
	public String getUnitListJson() {

		List<Unit> unitList = unitDAO.findAll();
		
		//向下拉菜单中添加一个默认项
		Unit unitDefault = new Unit();
		unitDefault.setUnitId(0);
		unitDefault.setUnitName("空");
		unitList.add(0, unitDefault);
		
		String unitListStr = JSONArray.fromObject(unitList).toString();
		return unitListStr;
	}
	
	public String getUnitListJsonByPage(int currentPage, int pageSize,
			Map<String, Object> m) {
		List<Unit> unitList = unitDAO.findByPagination(
				currentPage, pageSize, m);

		// 拼Jason字符串 {"total":total , "rows":[{},{}]}
		String unitListStr = "{\"total\":" + this.total() + " , \"rows\":"
				+ JSONArray.fromObject(unitList).toString() + "}";
		return unitListStr;
	}
	
	public int total() {
		return unitDAO.count("select count(*) from Unit");
	}

	/* ======以下声明======== */
	private UnitDAO unitDAO;
	
	@Resource
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}

}
