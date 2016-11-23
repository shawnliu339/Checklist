package com.ncut.wms.stock.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.stock.model.InStock;

@Repository("inStockDAO")
public class InStockDAO extends BaseDAOImpl<InStock> {

	public InStock findById(String inStockId) {
		String hql = "from InStock i where i.inStockId = :inStockId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("inStockId", inStockId);
		return (InStock) q.uniqueResult();
	}
}
