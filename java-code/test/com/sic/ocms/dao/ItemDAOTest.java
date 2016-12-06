package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.persistence.Checklist;
import com.sic.ocms.persistence.Item;

public class ItemDAOTest {


	@Test
	public void testSave() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


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

//		dao.delete(5);
		ctx.destroy();

	}

}
