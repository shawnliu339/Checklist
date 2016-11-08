package com.ncut.wms.commodity.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.commodity.dto.CommodityCategoryDTO;
import com.ncut.wms.commodity.model.CommodityCategory;
import com.ncut.wms.commodity.service.CommodityCategoryService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("cmdtCtgrAction")
@Scope("prototype")
public class CommodityCategoryAction extends ActionSupport implements
		ModelDriven<CommodityCategoryDTO> {

	/*========以下为声明部分========*/
	private CommodityCategoryService cmdtCtgrService;
	private CommodityCategory cmdCtgrt=new CommodityCategory();
	private CommodityCategoryDTO cmdtCtgrDTO;
	
	@Resource
	public void setCmdtCtgrService(CommodityCategoryService cmdtCtgrService) {
		this.cmdtCtgrService = cmdtCtgrService;
	}

	public void setCmdCtgrt(CommodityCategory cmdCtgrt) {
		this.cmdCtgrt = cmdCtgrt;
	}

	public void setCmdtCtgrDTO(CommodityCategoryDTO cmdtCtgrDTO) {
		this.cmdtCtgrDTO = cmdtCtgrDTO;
	}

	@Override
	public CommodityCategoryDTO getModel() {
		if(cmdtCtgrDTO == null) {
			cmdtCtgrDTO = new CommodityCategoryDTO();
		}
		return cmdtCtgrDTO;
	}
	
	/*========以下为逻辑部分========*/
	public String managementPage() {
		return "managementPage";
	}

	public void dgList(){
		DataGrid<CommodityCategoryDTO> dg = cmdtCtgrService.datagrid(cmdtCtgrDTO);
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=gbk");
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String add(){
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			BeanUtils.copyProperties(cmdtCtgrDTO, cmdCtgrt);
			cmdtCtgrService.add(cmdCtgrt);
			json.setSuccess(true);
			json.setMessage("添加商品分类成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加商品分类失败");
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
			BeanUtils.copyProperties(cmdtCtgrDTO, cmdCtgrt);
			cmdtCtgrService.update(cmdCtgrt);
			json.setSuccess(true);
			json.setMessage("修改商品分类成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("修改商品分类失败");
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
			BeanUtils.copyProperties(cmdtCtgrDTO, cmdCtgrt);
			cmdtCtgrService.delete(cmdCtgrt);
			json.setSuccess(true);
			json.setMessage("删除商品分类成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("删除商品分类失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getCategoryList() {

		List<CommodityCategory> cCList = cmdtCtgrService.getCategoryList();
		String str = JSONArray.fromObject(cCList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

}
