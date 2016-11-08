package com.ncut.wms.stock.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.stock.dto.StockDTO;
import com.ncut.wms.stock.dto.TotalStockDTO;
import com.ncut.wms.stock.model.Stock;
import com.ncut.wms.stock.service.StockService;
import com.ncut.wms.util.easyui.DataGrid;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("stockAction")
@Scope("prototype")
public class StockAction extends ActionSupport implements ModelDriven<TotalStockDTO> {

	/* ======以下业务逻辑======== */
	public String stockQueryPage() {

		return "stockQueryPage";
	}
	
	public String getTotalgrid() {
		
		DataGrid<TotalStockDTO> dg = sService.totalgrid(tsDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getDetailgrid() {

		DataGrid<StockDTO> dg = sService.detailgrid(tsDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/* ======以下声明======== */
	private Stock s = new Stock();
	private StockDTO sDTO;
	private TotalStockDTO tsDTO;
	private StockService sService;
	
	public void setS(Stock s) {
		this.s = s;
	}

	public void setsDTO(StockDTO sDTO) {
		this.sDTO = sDTO;
	}

	@Resource
	public void setsService(StockService sService) {
		this.sService = sService;
	}

	

	@Override
	public TotalStockDTO getModel() {
		if(tsDTO == null) {
			tsDTO = new TotalStockDTO();
		}
		return tsDTO;
	}

	public void setTsDTO(TotalStockDTO tsDTO) {
		this.tsDTO = tsDTO;
	}


}
