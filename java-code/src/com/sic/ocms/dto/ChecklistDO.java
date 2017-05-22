package com.sic.ocms.dto;

public class ChecklistDO {

	//not hidden
	//item
	private String fieldName;
	private Double group1Percentage;
	private String group2Name;
	private Double group2Percentage;
	private String group3Name;
	private Double group3Percentage;
	//checkitem
	private String checkitemContent;
	private String description;
	private String typicalDeliverables;
	//checkitemstatus
	private Integer status;
	private Integer problem;
	private String comment;
	private Integer prjtype;
	private Integer importance;

	//hidden
	private Integer group1Id;
	private Integer checkitemStatusId;
	private Integer checkitemId;



	public String getFieldName() {
		return fieldName;
	}
	public Double getGroup1Percentage() {
		return group1Percentage;
	}
	public String getGroup2Name() {
		return group2Name;
	}
	public Double getGroup2Percentage() {
		return group2Percentage;
	}
	public String getGroup3Name() {
		return group3Name;
	}
	public Double getGroup3Percentage() {
		return group3Percentage;
	}
	public String getCheckitemContent() {
		return checkitemContent;
	}
	public String getDescription() {
		return description;
	}
	public String getTypicalDeliverables() {
		return typicalDeliverables;
	}
	public Integer getStatus() {
		return status;
	}
	public Integer getProblem() {
		return problem;
	}
	public String getComment() {
		return comment;
	}
	public Integer getPrjtype() {
		return prjtype;
	}
	public Integer getImportance() {
		return importance;
	}
	public Integer getGroup1Id() {
		return group1Id;
	}
	public Integer getCheckitemStatusId() {
		return checkitemStatusId;
	}
	public Integer getCheckitemId() {
		return checkitemId;
	}
	public void setFieldName(String group1Name) {
		this.fieldName = group1Name;
	}
	public void setGroup1Percentage(Double group1Percentage) {
		this.group1Percentage = group1Percentage;
	}
	public void setGroup2Name(String group2Name) {
		this.group2Name = group2Name;
	}
	public void setGroup2Percentage(Double group2Percentage) {
		this.group2Percentage = group2Percentage;
	}
	public void setGroup3Name(String group3Name) {
		this.group3Name = group3Name;
	}
	public void setGroup3Percentage(Double group3Percentage) {
		this.group3Percentage = group3Percentage;
	}
	public void setCheckitemContent(String checkitemContent) {
		this.checkitemContent = checkitemContent;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTypicalDeliverables(String typicalDeliverables) {
		this.typicalDeliverables = typicalDeliverables;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setProblem(Integer problem) {
		this.problem = problem;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setPrjtype(Integer prjtype) {
		this.prjtype = prjtype;
	}
	public void setImportance(Integer importance) {
		this.importance = importance;
	}
	public void setGroup1Id(Integer group1Id) {
		this.group1Id = group1Id;
	}
	public void setCheckitemStatusId(Integer checkitemStatusId) {
		this.checkitemStatusId = checkitemStatusId;
	}
	public void setCheckitemId(Integer checkitemId) {
		this.checkitemId = checkitemId;
	}
	@Override
	public String toString() {
		return "ChecklistDO [group1Name=" + fieldName + ", group1Percentage=" + group1Percentage + ", group2Name="
				+ group2Name + ", group2Percentage=" + group2Percentage + ", group3Name=" + group3Name
				+ ", group3Percentage=" + group3Percentage + ", checkitemContent=" + checkitemContent + ", description="
				+ description + ", typicalDeliverables=" + typicalDeliverables + ", status=" + status + ", problem="
				+ problem + ", comment=" + comment + ", prjtype=" + prjtype + ", importance=" + importance
				+ ", group1Id=" + group1Id + ", checkitemStatusId=" + checkitemStatusId + ", checkitemId=" + checkitemId
				+ "]";
	}
}
