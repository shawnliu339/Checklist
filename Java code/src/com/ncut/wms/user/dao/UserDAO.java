package com.ncut.wms.user.dao;

import java.util.List;

import com.ncut.wms.base.dao.BaseDAO;
import com.ncut.wms.user.model.User;

public interface UserDAO extends BaseDAO<User>{
	
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> findAll();

	public User getUser(String username);

	public User findByLoginname(String loginname);

}
