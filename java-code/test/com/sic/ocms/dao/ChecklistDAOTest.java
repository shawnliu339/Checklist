package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.checklist.ChecklistDAO;
import com.sic.ocms.persistence.Checklist;
import com.sic.ocms.persistence.Project;

public class ChecklistDAOTest {

	@Test
	public void testSave() throws Exception{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


		ChecklistDAO dao = (ChecklistDAO) ctx.getBean("checklistDAO");
		Checklist cl = new Checklist();
		Project prj = new Project();
		prj.setProjectId(1);
		cl.setName("SEMAT");
		cl.setPercentage(0.0);
		cl.setProject(prj);
		dao.add(cl);
		ctx.destroy();
	}

}
