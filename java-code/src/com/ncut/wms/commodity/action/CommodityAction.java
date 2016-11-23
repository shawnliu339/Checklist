package com.ncut.wms.commodity.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.commodity.dto.CommodityDTO;
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.commodity.service.CommodityService;
import com.ncut.wms.supplier.dto.SupplierDTO;
import com.ncut.wms.unit.service.UnitService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("commodityAction")
@Scope("prototype")
public class CommodityAction extends ActionSupport implements
		ModelDriven<CommodityDTO> {

	

	/* ======以下业务逻辑======== */
	/**
	 * 跳转商品信息管理页面
	 * 
	 * @return
	 */
	public String managementPage() {

		return "managementPage";
	}

	/**
	 * 获取商品信息列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getDatagrid() throws IOException {

		DataGrid<CommodityDTO> dg = commodityService.datagrid(commodityDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getCommodityList() {
		
		List<Commodity> commodityList = commodityService.getCommodityList();
		String csJson = JSONArray.fromObject(commodityList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(csJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getCommodity() {
		
		CommodityDTO cDTO = commodityService.findById(commodityDTO.getCommodityId());
		String cJson = JSONObject.fromObject(cDTO).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(cJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 获取计量单位列表
	 * 
	 * @return
	 */
	public String getUnitList() {

		String unitList = unitService.getUnitListJson();

		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(unitList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String add(){
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			BeanUtils.copyProperties(commodityDTO, commodity);
			commodityService.add(commodity);
			json.setSuccess(true);
			json.setMessage("添加供应商成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加供应商失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String update(){
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			BeanUtils.copyProperties(commodityDTO, commodity);
			commodityService.update(commodity);
			json.setSuccess(true);
			json.setMessage("修改供应商成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("修改供应商失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String delete(){
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			commodityService.delete(commodityDTO);
			json.setSuccess(true);
			json.setMessage("删除供应商成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("删除供应商失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/* ======以下声明======== */
	private CommodityService commodityService;
	private UnitService unitService;
	private Commodity commodity = new Commodity();
	private CommodityDTO commodityDTO = new CommodityDTO();

	@Resource
	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}

	@Resource
	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public void setCommodityDTO(CommodityDTO commodityDTO) {
		this.commodityDTO = commodityDTO;
	}

	@Override
	public CommodityDTO getModel() {

		return commodityDTO;
	}

}
