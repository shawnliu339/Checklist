package com.sic.ocms.dao.project.impl;



import org.springframework.stereotype.Repository;

import com.sic.ocms.dao.base.impl.BaseDAOImpl;
import com.sic.ocms.dao.project.ProjectDAO;
import com.sic.ocms.persistence.Project;

@Repository("projectDAO")
public class ProjectDAOImpl extends BaseDAOImpl<Project> implements ProjectDAO{

}
