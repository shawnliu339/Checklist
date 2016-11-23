package com.ncut.wms.returned.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.returned.model.PurchaseReturnedTotal;

@Repository("purchaseReturnedTotal")
public class PurchaseReturnedTotalDAO extends BaseDAOImpl<PurchaseReturnedTotal> {

	public PurchaseReturnedTotal findById(String prtId) {
		String hql = "from PurchaseReturnedTotal prt where prt.prtId = :prtId";
		Query q = this.getSession().createQuery(hql).setParameter("prtId", prtId);
		return (PurchaseReturnedTotal) q.uniqueResult();
	}

}
