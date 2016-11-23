package com.ncut.wms.purchase.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.purchase.model.Purchasegoods;

@Repository("PurchasegoodsDAO")
public class PurchasegoodsDAO extends BaseDAOImpl<Purchasegoods> {

	public void add(List<Purchasegoods> pgList) {
		for(Purchasegoods pg : pgList) {
			this.add(pg);
		}
	}
}
