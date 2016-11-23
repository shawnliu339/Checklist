package com.ncut.wms.unit.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.commodity.model.Commodity;
import com.ncut.wms.unit.dao.UnitDAO;
import com.ncut.wms.unit.model.Unit;

@Repository("unitDAO")
public class UnitDAO4MySqlImpl extends BaseDAOImpl<Unit> implements UnitDAO {
	
	@Override
	public List<Unit> findAll() {
		return this.list("from Unit");
	}
	
	@Override
	public Unit findById(Integer unitId) {
		return this.load(unitId);
	}

	@Override
	public void merge(Unit t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Unit> findByPagination(int currentPage, int pageSize,
			Map<String, Object> m) {
		String hql = "from Unit where 1=1 ";
		Set<Entry<String, Object>> set = m.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			if("unitName".equals(me.getKey()) && !"".equals(me.getValue())){
				hql += " and " + me.getKey() + " like '%"+ me.getValue()  +"%'" ;
			}
			if("sort".equals(me.getKey()) && !"".equals(me.getValue())){
				hql += " order by " + me.getValue() ;
			}
			if("order".equals(me.getKey()) && !"".equals(me.getValue())){
				hql += " " + me.getValue();
			}
		}
		//计算分页后显示的第一个数据的索引号
		int index = (currentPage-1)*pageSize;
		Query query = getSession().createQuery(hql);
		//设置查询开始的索引号
		query.setFirstResult(index);
		//查询所显示的数据数
		query.setMaxResults(pageSize);
		List<Unit> unitList = query.list();
		return unitList;
	}

}
