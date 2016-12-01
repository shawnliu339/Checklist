package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.persistence.Item;

public class ItemDAOTest {

	
	@Test
	public void testSave() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		ItemDAO dao = (ItemDAO) ctx.getBean("itemDAO");
		dao.add(new Item());
		ctx.destroy();
		
	}
	
}
