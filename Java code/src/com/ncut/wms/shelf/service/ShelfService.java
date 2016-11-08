package com.ncut.wms.shelf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ncut.wms.shelf.dao.ShelfDAO;
import com.ncut.wms.shelf.model.Shelf;

@Service("shelfService")
public class ShelfService {

	/* ======以下业务逻辑======== */
	public List<Shelf> getShelfList() {
		String hql = "from Shelf";
		return sDAO.list(hql);
	}
	
	public void add(Shelf shelf) {
		sDAO.add(shelf);
	}
	
	/* ======以下声明======== */
	private ShelfDAO sDAO;

	@Resource
	public void setsDAO(ShelfDAO sDAO) {
		this.sDAO = sDAO;
	}

	
}
