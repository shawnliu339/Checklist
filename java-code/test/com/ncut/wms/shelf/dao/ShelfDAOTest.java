package com.ncut.wms.shelf.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ncut.wms.shelf.model.Shelf;
import com.ncut.wms.shelf.service.ShelfService;

public class ShelfDAOTest {
	
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	private ShelfService service = (ShelfService)ctx.getBean("shelfService");

	public void test() {
		char word = 'A';
		StringBuffer str = new StringBuffer();
		String [] sqlstr = new String [100];
		Integer count = 0;
		for(Integer i=0; i<10; i++){
			
		
			for(Integer j=1; j<=10; j++) {
				
				if(j<10) {
					str.append(word).append('0').append(j);
				}
				if(j>=10) {
					str.append(word).append(j);
				}
				
				sqlstr[count] = str.toString();
				count++;
				str.setLength(0);
			}
			word++;
		}
		
		
		for(int i=2; i<100; i++) {
			Shelf shelf = new Shelf();
			shelf.setShelfName(sqlstr[i]);
			service.add(shelf);
		}
		
	}

}
