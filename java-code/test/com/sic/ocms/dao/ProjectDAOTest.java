package com.sic.ocms.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.project.ProjectDAO;
import com.sic.ocms.persistence.Project;

public class ProjectDAOTest {

	@Test
	public void testSave() throws Exception{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


		ProjectDAO dao = (ProjectDAO) ctx.getBean("ProjectDAO");
		Project prj = new Project();
		prj.setProjectId(1);
		prj.setPrjId(2);
		prj.setName("Project1");
		dao.add(prj);;
		ctx.destroy();

	}

}
