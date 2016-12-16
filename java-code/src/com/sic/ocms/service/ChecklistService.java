package com.sic.ocms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ChecklistService {



	private DataGrid<ChecklistDO> dg = new DataGrid<ChecklistDO>();

	public DataGrid<ChecklistDO> getDataGrid() {

		List<Item> itemlist = itemDAO.list("from Item");
		List<ChecklistDO> table = new ArrayList<ChecklistDO>();


/*
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
*/
		//上から表示したい順に挿入していく
		for(int n=1;n<itemlist.size();n++){
			for(Item g1:itemlist){
				if(g1.getParent().getItemId()==g1.getItemId()&&g1.getRank()==n){
					Set<Item> group2 = g1.getChildren();
					for(int j=1;j<=group2.size();j++){
						for(Item g2:group2){
							if(g2.getItemId()!=g2.getParent().getItemId()&&g2.getRank()==j){
								Set<Item> group3 = g2.getChildren();
								for(int k=1;k<=group3.size();k++){
									for(Item g3:group3){
										if(g3.getItemId()!=g3.getParent().getItemId()&&g3.getRank()==k){
											Set<Checkitem> checkitems = g3.getCheckitems();
											for(int i=1;i<=checkitems.size();i++){
												for(Checkitem ci:checkitems){
													if(ci.getRank()==i){
														Set<CheckitemStatus> checkitemstatuses = ci.getCheckitemstatus();
														for(CheckitemStatus cis:checkitemstatuses){
															ChecklistDO row = new ChecklistDO();
															row.setGroup1Id(g1.getItemId());
															row.setGroup1Name(g1.getName());
															row.setGroup2Name(g2.getName());
															row.setGroup3Name(g3.getName());
															row.setCheckitemContent(ci.getContent());
															row.setDescription(ci.getDescrition());
															row.setTypicalDeliverables(ci.getTypicalDeliverables());
															row.setCheckitemStatusId(ci.getCheckitemId());
															row.setStatus(cis.getStatus());
															row.setProblem(cis.getProblem());
															row.setComment(cis.getComment());
															row.setPrjtype(cis.getPrjtype());
															row.setImportance(cis.getImportance());
															row.setCheckitemId(ci.getCheckitemId());
															table.add(row);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		dg.setRows(table);
		dg.setTotal(table.size());

		return dg;
	}

	public void update(String rows) {

		JSONArray jArr = JSONArray.fromObject(rows);

		List<CheckitemStatus> citemslist = checkitemStatusDAO.list("from CheckitemStatus");
		List<ChecklistDO> table = new ArrayList<ChecklistDO>();

		CheckitemStatus cis = new CheckitemStatus();

		for(int i=0;i<jArr.size();i++){
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));

			cis.setCheckItemStatusId(jObj.getInt("checkitemStatusId"));
			cis.setStatus(jObj.getInt("status"));
			cis.setProblem(jObj.getInt("problem"));
			cis.setComment(jObj.getString("comment"));

		}

		int f = 0;//存在フラグ

		for (CheckitemStatus cs : citemslist) {
			// 同じIDならアップデート
			if (cis.getCheckItemStatusId() == cs.getCheckItemStatusId()) {
				cs.setStatus(cis.getStatus());
				cs.setComment(cis.getComment());
				cs.setProblem(cis.getProblem());
				checkitemStatusDAO.update(cs);
				f = 1;
			}
		}
		if(f==0){
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
