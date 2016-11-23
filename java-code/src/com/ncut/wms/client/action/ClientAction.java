package com.ncut.wms.client.action;

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

import com.ncut.wms.client.dto.ClientDTO;
import com.ncut.wms.client.model.Client;
import com.ncut.wms.client.service.ClientService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("clientAction")
@Scope("prototype")
public class ClientAction extends ActionSupport implements ModelDriven<ClientDTO> {

	/* ======以下业务逻辑======== */
	public String managementPage() {

		return "managementPage";
	}

	public String getDatagrid() {

		DataGrid<ClientDTO> dg = cService.datagrid(cDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getClientList() {
		
		List<Client> cList = cService.getClientList();
		String cJson = JSONArray.fromObject(cList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(cJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	
	public String add(){
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			BeanUtils.copyProperties(cDTO, c);
			cService.add(c);
			json.setSuccess(true);
			json.setMessage("添加客户成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加客户失败");
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
			BeanUtils.copyProperties(cDTO, c);
			cService.update(c);
			json.setSuccess(true);
			json.setMessage("修改客户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("修改客户信息失败");
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
			cService.delete(cDTO);
			json.setSuccess(true);
			json.setMessage("删除客户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("删除客户信息失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/* ======以下声明======== */
	private Client c = new Client();
	private ClientDTO cDTO;
	private ClientService cService;
	
	@Resource
	public void setcService(ClientService cService) {
		this.cService = cService;
	}

	public void setC(Client c) {
		this.c = c;
	}

	public void setcDTO(ClientDTO cDTO) {
		this.cDTO = cDTO;
	}

	@Override
	public ClientDTO getModel() {
		if(cDTO == null) {
			cDTO = new ClientDTO();
		}
		return cDTO;
	}
	
}
