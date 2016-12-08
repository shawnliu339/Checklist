package com.sic.ocms.dto;

public class ChecklistDTO {
	//not hidden
	private String group1_name;
	private String group2_name;
	private String group3_name;
	private String checkitem_content;
	private String description;
	private String typical_deliverables;
	private Integer status;
	private Integer problem;
	private String comment;
	private Integer prjtype;
	private Integer importance;

	//hidden
	private Integer group1_id;
	private Integer checkitem_content_id;

	//detagrid field
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;


	public String getGroup1_name() {
		return group1_name;
	}
	public void setGroup1_name(String group1_name) {
		this.group1_name = group1_name;
	}
	public String getGroup2_name() {
		return group2_name;
	}
	public void setGroup2_name(String group2_name) {
		this.group2_name = group2_name;
	}
	public String getGroup3_name() {
		return group3_name;
	}
	public void setGroup3_name(String group3_name) {
		this.group3_name = group3_name;
	}
	public String getCheckitem_content() {
		return checkitem_content;
	}
	public void setCheckitem_content(String checkitem_content) {
		this.checkitem_content = checkitem_content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypical_deliverables() {
		return typical_deliverables;
	}
	public void setTypical_deliverables(String typical_deliverables) {
		this.typical_deliverables = typical_deliverables;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getProblem() {
		return problem;
	}
	public void setProblem(Integer problem) {
		this.problem = problem;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getPrjtype() {
		return prjtype;
	}
	public void setPrjtype(Integer prjtype) {
		this.prjtype = prjtype;
	}
	public Integer getImportance() {
		return importance;
	}
	public void setImportance(Integer importance) {
		this.importance = importance;
	}
	public Integer getGroup1_id() {
		return group1_id;
	}
	public void setGroup1_id(Integer group1_id) {
		this.group1_id = group1_id;
	}
	public Integer getCheckitem_content_id() {
		return checkitem_content_id;
	}
	public void setCheckitem_content_id(Integer checkitem_conent_id) {
		this.checkitem_content_id = checkitem_conent_id;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}



}
