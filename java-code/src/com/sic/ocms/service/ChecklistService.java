package com.sic.ocms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sic.ocms.dao.checklist.ChecklistDAO;
import com.sic.ocms.dto.ChecklistDTO;
import com.sic.ocms.persistence.Checklist;
import com.sic.ocms.util.easyui.DataGrid;

@Service("checklistService")
public class ChecklistService {

	public DataGrid<Checklist> getChecklistGrid(ChecklistDTO checklistDTO) {
		DataGrid<Checklist> dg = new DataGrid<Checklist>();
		String hql = "from Checklist u where 1=1";

		String totalHql = "select count(*) "+hql;
		//实现排序
		if(checklistDTO.getSort()!=null){
			hql+=" order by "+checklistDTO.getSort()+" "+checklistDTO.getOrder();
		}
		List<Checklist> checklists = checklistDAO.list(hql,checklistDTO.getPage(), checklistDTO.getRows());

		dg.setTotal(checklistDAO.count(totalHql));
		//dg.setRows();
		return dg;
	}

	private ChecklistDAO checklistDAO;

	@Resource
	public void setChecklistDAO(ChecklistDAO checklistDAO) {
		this.checklistDAO = checklistDAO;
	}





}
