package com.sic.ocms.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.persistence.Item;

public class ItemDAOTest {


	@Test
	public void testSave() throws Exception{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


		ItemDAO dao = (ItemDAO) ctx.getBean("itemDAO");

		List<Item> itemlist = new ArrayList<Item>();
		itemlist=dao.list("from Item");


		Item root = new Item();

		for(Item item : itemlist){
			if(item.getItemId()==item.getParent().getItemId()){
				root=item;
				System.out.println(root.getName());
			}
		}
		for(Item item : root.getChildren()){
			if(item.getItemId()!=item.getParent().getItemId()){
				System.out.println("+--"+item.getName());
				for(Item child: item.getChildren()){
					System.out.println(" +--"+child.getName());
				}
			}
		}

		//printChildren(root);



		ctx.destroy();
	}

	public void printChildren(Item item){

	}



}
