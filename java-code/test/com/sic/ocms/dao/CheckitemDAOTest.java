package com.sic.ocms.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.checkitem.CheckitemDAO;
import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.persistence.Checkitem;
import com.sic.ocms.persistence.Item;

public class CheckitemDAOTest {
	
	private static ClassPathXmlApplicationContext ctx;

	@BeforeClass
	public static void beforeClass() {
		
		 ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@AfterClass
	public static void afterClass() {

		ctx.destroy();
	}

	@Test
	public void testSave() throws Exception {

		CheckitemDAO ciDAO = (CheckitemDAO) ctx.getBean("checkitemDAO");
		ItemDAO iDAO = (ItemDAO) ctx.getBean("itemDAO");
		Checkitem ci = new Checkitem();
		ci.setContent("ソフトウェアによる解決策によって対応できる課題が識別されている");
		ciDAO.add(ci);
		Item itm = iDAO.loadByName("課題の識別").get(0);
		itm.getCheckitems().add(ci);
		iDAO.update(itm);
		
	}

}
