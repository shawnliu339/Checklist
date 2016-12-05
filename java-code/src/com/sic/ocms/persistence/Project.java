package com.sic.ocms.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	private Integer projectId;
	private Integer prjId;
	private String name;
	private Set<Checklist> checklists;

	@Id
	@GeneratedValue
	@Column(name = "project_id")
	public Integer getProjectId() {
		return projectId;
	}

	@Column(name = "prj_id")
	public Integer getPrjId() {
		return prjId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}


	@OneToMany(mappedBy = "project")
	public Set<Checklist> getChecklists() {
		return checklists;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public void setPrjId(Integer prjId) {
		this.prjId = prjId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChecklists(Set<Checklist> checklists) {
		this.checklists = checklists;
	}
}
