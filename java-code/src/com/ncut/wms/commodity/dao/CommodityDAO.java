package com.ncut.wms.commodity.dao;

import java.util.List;
import java.util.Map;

import com.ncut.wms.base.dao.BaseDAO;
import com.ncut.wms.commodity.model.Commodity;

public interface CommodityDAO extends BaseDAO<Commodity>{

	/**
	 * 查询所有商品列表
	 * @return 商品列表
	 */
	public List<Commodity> findAll();

	/**
	 * 分页查询商品列表
	 * @param currentPage 当前页
	 * @param pageSize 一页呈现数据数量
	 * @return 该页商品列表
	 */
	public List<Commodity> findByPagination(int currentPage, int pageSize);
	
	/**
	 * 
	 * @param currentPage 当前页
	 * @param pageSize 一页呈现数据数量
	 * @param searchWords 搜索关键字
	 * @return
	 */
	public List<Commodity> findByPagination(int currentPage, int pageSize, Map<String, Object> searchWords);
	
	
	/**
	 * 获取商品总数
	 * @return 商品总数
	 */
	public int count();

	/**
	 * 存储商品信息
	 * @param commodity
	 */
	public void save(Commodity commodity);

	/**
	 * 修改商品信息
	 * @param commodity
	 */
	public void update(Commodity commodity);
	
	/**
	 * 删除商品信息
	 * @param commodity
	 */
	public void delete(Commodity commodity);

	/**
	 * 根据id删除商品信息
	 * @param id
	 */
	public void delete(Integer id);
	
	public Commodity findById(Integer id);
}
