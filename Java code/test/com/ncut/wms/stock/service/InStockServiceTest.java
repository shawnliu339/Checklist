package com.ncut.wms.stock.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class InStockServiceTest {

	@Test
	public void testGetOrder() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		InStockService service = (InStockService)ctx.getBean("inStockService");
		String date = "2015-01-29 00:00:00";
		String code = service.getOrderCode(date.substring(0, 10).replace("-", ""));
		System.out.println(code);
		ctx.destroy();
		
	}
}
