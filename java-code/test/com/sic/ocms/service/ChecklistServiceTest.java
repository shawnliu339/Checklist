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

		String s = 	"[{\"checkitemContent\":\"ソフトウェアによる解決策によって対応できる課題が識別されている\",\"checkitemId\":1,\"checkitemStatusId\":45,\"comment\":\"test\",\"description\":\"\",\"group1Id\":21,\"group1Name\":\"顧客\",\"group2Name\":\"機会\",\"group3Name\":\"課題の識別\",\"importance\":1,\"prjtype\":1,\"problem\":1,\"status\":\"4\",\"typicalDeliverables\":\"\"}]";


		cs.update(s);

		dg=cs.getDataGrid();

		for(ChecklistDO csd : dg.getRows()) {
			System.out.println(csd.toString());
		}
	}


	public void testCalculatePercentage(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		ChecklistService cs = (ChecklistService) ctx.getBean("checklistService");




		cs.calculatePercentage();

		DataGrid<ChecklistDO> dg = new DataGrid<ChecklistDO>();
		dg=cs.getDataGrid();

		int i = 1;
		for(ChecklistDO csd : dg.getRows()) {
			if(i<=3){
				System.out.println(csd.getGroup3Percentage()+"	"+csd.getCheckitemContent()+"	"+csd.getStatus());
				i++;
			}
		}



	}
}
