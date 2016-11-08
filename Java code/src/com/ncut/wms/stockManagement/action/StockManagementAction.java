package com.ncut.wms.stockManagement.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.stockManagement.dto.StockManagementDTO;
import com.ncut.wms.stockManagement.service.StockManagementService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



@Controller("stockManagementAction")
@Scope("prototype")
public class StockManagementAction extends ActionSupport implements ModelDriven<StockManagementDTO>{

	/* ======以下业务逻辑======== */
	public String stockWarningPage() {
		return "stockWarningPage";
	}
	
	public String reportBreakPage() {
		return "reportBreakPage";
	}
	
	public String reportBreakStockOutPage() {
		return "reportBreakStockOutPage";
	}
	
	public String reportBreakQueryPage() {
		return "reportBreakQueryPage";
	}
	
	public String reportBreakStockOutQueryPage() {
		return "reportBreakStockOutQueryPage";
	}
	
	public String saveReportBreak() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			smService.saveReportBreak(smDTO);
			json.setSuccess(true);
			json.setMessage("添加报损单据成功！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加报损单据失败！");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String saveReportBeakStockOut() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			smService.saveReportBeakStockOut(smDTO);
			json.setSuccess(true);
			json.setMessage("添加出库单据成功！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加出库单据失败！");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getBreakTotalGrid() {
		DataGrid<StockManagementDTO> dg = smService.getBreakTotalGrid(smDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getBreakDetailGrid() {
		DataGrid<StockManagementDTO> dg = smService.getBreakDetailGrid(smDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getBreakStockOutTotalGrid() {
		DataGrid<StockManagementDTO> dg = smService.getBreakStockOutTotalGrid(smDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getBreakStockOutDetailGrid() {
		DataGrid<StockManagementDTO> dg = smService.getBreakStockOutDetailGrid(smDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getStockWarningTotalGrid() {
		DataGrid<StockManagementDTO> dg = smService.getStockWarningTotalGrid(smDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/* ======以下声明======== */
	private StockManagementDTO smDTO;
	private StockManagementService smService;
	
	@Override
	public StockManagementDTO getModel() {
		if (smDTO == null) {
			smDTO = new StockManagementDTO();
		}
		return smDTO;
	}

	public void setSmDTO(StockManagementDTO smDTO) {
		this.smDTO = smDTO;
	}

	@Resource
	public void setSmService(StockManagementService smService) {
		this.smService = smService;
	}

}
