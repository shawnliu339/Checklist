package com.ncut.wms.index.action;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ncut.wms.user.model.User;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author 刘思远
 *
 */

@Controller("index")
@Scope("prototype")
public class IndexAction extends ActionSupport{

	@Override
	public String execute() {
		
		return "index";
	}
	
	public String userManagementPage() {
		
		return "userManagementPage";
	}
	
	public String storageManagementPage() {
		
		return "storageManagementPage";
	}
	
	public String isLogin() {
		Json json = new Json();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		if(user != null) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String logout() {
		Json json = new Json();
		json.setMessage("期待您下次使用智慧仓库管理系统！");
		ServletActionContext.getRequest().getSession().invalidate();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
}
