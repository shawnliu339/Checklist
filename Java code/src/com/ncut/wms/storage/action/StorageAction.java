package com.ncut.wms.storage.action;

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

import com.ncut.wms.storage.dto.StorageDTO;
import com.ncut.wms.storage.model.Storage;
import com.ncut.wms.storage.service.StorageService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("storageAction")
@Scope("prototype")
public class StorageAction extends ActionSupport implements ModelDriven<StorageDTO> {

	/* ======以下业务逻辑======== */
	public String getStorageList() {
		List<Storage> storageList = storageService.getStorageList();
		String storageJson = JSONArray.fromObject(storageList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(storageJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getTotalGrid() {
		DataGrid<StorageDTO> dg = storageService.getTotalGrid(storageDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String add() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			storage = new Storage();
			BeanUtils.copyProperties(storageDTO, storage);
			storageService.add(storage);
			json.setSuccess(true);
			json.setMessage("添加仓库信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加仓库信息失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String delete() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			storageService.delete(storageDTO.getStorageId());
			json.setSuccess(true);
			json.setMessage("删除仓库信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("删除仓库信息失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String update() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			storage = new Storage();
			BeanUtils.copyProperties(storageDTO, storage);
			storageService.update(storage);
			json.setSuccess(true);
			json.setMessage("修改仓库信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("修改仓库信息失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	/* ======以下声明======== */
	private StorageService storageService;
	private StorageDTO storageDTO;
	private Storage storage;

	@Resource
	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	public void setStorageDTO(StorageDTO storageDTO) {
		this.storageDTO = storageDTO;
	}

	@Override
	public StorageDTO getModel() {
		if(storageDTO == null) {
			storageDTO = new StorageDTO();
		}
		return storageDTO;
	}
}
