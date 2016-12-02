package com.sic.ocms.dao.checkitemstatus.impl;



import org.springframework.stereotype.Repository;

import com.sic.ocms.dao.base.impl.BaseDAOImpl;
import com.sic.ocms.dao.checkitemstatus.CheckitemStatusDAO;
import com.sic.ocms.persistence.CheckitemStatus;

@Repository("checkitemstatusDAO")
public class CheckitemStatusDAOImpl extends BaseDAOImpl<CheckitemStatus> implements CheckitemStatusDAO{

}
