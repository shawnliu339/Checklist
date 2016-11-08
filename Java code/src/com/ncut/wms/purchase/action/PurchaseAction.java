package com.ncut.wms.purchase.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.purchase.dto.PurchaseDTO;
import com.ncut.wms.purchase.dto.PurchasegoodsDTO;
import com.ncut.wms.purchase.model.Purchase;
import com.ncut.wms.purchase.model.Purchasegoods;
import com.ncut.wms.purchase.service.PurchaseService;
import com.ncut.wms.purchase.service.PurchasegoodsService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("purchaseAction")
@Scope("prototype")
public class PurchaseAction extends ActionSupport implements ModelDriven<PurchaseDTO> {

	/* ======以下业务逻辑======== */
	public String addPage() {

		return "addPage";
	}
	
	public String queryPage() {
		return "queryPage";
	}
	
	public String getOrderCode() {
		
		String orderCode = pService.getOrderCode(pDTO.getCreateDate());
		pDTO.setPurchaseId(orderCode);
		String purchaseJson = JSONObject.fromObject(pDTO).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(purchaseJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String saveOrder() {
		
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			pService.saveOrder(pDTO);
			json.setSuccess(true);
			json.setMessage("添加进货订单成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加进货订单失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getDatagrid() {

		DataGrid<PurchaseDTO> dg = pService.datagrid(pDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getQuerygrid() {

		DataGrid<PurchaseDTO> dg = pService.querygrid(pDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getDetailGrid() {
		
		DataGrid<PurchasegoodsDTO> dg = pgService.datagrid(pDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	
	
	
	/* ======以下声明======== */
	private Purchase p = new Purchase();
	private PurchaseDTO pDTO;
	private Purchasegoods pg;
	private PurchasegoodsDTO pgDTO;
	private PurchaseService pService;
	private PurchasegoodsService pgService;

	public void setP(Purchase p) {
		this.p = p;
	}

	public void setpDTO(PurchaseDTO pDTO) {
		this.pDTO = pDTO;
	}

	public void setPg(Purchasegoods pg) {
		this.pg = pg;
	}

	public void setPgDTO(PurchasegoodsDTO pgDTO) {
		this.pgDTO = pgDTO;
	}

	@Resource
	public void setpService(PurchaseService pService) {
		this.pService = pService;
	}

	@Override
	public PurchaseDTO getModel() {
		if(pDTO == null) {
			pDTO = new PurchaseDTO();
		}
		return pDTO;
	}

	@Resource
	public void setPgService(PurchasegoodsService pgService) {
		this.pgService = pgService;
	}


}
