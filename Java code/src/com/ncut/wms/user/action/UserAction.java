package com.ncut.wms.user.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.user.dto.UserDTO;
import com.ncut.wms.user.model.User;
import com.ncut.wms.user.service.UserService;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.json.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<UserDTO> {
	
/*========以下为逻辑部分========*/
	
	@Override
	public String execute()  {
		
		return "index";
	}
	
	public String add() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			user = new User();
			userDTO.setPassword(userDTO.getPassword1());
			BeanUtils.copyProperties(userDTO, user);
			userService.add(user);
			json.setSuccess(true);
			json.setMessage("添加用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加用户失败");
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
			user = new User();
			userService.delete(userDTO.getIds());
			json.setSuccess(true);
			json.setMessage("删除用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("删除用户失败");
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
			user = new User();
			userDTO.setPassword(userDTO.getPassword1());
			BeanUtils.copyProperties(userDTO, user);
			userService.update(user);
			json.setSuccess(true);
			json.setMessage("修改用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("修改用户失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String loginnameIsEqual() {
		Json json = new Json();
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		boolean isSuccess = userService.loginnameIsEqual(userDTO.getLoginname());
		if(isSuccess) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String getUserGrid() {
		DataGrid<User> dg = userService.getUserGrid(userDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.fromObject(dg).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String login(){
		Json json = new Json();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		User u = userService.login(userDTO.getLoginname());
		if(u != null) {
			if(userDTO.getPassword().equals(u.getPassword())){
				ServletActionContext.getRequest().getSession().setAttribute("user", u);
				ActionContext.getContext().put("rurl", "home_showHome");
				json.setSuccess(true);
				json.setMessage("登陆成功欢迎进入智慧仓库管理系统");
			}else{
				json.setSuccess(false);
				json.setMessage("账号或密码错误");
			}
		}else{
			json.setSuccess(false);
			json.setMessage("账号或密码错误");
		}
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONObject.fromObject(json).toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return NONE;
		
	}
	
	/*========以下为声明部分========*/
	private UserService userService;
	private UserDTO userDTO;
	private User user;
	
	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public UserDTO getModel() {
		if(userDTO == null) {
			userDTO = new UserDTO();
		}
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
}
