package com.ncut.wms.unit.dao;

import java.util.List;
import java.util.Map;

import com.ncut.wms.base.dao.BaseDAO;
import com.ncut.wms.unit.model.Unit;

public interface UnitDAO extends BaseDAO<Unit> {

	public List<Unit> findAll();

	public Unit findById(Integer unitId);

	public List<Unit> findByPagination(int currentPage, int pageSize,
			Map<String, Object> m);
}
