package com.ncut.wms.stock.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.stock.model.TotalStock;

@Repository("totalStockDAO")
public class TotalStockDAO extends BaseDAOImpl<TotalStock>{

	public TotalStock findByCommodityId(Integer commodityId) {
		String hql = "from TotalStock ts where ts.commodityId = :commodityId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("commodityId", commodityId);
		return (TotalStock) q.uniqueResult();
	}
}
