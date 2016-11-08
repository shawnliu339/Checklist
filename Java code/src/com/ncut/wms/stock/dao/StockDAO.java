package com.ncut.wms.stock.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.stock.model.Stock;

@Repository("stockDAO")
public class StockDAO extends BaseDAOImpl<Stock> {

	public Stock findByCommodityAndStorage(Integer commodityId, Integer storageId) {
		
		String hql = "from Stock s where s.commodityId = :commodityId and s.storageId = :storageId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("commodityId", commodityId);
		q.setParameter("storageId", storageId);
		return (Stock) q.uniqueResult();
	}
}
