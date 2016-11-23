package com.ncut.wms.user.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ncut.wms.user.model.User;

public class UserServiceTest {

	public void testAdd() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		UserService service = (UserService)ctx.getBean("userService");
		service.add(new User());
		
		ctx.destroy();
		
	}
	
	public void testGetUserList() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		UserService service = (UserService)ctx.getBean("userService");
		String str = service.getUserList().toString();
		System.out.println(str);
		
		ctx.destroy();
		
	}
	
	@Test
	public void testGetUserListJson() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		UserService service = (UserService)ctx.getBean("userService");
		String str = service.getUserListJson();
		System.out.println(str);
		
		ctx.destroy();
		
	}

}
