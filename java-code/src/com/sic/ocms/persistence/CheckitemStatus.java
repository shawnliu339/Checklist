package com.sic.ocms.persistence;

import javax.persistence.*;

@Entity
@Table(name = "checkite_status")
public class CheckitemStatus {

	private Integer checkItemStatusId;
	private Integer status;
	private String comment;
	private String deliverables;
	private Integer prjtype;
	private Integer importance;
	private String description;
	private Integer history;
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

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	@Column(name = "history")
	public Integer getHistory() {
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

	public void setHistory(Integer history) {
		this.history = history;
	}

	public void setProblem(Integer problem) {
		this.problem = problem;
	}

	public void setCheckitem(Checkitem checkitem) {
		this.checkitem = checkitem;
	}

}
