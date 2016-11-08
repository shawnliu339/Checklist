package com.ncut.wms.stock.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.stock.dto.InStockDTO;
import com.ncut.wms.stock.dto.ShelfRemainDTO;
import com.ncut.wms.stock.service.ShelfRemainService;
import com.ncut.wms.supplier.dto.SupplierDTO;
import com.ncut.wms.util.easyui.DataGrid;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("shelfRemainAction")
@Scope("prototype")
public class ShelfRemainAction extends ActionSupport implements
		ModelDriven<ShelfRemainDTO> {

	/* ======以下业务逻辑======== */
	public String getDatagrid() {

		DataGrid<ShelfRemainDTO> dg = srService.datagrid(srDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getReturnGrid() {

		DataGrid<ShelfRemainDTO> dg = srService.returnGrid(srDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/* ======以下声明======== */
	private InStockDTO iDTO;
	private ShelfRemainDTO srDTO;
	private ShelfRemainService srService;
	
	@Override
	public ShelfRemainDTO getModel() {
		if(srDTO == null) {
			srDTO = new ShelfRemainDTO();
		}
		return srDTO;
	}

	public void setiDTO(InStockDTO iDTO) {
		this.iDTO = iDTO;
	}

	public void setSrDTO(ShelfRemainDTO srDTO) {
		this.srDTO = srDTO;
	}

	@Resource
	public void setSrService(ShelfRemainService srService) {
		this.srService = srService;
	}

}
