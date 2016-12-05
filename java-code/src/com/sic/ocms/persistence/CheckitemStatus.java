package com.sic.ocms.persistence;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "checkitem_status")
public class CheckitemStatus {

	private Integer checkItemStatusId;
	private Integer status;
	private String comment;
	private String deliverables;
	private Integer prjtype;
	private Integer importance;
	private String description;
	private Date history;
	private Integer problem;
	private Checkitem checkitem;

	@Id
	@GeneratedValue
	@Column(name = "checkitem_status_id")
	public Integer getCheckItemStatusId() {
		return checkItemStatusId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	@Type(type = "text")
	@Column(name = "comment")
	public String getComment() {
		return comment;
	}

	@Column(name = "deliverables")
	public String getDeliverables() {
		return deliverables;
	}

	@Column(name = "prj_type")
	public Integer getPrjtype() {
		return prjtype;
	}

	@Column(name = "importance")
	public Integer getImportance() {
		return importance;
	}

	@Type(type = "text")
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	@Column(name = "history")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getHistory() {
		return history;
	}

	@Column(name = "problem")
	public Integer getProblem() {
		return problem;
	}

	@ManyToOne
	@JoinColumn(name = "checkitem_id")
	public Checkitem getCheckitem() {
		return checkitem;
	}

	public void setCheckItemStatusId(Integer checkItemStatusId) {
		this.checkItemStatusId = checkItemStatusId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
	}

	public void setPrjtype(Integer prjtype) {
		this.prjtype = prjtype;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHistory(Date history) {
		this.history = history;
	}

	public void setProblem(Integer problem) {
		this.problem = problem;
	}

	public void setCheckitem(Checkitem checkitem) {
		this.checkitem = checkitem;
	}


}
