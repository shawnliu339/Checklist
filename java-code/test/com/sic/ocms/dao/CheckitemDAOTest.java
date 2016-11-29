package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.checkitem.CheckitemDAO;
import com.sic.ocms.persistence.Checkitem;

public class CheckitemDAOTest {
	
	@Test
	public void testSave() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		CheckitemDAO dao = (CheckitemDAO) ctx.getBean("checkitemDAO");
		Checkitem ci = new Checkitem();
		ci.setContent("test");
		ci.setRelatedGoalId(1);
		ci.setRelatedItemId(2);
		dao.add(ci);
		ctx.destroy();
		
	}

}
