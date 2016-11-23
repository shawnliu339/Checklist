package com.ncut.wms.commodity.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ncut.wms.base.dao.impl.BaseDAOImpl;
import com.ncut.wms.commodity.dao.CommodityDAO;
import com.ncut.wms.commodity.model.Commodity;

@Component("commodityDAO")
public class CommodityDAO4MySqlImpl extends BaseDAOImpl<Commodity> implements CommodityDAO {

	private SessionFactory sessionFactory;

	@Override
	public List<Commodity> findAll() {
		String hql = "from Commodity";
		List<Commodity> commodityList = getSession().createQuery(hql)
				.list();
		return commodityList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * sessionFactory获取session
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Commodity> findByPagination(int currentPage, int pageSize) {
		//计算分页后显示的第一个数据的索引号
		int index = (currentPage-1)*pageSize;
		String hql = "from Commodity";
		Query query = getSession().createQuery(hql);
		//设置查询开始的索引号
		query.setFirstResult(index);
		//查询所显示的数据数
		query.setMaxResults(pageSize);
		List<Commodity> commodityList = query.list();
		return commodityList;
	}

	@Override
	public int count() {
		String hql = "select count(*) from Commodity";
		Query query = getSession().createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public void save(Commodity commodity) {
		getSession().save(commodity);
		
	}

	@Override
	public void update(Commodity commodity) {
		getSession().update(commodity);
		
	}

	@Override
	public void delete(Commodity commodity) {
		getSession().delete(commodity);
	}

	@Override
	public void delete(Integer id) {
		Commodity commodity = findById(id);
		getSession().delete(commodity);
	}

	@Override
	public Commodity findById(Integer id) {
		String hql = "from Commodity c where c.commodityId=:id";
		Query query = getSession().createQuery(hql);
		Commodity commodity = (Commodity) query.setInteger("id", id).uniqueResult();
		return commodity;
	}

	@Override
	public List<Commodity> findByPagination(int currentPage, int pageSize,
			Map<String, Object> searchWords) {
		String hql = "from Commodity where 1=1 ";
		Set<Entry<String, Object>> set = searchWords.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			if("commodityName".equals(me.getKey()) && !"".equals(me.getValue())){
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
		List<Commodity> commodityList = query.list();
		return commodityList;
	}

}
