package com.ncut.wms.saleManagement.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.saleManagement.model.SaleStockOutDetail;

@Repository("saleStockOutDetailDAO")
public class SaleStockOutDetailDAO extends BaseDAOImpl<SaleStockOutDetail> {

	public SaleStockOutDetail findBySaleDetail(Integer sdId) {
		String hql = "from SaleStockOutDetail sod where sod.sdId = :sdId";
		Query q = this.getSession().createQuery(hql).setParameter("sdId", sdId);
		return (SaleStockOutDetail) q.uniqueResult();
	 }

}
