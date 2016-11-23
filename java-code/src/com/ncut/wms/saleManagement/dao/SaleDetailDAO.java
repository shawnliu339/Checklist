package com.ncut.wms.saleManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.saleManagement.model.SaleDetail;

@Repository("saleDetailDAO")
public class SaleDetailDAO extends BaseDAOImpl<SaleDetail> {

	@SuppressWarnings("unchecked")
	public List<SaleDetail> findBySaleTotal(String stId) {
		String hql = "from SaleDetail sd where sd.stId = :stId";
		Query q = this.getSession().createQuery(hql).setParameter("stId", stId);
		return q.list();
	}

}
