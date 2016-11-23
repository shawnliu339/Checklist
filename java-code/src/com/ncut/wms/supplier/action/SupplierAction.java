package com.ncut.wms.supplier.action;

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

import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.supplier.dto.SupplierDTO;
import com.ncut.wms.supplier.model.Supplier;
import com.ncut.wms.supplier.service.SupplierService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("supplierAction")
@Scope("prototype")
public class SupplierAction extends ActionSupport implements
		ModelDriven<SupplierDTO> {

	/* ======以下业务逻辑======== */
	public String managementPage() {

		return "managementPage";
	}

	public String getDatagrid() {

		DataGrid<SupplierDTO> dg = supplierService.datagrid(supplierDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getSupplierList() {
		
		List<Supplier> supplierList = supplierService.getSupplierList();
		String suppliersJson = JSONArray.fromObject(supplierList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(suppliersJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	
	public String add(){
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			BeanUtils.copyProperties(supplierDTO, supplier);
			supplierService.add(supplier);
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
			BeanUtils.copyProperties(supplierDTO, supplier);
			supplierService.update(supplier);
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
			supplierService.delete(supplierDTO);
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
	
	public String toExcel(){
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			supplierService.toExcel();
			json.setSuccess(true);
			json.setMessage("导出excel成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("导出excel失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/* ======以下声明======== */
	private Supplier supplier = new Supplier();
	private SupplierDTO supplierDTO = new SupplierDTO();
	private SupplierService supplierService;

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setSupplierDTO(SupplierDTO supplierDTO) {
		this.supplierDTO = supplierDTO;
	}

	@Resource
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@Override
	public SupplierDTO getModel() {
		if (supplierDTO.getSupplierName() == null) {
			supplierDTO.setSupplierName("");
		}
		return supplierDTO;
	}

}
