package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.checkitem.status.CheckitemStatusDAO;
import com.sic.ocms.persistence.Checkitem;
import com.sic.ocms.persistence.CheckitemStatus;
import com.sic.ocms.persistence.Item;

public class CheckitemStatusDAOTest {

	@Test
	public void testSave() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


		CheckitemStatusDAO dao = (CheckitemStatusDAO) ctx.getBean("checkitemstatusDAO");
		CheckitemStatus cs = new CheckitemStatus();
		Checkitem si = new Checkitem();
		Item itm = new Item();
		itm.setItemId(5);
//		si.setCheckitemId(1);
//		cs.setStatus(1);
//		cs.setComment("test");
//		cs.setDeliverables("test");
//		cs.setPrjtype(1);
//		cs.setImportance(1);
//		cs.setDescription("test");
//		cs.setHistory(null);
//		cs.setProblem(0);
//		cs.setCheckitem(si);
		dao.add(cs);
		ctx.destroy();

	}

}
