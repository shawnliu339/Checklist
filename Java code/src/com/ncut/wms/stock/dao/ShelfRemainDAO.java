package com.ncut.wms.stock.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.stock.model.ShelfRemain;

@Repository("shelfRemainDAO")
public class ShelfRemainDAO extends BaseDAOImpl<ShelfRemain> {

	public ShelfRemain findByOrderIdAndDetailId(String inStockId,
			Integer inStockgoodsId) {
		String hql = "from ShelfRemain sr where sr.orderId = :orderId and sr.detailId = :detailId";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("orderId", inStockId);
		q.setParameter("detailId", inStockgoodsId);
		return (ShelfRemain) q.uniqueResult();
	}

}
