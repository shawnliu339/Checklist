package com.ncut.wms.stock.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ncut.wms.stock.model.TotalStock;

public class StockTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StockService service = (StockService) ctx.getBean("stockService");
	}
	

}
