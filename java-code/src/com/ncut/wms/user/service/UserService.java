package com.ncut.wms.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ncut.wms.stockManagement.dto.StockManagementDTO;
import com.ncut.wms.stockManagement.model.BreakTotal;
import com.ncut.wms.user.dao.UserDAO;
import com.ncut.wms.user.dto.UserDTO;
import com.ncut.wms.user.model.User;
import com.ncut.wms.util.easyui.DataGrid;

@Component("userService")
public class UserService {

	/* ======以下业务逻辑======== */
	public DataGrid<User> getUserGrid(UserDTO userDTO) {
		DataGrid<User> dg = new DataGrid<User>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from User u where 1=1";
		
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(userDTO.getSort()!=null){
			hql+=" order by "+userDTO.getSort()+" "+userDTO.getOrder();
		}
		List<User> userList = userDAO.list(hql, map, userDTO.getPage(), userDTO.getRows());
		
		dg.setTotal(userDAO.count(totalHql, map));
		dg.setRows(userList);
		return dg;
	}
	
	public void add(User user) {
		userDAO.add(user);
	}
	
	public void update(User user) {
		userDAO.update(user);
	}
	
	public void delete(String ids) {
		String []idArr = ids.split(",");
		for(String id : idArr ) {
			userDAO.delete(Integer.parseInt(id));
		}
		
	}
	
	public User login(String username) {
		return userDAO.getUser(username);
	}
	
	public List<User> getUserList() {
		List<User> userList = userDAO.findAll();
		return userList;
	}
	
	public String getUserListJson() {
		List<User> userList = userDAO.findAll();
		String json = JSONArray.fromObject(userList).toString();
		return json;
	}
	
	public User findById(Integer id) {
		return userDAO.load(id);
	}

	/* ======以下声明======== */
	private UserDAO userDAO;
	
	@Resource
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean loginnameIsEqual(String loginname) {
		if(userDAO.findByLoginname(loginname)!=null) {
			return false;
		} else {
			return true;
		}
		
	}

}
