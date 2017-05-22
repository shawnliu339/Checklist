package com.sic.ocms.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ncut.wms.util.json.Json;
import com.sic.ocms.dto.ChecklistDO;
import com.sic.ocms.dto.DashboardDO;
import com.sic.ocms.service.ChecklistService;
import com.sic.ocms.util.easyui.DataGrid;

import net.sf.json.JSONObject;

@Controller
public class ChecklistController{

	@RequestMapping("/checklist")
	public String index() {
		return "checklist";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(ModelMap map) {
		map.put("a", "aaa");
		for(DashboardDO alpha : checklistService.getDashboard()) {
			map.put(alpha.getParentname(), alpha.getChildren());
		}
		return "dashboard";
	}
	
	/**
	 * datagridを表示する
	 * @return
	 */
	@RequestMapping("/checklist_dataGrid")
	public void dataGrid(HttpServletResponse response) {
		
		DataGrid<ChecklistDO> dg = checklistService.getDataGrid();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/checklist_goSubChecklist")
	public String goSubChecklist() {
		return "subchecklist";
	}
	
	@RequestMapping("/checklist_update")
	public void update(String rows, HttpServletResponse response) {
		Json json = new Json();
		response.setContentType("text/html;charset=utf-8");
		try {
			checklistService.update(rows);
			json.setSuccess(true);
			json.setMessage("登録成功！");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("登録失敗");
		}
		try {
			response.getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * 以下宣言
	 * 
	 * */
	
	private ChecklistService checklistService;
	private ChecklistDO checklistDO;

	public void setChecklistDO(ChecklistDO checklistDO) {
		this.checklistDO = checklistDO;
	}

	@Resource
	public void setChecklistService(ChecklistService checklistService) {
		this.checklistService = checklistService;
	}

}
