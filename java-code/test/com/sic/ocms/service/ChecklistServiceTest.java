package com.sic.ocms.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dto.ChecklistDO;
import com.sic.ocms.util.easyui.DataGrid;

public class ChecklistServiceTest {



	public void testCheckitem() throws Exception{

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		ChecklistService cs = (ChecklistService) ctx.getBean("checklistService");


		DataGrid<ChecklistDO> dg = new DataGrid<ChecklistDO>();

		dg=cs.getDataGrid();


		for(ChecklistDO csd : dg.getRows()) {
			System.out.println(csd.toString());
		}
	}


@Test
	public void testUpdate(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		ChecklistService cs = (ChecklistService) ctx.getBean("checklistService");

		DataGrid<ChecklistDO> dg = new DataGrid<ChecklistDO>();

		dg=cs.getDataGrid();

		System.out.println();

		for(ChecklistDO csd : dg.getRows()) {
			System.out.println(csd.toString());
		}

	}
}
