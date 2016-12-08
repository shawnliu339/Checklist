package com.sic.ocms.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.persistence.Item;

public class ItemDAOTest {
	
	private static ClassPathXmlApplicationContext ctx;

	@BeforeClass
	public static void beforeClass() {
		
		 ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static void afterClass() {

		ctx.destroy();
	}
	
	public void testSave() throws Exception {

		ItemDAO dao = (ItemDAO) ctx.getBean("itemDAO");
		Item itm = new Item();
		Item itm1 = new Item();
		Item itm2 = new Item();
		
		itm.setName("顧客");
		itm1.setName("機会");
		itm2.setName("ステークホルダー");
		
		itm.getChildren().add(itm1);
		itm.getChildren().add(itm2);
		
		itm1.setParent(itm);
		itm2.setParent(itm);
		
		dao.add(itm);
	}
	
	public void testDelete() {
		
		ItemDAO dao = (ItemDAO) ctx.getBean("itemDAO");
		dao.delete(2);
		
	}

	@Test
	public void testLoad() {
		
		ItemDAO dao = (ItemDAO) ctx.getBean("itemDAO");
	}

}
