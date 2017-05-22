package com.sic.ocms.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

/*
 *
 * ダッシュボード用のデータを取得
 *
 */
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

/*
 *
 * アルファの名前をキーにして、そのアルファ以下の要素のデータを取得
 * チェックリスト表示用
 *
 */
	public DataGrid<ChecklistDO> getDataGrid(String alphaname) {
		List<Item> items = itemDAO.list("from Item");
		List<ChecklistDO> rows = new ArrayList<ChecklistDO>();

		for(Item alpha:items){
			if(alpha.getName().equals(alphaname)){
				Set<Item> statuses = alpha.getChildren();
				for(int k=1;k<=statuses.size();k++){
					for(Item status:statuses){
						if(status.getItemId()!=status.getParent().getItemId()&&status.getRank()==k){
							Set<Checkitem> checkitems = status.getCheckitems();
							for(int i=1;i<=checkitems.size();i++){
								for(Checkitem checkitem:checkitems){
									if(checkitem.getRank()==i){
										Set<CheckitemStatus> checkitemstatuses = checkitem.getCheckitemstatus();
										for(CheckitemStatus checkitemstatus:checkitemstatuses){
											ChecklistDO row = new ChecklistDO();
											row.setGroup1Id(alpha.getParent().getItemId());
											row.setFieldName(alpha.getParent().getName());
											DecimalFormat df = new DecimalFormat("#");
											row.setGroup1Percentage( Double.valueOf(df.format(alpha.getParent().getPercentage())));
											row.setGroup2Name(alpha.getName());
											row.setGroup2Percentage( Double.valueOf(df.format(alpha.getPercentage())));
											row.setGroup3Name(status.getName());
											row.setGroup3Percentage( Double.valueOf(df.format(status.getPercentage())));
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
											rows.add(row);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		dg.setRows(rows);
		dg.setTotal(rows.size());
		return dg;

	}

/*
 *
 * データベースから全データを取得
 * チェックリスト表示用
 *
 */
	public DataGrid<ChecklistDO> getDataGrid() {
		List<Item> items = itemDAO.list("from Item");
		List<ChecklistDO> rows = new ArrayList<ChecklistDO>();

		//上から表示したい順に挿入していく
		for(int n=1;n<items.size();n++){
			for(Item field:items){
				if(field.getParent().getItemId()==field.getItemId()&&field.getRank()==n){
					Set<Item> alphas = field.getChildren();
					for(int j=1;j<=alphas.size();j++){
						for(Item alpha:alphas){
							if(alpha.getItemId()!=alpha.getParent().getItemId()&&alpha.getRank()==j){
								Set<Item> statuses = alpha.getChildren();
								for(int k=1;k<=statuses.size();k++){
									for(Item status:statuses){
										if(status.getItemId()!=status.getParent().getItemId()&&status.getRank()==k){
											Set<Checkitem> checkitems = status.getCheckitems();
											for(int i=1;i<=checkitems.size();i++){
												for(Checkitem checkitem:checkitems){
													if(checkitem.getRank()==i){
														Set<CheckitemStatus> checkitemstatuses = checkitem.getCheckitemstatus();
														for(CheckitemStatus checkitemstatus:checkitemstatuses){
															ChecklistDO row = new ChecklistDO();
															row.setGroup1Id(field.getItemId());
															row.setFieldName(field.getName());
															DecimalFormat df = new DecimalFormat("#");
															row.setGroup1Percentage( Double.valueOf(df.format(field.getPercentage())));
															row.setGroup2Name(alpha.getName());
															row.setGroup2Percentage( Double.valueOf(df.format(alpha.getPercentage())));
															row.setGroup3Name(status.getName());
															row.setGroup3Percentage( Double.valueOf(df.format(status.getPercentage())));
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
															rows.add(row);
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
		dg.setRows(rows);
		dg.setTotal(rows.size());
		return dg;
	}

/*
 *
 * フロントから送られてきたデータを使ってデータベースの内容をアップデート
 * 変更してるかどうかの判断は行わず、フロントから送られてきたデータ全てで上書き
 *
 */
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

/*
 *
 * itemの得点を計算にデータベースに保存
 * ステータス、アルファ、関心領域としたの階層から計算していく
 *
 */
	public void calculatePercentage(){
		List<Item> items = itemDAO.list("from Item");
		List<Item> fields = getFields();
		List<Item> alphas = getAlphas();
		List<Item> statuses = getStatuses();

		for(Item item:statuses){
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

		for(Item item:alphas){
			Set<Item> children = item.getChildren();
			int sum = 0;
			for(Item child:children){
				sum+=child.getPercentage();
			}
			item.setPercentage((double)sum/children.size());
			itemDAO.update(item);
		}

		for(Item item:fields){
			Set<Item> children = item.getChildren();
			int sum = 0;
			for(Item child:children){
				sum+=child.getPercentage();
			}
			item.setPercentage((double)sum/children.size());
			itemDAO.update(item);
		}
	}

/*
 *
 * データベースから関心領域のみをリストにして抽出
 *
 */
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

/*
 *
 * データベースからアルファのみをリストにして抽出
 *
 */
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

/*
 *
 * データベースからステータスのみをリストにして抽出
 *
 */
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

/*
 *
 * リソースの宣言
 *
 */
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
