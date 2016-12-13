package com.sic.ocms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sic.ocms.dao.checkitem.CheckitemDAO;
import com.sic.ocms.dao.checkitem.status.CheckitemStatusDAO;
import com.sic.ocms.dao.item.ItemDAO;
import com.sic.ocms.dto.ChecklistDO;
import com.sic.ocms.persistence.Checkitem;
import com.sic.ocms.persistence.CheckitemStatus;
import com.sic.ocms.persistence.Item;
import com.sic.ocms.util.easyui.DataGrid;

public class ChecklistService {
	private DataGrid<ChecklistDO> dg = new DataGrid<ChecklistDO>();
	
	//SpringのAnnotationを使って、Injectionするので、これいらない。
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	//SpringのAnnotationを使って
	private ItemDAO idao = (ItemDAO) ctx.getBean("itemDAO");;
	private CheckitemDAO cidao = (CheckitemDAO) ctx.getBean("checkitemDAO");;
	private CheckitemStatusDAO csdao =(CheckitemStatusDAO) ctx.getBean("checkitemstatusDAO");;

	//したは全部業務ロジックなので、関数に入れてください。
	private List<Item> itemlist = idao.list("from Item");
	private List<Checkitem> citemlist = cidao.list("from Checkitem");
	private List<CheckitemStatus> citemslist = csdao.list("from CheckitemStatus");


	//この関数をテストパッケージでテストしてください。
	public DataGrid<ChecklistDO> getDataGrid(){

		List<ChecklistDO> table = new ArrayList<ChecklistDO>();

		//最小単位であるcheckitemlistから挿入していく
		for(Checkitem citem:citemlist){
			ChecklistDO row = new ChecklistDO();
			row.setCheckitemContent(citem.getContent());
			row.setDescription(citem.getDescrition());
			row.setTypicalDeliverables(citem.getTypicalDeliverables());
			row.setCheckitemContentId(citem.getCheckitemId());
			//次にCheckitemsStatus
			for(CheckitemStatus cs:citemslist){
				if(citem.getCheckitemId()==cs.getCheckitem().getCheckitemId()){
					row.setStatus(cs.getStatus());
					row.setProblem(cs.getProblem());
					row.setComment(cs.getComment());
					row.setPrjtype(cs.getPrjtype());
					row.setImportance(cs.getImportance());
					Item itm = cs.getItem();
					//最後にItem
					for(Item g3:itemlist){
						if(g3.getItemId()==itm.getItemId()){
							row.setGroup3Name(g3.getName());
							for(Item g2:itemlist){
								if(g2.getItemId()==g3.getParent().getItemId()){
									row.setGroup2Name(g2.getName());
									for(Item g1:itemlist){
										if(g1.getItemId()==g2.getParent().getItemId()){
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
			table.add(row);
		}
		dg.setRows(table);
		dg.setTotal(table.size());

		ctx.destroy();

		return dg;
	}

	public void changeCheckitemStatus(){
		List<ChecklistDO> table = dg.getRows();

		//表の一行ごとにデータベースと比較して
		for(ChecklistDO cldto:table){
			for(CheckitemStatus cs:citemslist){
				//同じIDかつステータスまたはコメントが変更されているならアップデート
				if(cldto.getCheckitemContentId()==cs.getCheckItemStatusId() && (cldto.getStatus()!=cs.getStatus() || cldto.getComment()!=cs.getComment())){
					cs.setStatus(cldto.getStatus());
					cs.setComment(cldto.getComment());
					csdao.update(cs);
				}
			}
		}
		ctx.destroy();
	}

}
