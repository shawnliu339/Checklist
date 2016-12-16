package com.sic.ocms.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sic.ocms.dao.checkitem.CheckitemDAO;
import com.sic.ocms.dao.checkitem.status.CheckitemStatusDAO;
import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.dto.ChecklistDO;
import com.sic.ocms.persistence.Checkitem;
import com.sic.ocms.persistence.CheckitemStatus;
import com.sic.ocms.persistence.Item;
import com.sic.ocms.util.easyui.DataGrid;

@Service
public class ChecklistService {

	public static void setCheckitem(Checkitem citem,ChecklistDO row){
		row.setCheckitemContent(citem.getContent());
		row.setDescription(citem.getDescrition());
		row.setTypicalDeliverables(citem.getTypicalDeliverables());
		row.setCheckitemStatusId(citem.getCheckitemId());
	}

	public static void setCheckitemStatus(CheckitemStatus cs,ChecklistDO row){
		row.setStatus(cs.getStatus());
		row.setProblem(cs.getProblem());
		row.setComment(cs.getComment());
		row.setPrjtype(cs.getPrjtype());
		row.setImportance(cs.getImportance());
	}

	private DataGrid<ChecklistDO> dg = new DataGrid<ChecklistDO>();

	public DataGrid<ChecklistDO> getDataGrid() {

		List<Item> itemlist = itemDAO.list("from Item");
		List<Checkitem> citemlist = checkitemDAO.list("from Checkitem");
		List<CheckitemStatus> citemslist = checkitemStatusDAO.list("from CheckitemStatus");
		List<ChecklistDO> table = new ArrayList<ChecklistDO>();



		// 最小単位であるcheckitemから挿入していく
		for (Checkitem citem : citemlist) {
			ChecklistDO row = new ChecklistDO();
			setCheckitem(citem,row);
			// 次にCheckitemsStatus
			for (CheckitemStatus cs : citemslist) {
				if (citem.getCheckitemId() == cs.getCheckitem().getCheckitemId()) {
					setCheckitemStatus(cs,row);
					Item itm = cs.getItem();
					// 最後にItem
					for (Item g3 : itemlist) {
						if (g3.getItemId() == itm.getItemId()) {
							row.setGroup3Name(g3.getName());
							for (Item g2 : itemlist) {
								if (g2.getItemId() == g3.getParent().getItemId()) {
									row.setGroup2Name(g2.getName());
									for (Item g1 : itemlist) {
										if (g1.getItemId() == g2.getParent().getItemId()) {
											row.setGroup1Id(g1.getItemId());
											row.setGroup1Name(g1.getName());
										}
									}
								}
							}
						}
					}
				}
			}
			if(row.getGroup1Name()!=null&&row.getGroup2Name()!=null&&row.getGroup3Name()!=null)table.add(row);
		}
		dg.setRows(table);
		dg.setTotal(table.size());

		return dg;
	}

	public void updateCheckitemStatus(ChecklistDO checklistDO) {
		List<CheckitemStatus> citemslist = checkitemStatusDAO.list("from CheckitemStatus");
		List<ChecklistDO> table = dg.getRows();

		int f = 0;//存在フラグ

		for (CheckitemStatus cs : citemslist) {
			// 同じIDならアップデート
			if (checklistDO.getCheckitemStatusId() == cs.getCheckItemStatusId()) {
				cs.setStatus(checklistDO.getStatus());
				cs.setComment(checklistDO.getComment());
				cs.setProblem(checklistDO.getProblem());
				checkitemStatusDAO.update(cs);
				f = 1;
			}
		}
		if(f==0){
			CheckitemStatus cis = new CheckitemStatus();
			cis.setStatus(checklistDO.getStatus());
			cis.setComment(checklistDO.getComment());
			cis.setProblem(checklistDO.getProblem());
			checkitemStatusDAO.add(cis);
		}
	}


	private ItemDAO itemDAO;
	private CheckitemDAO checkitemDAO;
	private CheckitemStatusDAO checkitemStatusDAO;

	@Resource
	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Resource
	public void setCheckitemDAO(CheckitemDAO checkitemDAO) {
		this.checkitemDAO = checkitemDAO;
	}

	@Resource
	public void setCheckitemStatusDAO(CheckitemStatusDAO checkitemStatusDAO) {
		this.checkitemStatusDAO = checkitemStatusDAO;
	}

}
