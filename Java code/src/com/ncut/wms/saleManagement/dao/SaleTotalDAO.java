package com.ncut.wms.saleManagement.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.saleManagement.model.SaleTotal;

@Repository("saleTotalDAO")
public class SaleTotalDAO extends BaseDAOImpl<SaleTotal> {

	public SaleTotal load(String stId) {
		String hql = "from SaleTotal st where st.stId = :stId";
		Query q = this.getSession().createQuery(hql).setParameter("stId", stId);
		return (SaleTotal) q.uniqueResult();
	}

}
