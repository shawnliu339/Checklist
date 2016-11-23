package com.ncut.wms.stock.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.purchase.dto.PurchaseDTO;
import com.ncut.wms.purchase.dto.PurchasegoodsDTO;
import com.ncut.wms.stock.dto.InStockDTO;
import com.ncut.wms.stock.dto.InStockgoodsDTO;
import com.ncut.wms.stock.model.InStock;
import com.ncut.wms.stock.model.InStockgoods;
import com.ncut.wms.stock.service.InStockService;
import com.ncut.wms.stock.service.InStockgoodsService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("inStockAction")
@Scope("prototype")
public class inStockAction extends ActionSupport implements ModelDriven<InStockDTO> {

	/* ======以下业务逻辑======== */
	/**
	 * 跳转入库管理页面
	 * 
	 * @return
	 */
	public String managementPage() {

		return "managementPage";
	}
	
	public String queryPage() {
		return "queryPage";
	}
	
	public String getQuerygrid() {

		DataGrid<InStockDTO> dg = iService.querygrid(iDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getDetailGrid() {
		
		DataGrid<InStockgoodsDTO> dg = igService.datagrid(iDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String purchase() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			iService.savePurchase(iDTO);
			json.setSuccess(true);
			json.setMessage("采购入库成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("采购入库失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	
	
	
	/* ======以下声明======== */
	private InStock inStock = new InStock();
	private InStockgoods ig = new InStockgoods();
	private InStockDTO iDTO;
	private InStockgoodsDTO igDTO;
	private InStockService iService;
	private InStockgoodsService igService;
	
	@Override
	public InStockDTO getModel() {
		if(iDTO == null) {
			iDTO = new InStockDTO();
		}
		return iDTO;
	}

	public void setiDTO(InStockDTO iDTO) {
		this.iDTO = iDTO;
	}

	public void setIgDTO(InStockgoodsDTO igDTO) {
		this.igDTO = igDTO;
	}

	@Resource
	public void setiService(InStockService iService) {
		this.iService = iService;
	}

	@Resource
	public void setIgService(InStockgoodsService igService) {
		this.igService = igService;
	}
}
