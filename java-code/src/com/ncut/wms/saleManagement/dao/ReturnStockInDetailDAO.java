package com.ncut.wms.saleManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.saleManagement.model.ReturnStockInDetail;

@Repository("returnStockInDetailDAO")
public class ReturnStockInDetailDAO extends BaseDAOImpl<ReturnStockInDetail> {

	public List<ReturnStockInDetail> findByCommodityId(Integer commodityId) {
		String hql = "from ReturnStockInDetail rid where rid.commodityId = :commodityId";
		Query q = this.getSession().createQuery(hql).setParameter("commodityId", commodityId);
		return q.list();
	}

}
