package com.ncut.wms.saleManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.saleManagement.model.SaleDetailSource;



@Repository("saleDetailSourceDAO")
@SuppressWarnings("unchecked")
public class SaleDetailSourceDAO extends BaseDAOImpl<SaleDetailSource> {

	
	public List<SaleDetailSource> findBySaleTotal(String stId) {
		String hql = "from SaleDetailSource sds where sds.stId = :stId";
		Query q = this.getSession().createQuery(hql).setParameter("stId", stId);
		return q.list();
	}

	public List<SaleDetailSource> findBySaleDetail(Integer sdId) {
		String hql = "from SaleDetailSource sds where sds.sdId = :sdId";
		Query q = this.getSession().createQuery(hql).setParameter("sdId", sdId);
		return q.list();
	}

}
