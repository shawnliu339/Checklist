package com.sic.ocms.dao.item.impl;



import org.springframework.stereotype.Repository;

import com.sic.ocms.dao.base.impl.BaseDAOImpl;
import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.persistence.Item;

@Repository("itemDAO")
public class ItemDAOImpl extends BaseDAOImpl<Item> implements ItemDAO{

}
