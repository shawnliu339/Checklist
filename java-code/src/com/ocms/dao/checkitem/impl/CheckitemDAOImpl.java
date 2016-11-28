package com.ocms.dao.checkitem.impl;



import org.springframework.stereotype.Repository;

import com.ocms.dao.base.impl.BaseDAOImpl;
import com.ocms.dao.checkitem.CheckitemDAO;
import com.ocms.persistence.Checkitem;

@Repository("checkitemDAO")
public class CheckitemDAOImpl extends BaseDAOImpl<Checkitem> implements CheckitemDAO {


}
