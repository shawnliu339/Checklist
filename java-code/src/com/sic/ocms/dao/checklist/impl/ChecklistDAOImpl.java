package com.sic.ocms.dao.checklist.impl;



import org.springframework.stereotype.Repository;

import com.sic.ocms.dao.base.impl.BaseDAOImpl;
import com.sic.ocms.dao.checklist.ChecklistDAO;
import com.sic.ocms.persistence.Checklist;

@Repository("checklistDAO")
public class ChecklistDAOImpl extends BaseDAOImpl<Checklist> implements ChecklistDAO{

}
