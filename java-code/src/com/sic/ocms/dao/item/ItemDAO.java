package com.sic.ocms.dao.item;

import java.util.List;

import com.sic.ocms.dao.base.BaseDAO;
import com.sic.ocms.persistence.Item;

public interface ItemDAO extends BaseDAO<Item> {
	
	List<Item> loadByName(String name);
	
}
