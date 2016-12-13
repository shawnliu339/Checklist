package com.sic.ocms.dao.item.impl;



import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sic.ocms.dao.base.impl.BaseDAOImpl;
import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.persistence.Item;

@Repository("itemDAO")
public class ItemDAOImpl extends BaseDAOImpl<Item> implements ItemDAO{

	@Override
	public List<Item> loadByName(String name) {
		
		String hql = "from Item itm where itm.name = :name";
		List<Item> itms =  this.getSession().createQuery(hql)
				.setParameter("name", name).list();
		return itms;
	}

}
