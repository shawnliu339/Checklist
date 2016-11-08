package com.ncut.wms.purchaseManagement.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.purchaseManagement.dto.PurchaseManagementDTO;
import com.ncut.wms.purchaseManagement.model.ReturnStockOutTotal;
import com.ncut.wms.purchaseManagement.service.PurchaseManagementService;
import com.ncut.wms.returned.dto.ReturnedDTO;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("purchaseManagementAction")
@Scope("prototype")
public class PurchaseManagementAction extends ActionSupport implements ModelDriven<PurchaseManagementDTO> {

	
	/* ======以下业务逻辑======== */
	public String purchaseReturnStockOutPage() {
		return "purchaseReturnStockOutPage";
	}
	
	public String getReturnStockOutTotalGrid() {
		DataGrid<PurchaseManagementDTO> dg = pmService.getReturnStockOutTotalGrid(pmDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getReturnStockOutDetailGrid() {
		DataGrid<PurchaseManagementDTO> dg = pmService.getReturnStockOutDetailGrid(pmDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String saveReturnStockOut() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			pmService.saveReturnStockOut(pmDTO);
			json.setSuccess(true);
			json.setMessage("退货出库成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("退货出库失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/* ======以下声明======== */
	private PurchaseManagementDTO pmDTO;
	private PurchaseManagementService pmService;

	@Override
	public PurchaseManagementDTO getModel() {
		if(pmDTO == null) {
			pmDTO = new PurchaseManagementDTO();
		}
		return pmDTO;
	}

	public void setPmDTO(PurchaseManagementDTO pmDTO) {
		this.pmDTO = pmDTO;
	}

	@Resource
	public void setPmService(PurchaseManagementService pmService) {
		this.pmService = pmService;
	}
}
