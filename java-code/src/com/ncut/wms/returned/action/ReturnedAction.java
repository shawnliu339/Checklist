package com.ncut.wms.returned.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.purchase.dto.PurchaseDTO;
import com.ncut.wms.returned.dto.ReturnedDTO;
import com.ncut.wms.returned.service.ReturnedService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("returnedAction")
@Scope("prototype")
public class ReturnedAction extends ActionSupport implements ModelDriven<ReturnedDTO> {

	/* ======以下业务逻辑======== */
	public String managementPage() {
		return "managementPage";
	}
	
	public String purchaseReturnQueryPage() {
		return "purchaseReturnQueryPage";
	}
	
	public String purchaseReturnStockOutPage() {
		return "purchaseReturnStockOutPage";
	}
	
	public String returnStockOutQueryPage() {
		return "returnStockOutQueryPage";
	}
	
	public String purchaseReturn() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			rService.savePurchaseReturn(rDTO);
			json.setSuccess(true);
			json.setMessage("申请退货成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("申请退货失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getPurchaseReturnTotalGrid() {
		DataGrid<ReturnedDTO> dg = rService.purchaseReturnTotalGrid(rDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getPurchaseReturnQueryGrid() {
		DataGrid<ReturnedDTO> dg = rService.purchaseReturnQueryGrid(rDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getPurchaseReturnDetailGrid() {
		DataGrid<ReturnedDTO> dg = rService.purchaseReturnDetailGrid(rDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/* ======以下声明======== */
	private ReturnedService rService;
	private ReturnedDTO rDTO;

	@Resource
	public void setrService(ReturnedService rService) {
		this.rService = rService;
	}
	
	@Override
	public ReturnedDTO getModel() {
		if(rDTO == null) {
			rDTO = new ReturnedDTO();
		}
		return rDTO;
	}
	
}
