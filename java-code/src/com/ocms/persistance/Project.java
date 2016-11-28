package com.ocms.persistance;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Project {


	private int projectId;
	private int prjId;
	private String name;
	private Set<Checklist> checklists;



	@Id
	@GeneratedValue
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getPrjId() {
		return prjId;
	}
	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Checklist> getChecklists() {
		return checklists;
	}
	public void setChecklists(Set<Checklist> checklists) {
		this.checklists = checklists;
	}



}
