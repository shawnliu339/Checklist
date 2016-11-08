package com.ncut.wms.base.dao;

import java.util.List;
import java.util.Map;

public interface BaseDAO<T> {

	public void add(T t);
	public void delete(int id);
	public void update(T t);
	public void merge(T t);
	public T load(int id);
	public T load(String id);
	
	public int count(String hql);
	public int count(String hql,Map<String,Object> args);
	
	public List<T> list(String hql);
	public List<T> list(String hql,Object arg);
	public List<T> list(String hql,Object[] args);
	public List<T> list(String hql,int page,int rows);
	public List<T> list(String hql,Map<String,Object> args,int page,int rows);
	
	public List<T> listBySql(String sql);
}
