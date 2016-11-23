package com.ncut.wms.purchase.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.purchase.model.Purchase;

@Repository("PurchseDAO")
public class PurchaseDAO extends BaseDAOImpl<Purchase> {

	public Purchase findById(String purchaseId) {

		String hql = "from Purchase p where p.purchaseId = :purchaseId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("purchaseId", purchaseId);
		return (Purchase) q.uniqueResult();
	}

}
