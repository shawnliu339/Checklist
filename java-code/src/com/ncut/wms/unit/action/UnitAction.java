package com.ncut.wms.unit.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.commodity.service.CommodityService;
import com.ncut.wms.unit.model.Unit;
import com.ncut.wms.unit.service.UnitService;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("unitAction")
@Scope("prototype")
public class UnitAction extends ActionSupport implements
ModelDriven<Unit> {

	/*======以下业务逻辑========*/
	/**
	 * 跳转计量单位管理页面
	 * 
	 * @return
	 */
	public String managementPage() {

		return "managementPage";
	}
	
	public String addUnit() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			unitService.addUnit(unit);
			json.setSuccess(true);
			json.setMessage("添加计量单位成功！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加计量单位失败！");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String deleteUnit() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			unitService.deleteUnit(unit);
			json.setSuccess(true);
			json.setMessage("删除计量单位成功！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("删除计量单位失败！");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String editUnit() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			unitService.editUnit(unit);
			json.setSuccess(true);
			json.setMessage("修改计量单位成功！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("修改计量单位失败！");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getUnitList() throws IOException {

		// 获得request对象，获取页面数据
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得当前页
		int currentPage = Integer.parseInt(request.getParameter("page"));
		// 获得一页显示的数据数量
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		// 获取排序的方式
		String order = request.getParameter("order") == null ? "" : request
				.getParameter("order");
		// 获取排序的字段
		String sort = request.getParameter("sort") == null ? "" : request
				.getParameter("sort");

		// 查询数据存入map
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("unitName", unit.getUnitName());
		m.put("sort", sort);
		m.put("order", order);

		// 通过分页获得对应商品列表的json字符串
		String unitList = unitService.getUnitListJsonByPage(
				currentPage, pageSize, m);
		// 获得response对象,响应页面:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(unitList);
		return NONE;
	}

	/* ======以下声明======== */
	private UnitService unitService;
	private Unit unit = new Unit();
	
	@Override
	public Unit getModel() {
		if (unit.getUnitName() == null) {
			unit.setUnitName("");
		}
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	@Resource
	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}
	
	

}
