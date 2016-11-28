package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ocms.dao.checkitem.CheckitemDAO;

public class CheckitemDAOTest {
	
	@Test
	public void testSave() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		CheckitemDAO dao = (CheckitemDAO) ctx.getBean("checkitemDAO");

		
		ctx.destroy();
		
	}

}
