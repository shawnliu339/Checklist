package com.ncut.wms.stock.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.stock.model.InStockgoods;

@Repository("inStockgoods")
public class InStockgoodsDAO extends BaseDAOImpl<InStockgoods> {

	public List<InStockgoods> findByCommodity(Integer commodityId) {
		String hql = "from InStockgoods ig where ig.commodityId = :commodityId";
		Query q = this.getSession().createQuery(hql).setParameter("commodityId", commodityId);
		return q.list();
	}

}
