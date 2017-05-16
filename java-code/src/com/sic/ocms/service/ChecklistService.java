package com.sic.ocms.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sic.ocms.dao.checkitem.CheckitemDAO;
import com.sic.ocms.dao.checkitem.status.CheckitemStatusDAO;
import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.dto.ChecklistDO;
import com.sic.ocms.dto.DashboardDO;
import com.sic.ocms.persistence.Checkitem;
import com.sic.ocms.persistence.CheckitemStatus;
import com.sic.ocms.persistence.Item;
import com.sic.ocms.util.easyui.DataGrid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ChecklistService {

	private DataGrid<ChecklistDO> dg = new DataGrid<ChecklistDO>();
	ChecklistDO row = new ChecklistDO();
	List<ChecklistDO> table = new ArrayList<ChecklistDO>();

	public List<DashboardDO> getDashboard(){
		List<DashboardDO> elements = new ArrayList<DashboardDO>();

		List<Item> alpha = getAlphas();
		for(Item item:alpha){
			DashboardDO element = new DashboardDO();
			element.setParentname(item.getName());
			element.setChildren(item.getChildren());
			elements.add(element);
		}
		return elements;
	}

	public DataGrid<ChecklistDO> getDataGrid(String alphaname) {
		List<Item> items = itemDAO.list("from Item");
		List<ChecklistDO> table = new ArrayList<ChecklistDO>();
		Set<Item> statuses = new HashSet<Item>();

		//上から表示したい順に挿入していく
						for(Item g2:items){
							if(g2.getName().equals(alphaname)){
								Set<Item> group3 = g2.getChildren();
								for(int k=1;k<=group3.size();k++){
									for(Item g3:group3){
										if(g3.getRank()==k){
											Set<Checkitem> checkitems = g3.getCheckitems();
											for(int i=1;i<=checkitems.size();i++){
												for(Checkitem checkitem:checkitems){
													if(checkitem.getRank()==i){
														Set<CheckitemStatus> checkitemstatuses = checkitem.getCheckitemstatus();
														for(CheckitemStatus checkitemstatus:checkitemstatuses){
															ChecklistDO row = new ChecklistDO();
															DecimalFormat df = new DecimalFormat("#");
															row.setGroup2Name(g2.getName());
															row.setGroup2Percentage( Double.valueOf(df.format(g2.getPercentage())));
															row.setGroup3Name(g3.getName());
															row.setGroup3Percentage( Double.valueOf(df.format(g3.getPercentage())));
															row.setCheckitemContent(checkitem.getContent());
															row.setDescription(checkitem.getDescrition());
															row.setTypicalDeliverables(checkitem.getTypicalDeliverables());
															row.setCheckitemStatusId(checkitemstatus.getCheckItemStatusId());
															row.setStatus(checkitemstatus.getStatus());
															row.setProblem(checkitemstatus.getProblem());
															row.setComment(checkitemstatus.getComment());
															row.setPrjtype(checkitemstatus.getPrjtype());
															row.setImportance(checkitemstatus.getImportance());
															row.setCheckitemId(checkitem.getCheckitemId());
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
		dg.setRows(table);
		dg.setTotal(table.size());
		return dg;

	}

	//データベースからデータを取得
	public DataGrid<ChecklistDO> getDataGrid() {
		List<Item> itemｓ = itemDAO.list("from Item");
		List<ChecklistDO> table = new ArrayList<ChecklistDO>();

		//上から表示したい順に挿入していく
		for(int n=1;n<itemｓ.size();n++){
			for(Item g1:itemｓ){
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
												for(Checkitem checkitem:checkitems){
													if(checkitem.getRank()==i){
														Set<CheckitemStatus> checkitemstatuses = checkitem.getCheckitemstatus();
														for(CheckitemStatus checkitemstatus:checkitemstatuses){
															ChecklistDO row = new ChecklistDO();
															row.setGroup1Id(g1.getItemId());
															row.setGroup1Name(g1.getName());
															DecimalFormat df = new DecimalFormat("#");
															row.setGroup1Percentage( Double.valueOf(df.format(g1.getPercentage())));
															row.setGroup2Name(g2.getName());
															row.setGroup2Percentage( Double.valueOf(df.format(g2.getPercentage())));
															row.setGroup3Name(g3.getName());
															row.setGroup3Percentage( Double.valueOf(df.format(g3.getPercentage())));
															row.setCheckitemContent(checkitem.getContent());
															row.setDescription(checkitem.getDescrition());
															row.setTypicalDeliverables(checkitem.getTypicalDeliverables());
															row.setCheckitemStatusId(checkitemstatus.getCheckItemStatusId());
															row.setStatus(checkitemstatus.getStatus());
															row.setProblem(checkitemstatus.getProblem());
															row.setComment(checkitemstatus.getComment());
															row.setPrjtype(checkitemstatus.getPrjtype());
															row.setImportance(checkitemstatus.getImportance());
															row.setCheckitemId(checkitem.getCheckitemId());
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

	//データベースをアップデート
	public void update(String rows) {
		JSONArray jArr = JSONArray.fromObject(rows);
		List<CheckitemStatus> checkitemstatuses = checkitemStatusDAO.list("from CheckitemStatus");
		CheckitemStatus updatedcheckitemstatus = new CheckitemStatus();

		for(int i=0;i<jArr.size();i++){
			JSONObject jObj = JSONObject.fromObject(jArr.get(i));
			updatedcheckitemstatus.setCheckItemStatusId(jObj.getInt("checkitemStatusId"));
			updatedcheckitemstatus.setStatus(jObj.getInt("status"));
			updatedcheckitemstatus.setProblem(jObj.getInt("problem"));
			updatedcheckitemstatus.setComment(jObj.getString("comment"));
			for (CheckitemStatus checkitemstatus : checkitemstatuses) {
				// 同じIDならアップデート
				if (updatedcheckitemstatus.getCheckItemStatusId() == checkitemstatus.getCheckItemStatusId()) {
					checkitemstatus.setStatus(updatedcheckitemstatus.getStatus());
					checkitemstatus.setComment(updatedcheckitemstatus.getComment());
					checkitemstatus.setProblem(updatedcheckitemstatus.getProblem());
					checkitemStatusDAO.update(checkitemstatus);
					calculatePercentage();
					break;
				}
			}
		}
	}

	//データベース内のitemの得点を計算
	public void calculatePercentage(){
		List<Item> items = itemDAO.list("from Item");
		List<Item> group1 = new ArrayList<Item>();
		List<Item> group2 = new ArrayList<Item>();
		List<Item> group3 = new ArrayList<Item>();
		for(Item item:items){
			if(item.getChildren().size()==0){
				group3.add(item);
			}else if(item.getItemId()==item.getParent().getItemId()){
				group1.add(item);
			}else{
				group2.add(item);
			}
		}

		for(Item item:group3){//まずグループ3
			int completioncounter = 0;		//完了数
			int correspondencecounter = 0;	//対応中数
			int notstartedcounter = 0;		//未着手数
			Set<Checkitem> checkitems = item.getCheckitems();
			for(Checkitem checkitem:checkitems){
				Set<CheckitemStatus> checkitemstatuses = checkitem.getCheckitemstatus();
				for(CheckitemStatus checkitemstatus:checkitemstatuses){
					if(checkitemstatus.getStatus()==5){
						completioncounter+=1;
					}else if(checkitemstatus.getStatus()==4){
						correspondencecounter+=1;
					}else if(checkitemstatus.getStatus()==3){
						notstartedcounter+=1;
					}
				}
			}
			double percentage;
			if(completioncounter+correspondencecounter+notstartedcounter==0){
				percentage=0.0;
			}else{
				percentage=(completioncounter+(correspondencecounter*0.5))/(completioncounter+correspondencecounter+notstartedcounter);
			}
			item.setPercentage(percentage*100);
			itemDAO.update(item);
		}

		for(Item item:group2){
			Set<Item> children = item.getChildren();
			int sum = 0;
			for(Item child:children){
				sum+=child.getPercentage();
			}
			item.setPercentage((double)sum/children.size());
			itemDAO.update(item);
		}

		for(Item item:group1){
			Set<Item> children = item.getChildren();
			int sum = 0;
			for(Item child:children){
				sum+=child.getPercentage();
			}
			item.setPercentage((double)sum/children.size());
			itemDAO.update(item);
		}
	}

	//グループ1の取得
	public List<Item> getFields(){
		List<Item> fields = new ArrayList<Item>();
		List<Item> items = itemDAO.list("from Item");

		for(Item item:items){
			if(item.getParent().getItemId()==item.getItemId()){
				fields.add(item);
			}
		}
		return fields;
	}

	//グループ2の取得
	public List<Item> getAlphas(){
		List<Item> alphas = new ArrayList<Item>();
		List<Item> fields = getFields();

		for(Item item:fields){
			for(Item child:item.getChildren()){
				if(child.getItemId()!=item.getItemId())alphas.add(child);
			}
		}
		return alphas;
	}
	//グループ3の取得
	public List<Item> getStatuses(){
		List<Item> Statuses = new ArrayList<Item>();
		List<Item> alphas = getAlphas();

		for(Item item:alphas){
			for(Item child:item.getChildren()){
				Statuses.add(child);
			}
		}
		return Statuses;
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
