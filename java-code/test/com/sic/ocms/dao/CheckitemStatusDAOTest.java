package com.sic.ocms.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.checkitem.status.CheckitemStatusDAO;
import com.sic.ocms.persistence.CheckitemStatus;

public class CheckitemStatusDAOTest {

	@Test
	public void testSave() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


		CheckitemStatusDAO dao = (CheckitemStatusDAO) ctx.getBean("checkitemstatusDAO");
		List<CheckitemStatus> csitemlist = dao.list("from CheckitemStatus");

		CheckitemStatus cs = new CheckitemStatus();

		cs.setCheckItemStatusId(45);
		cs.setStatus(4);
		cs.setComment("test");

		for(CheckitemStatus cis:csitemlist){
			if(cis.getCheckItemStatusId()==cs.getCheckItemStatusId()){
				cis.setStatus(cs.getStatus());
				cis.setComment(cs.getComment());
				dao.update(cis);
			}
		}

		ctx.destroy();

	}

}
