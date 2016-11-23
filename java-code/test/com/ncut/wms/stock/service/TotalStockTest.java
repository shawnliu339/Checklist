package com.ncut.wms.stock.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TotalStockTest {

	@Test
	public void test() {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		TotalStockService tsService = (TotalStockService) ctx.getBean("totalStockService");
		tsService.addTest(10);
	}

}
