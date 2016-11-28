package com.ocms.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="checkite_status")
public class CheckitemStatus {

	private int checkItemStatusId;
	private String comment;
	private String deliverables;
	private int prjtype;
	private int importance;
	private String description;
	private int history;
	private int problem;
	private Checkitem checkitem;



	@Id
	@GeneratedValue
	public int getCheckItemStatusId() {
		return checkItemStatusId;
	}
	public void setCheckItemStatusId(int checkItemStatusId) {
		this.checkItemStatusId = checkItemStatusId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDeliverables() {
		return deliverables;
	}
	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
	}
	public int getPrjtype() {
		return prjtype;
	}
	public void setPrjtype(int prjtype) {
		this.prjtype = prjtype;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	public int getProblem() {
		return problem;
	}
	public void setProblem(int problem) {
		this.problem = problem;
	}
	public Checkitem getCheckitem() {
		return checkitem;
	}
	public void setCheckitem(Checkitem checkitem) {
		this.checkitem = checkitem;
	}



}
