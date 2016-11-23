package com.ncut.wms.supplier.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ncut.wms.stock.service.StockService;

public class SupplierServiceTest {

	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private SupplierService service = (SupplierService) ctx.getBean("supplierService");
	
	@Test
	public void testGetSupplierList() {
		
		service.toExcel();
		
	}

}
