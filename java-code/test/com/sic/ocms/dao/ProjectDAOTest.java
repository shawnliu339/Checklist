package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.project.ProjectDAO;
import com.sic.ocms.persistence.Project;

public class ProjectDAOTest {

	@Test
	public void testSave() throws Exception{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


		ProjectDAO dao = (ProjectDAO) ctx.getBean("projectDAO");
		Project prj = new Project();
		dao.delete(2);
		ctx.destroy();

	}

}
