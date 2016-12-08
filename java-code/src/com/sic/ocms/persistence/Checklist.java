package com.sic.ocms.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "checklist")
public class Checklist {

	private Integer checklistId;
	private String name;
	private Double percentage;
	private Set<Item> items;
	private Project project;

	@Id
	@GeneratedValue
	@Column(name = "checklist_id")
	public Integer getChecklistId() {
		return checklistId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "percentage")
	public Double getPercentage() {
		return percentage;
	}

	@OneToMany(mappedBy = "checklist")
	public Set<Item> getItems() {
		return items;
	}

	@ManyToOne
	@JoinColumn(name = "project_id")
	public Project getProject() {
		return project;
	}

	public void setChecklistId(Integer checklistId) {
		this.checklistId = checklistId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
