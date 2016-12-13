package com.sic.ocms.service;

import org.junit.Test;

import com.sic.ocms.persistence.CheckitemStatus;

public class ChecklistServiceTest {


	@Test
	public void test() throws Exception{

		ChecklistService cs = new ChecklistService();
		CheckitemStatus cis = new CheckitemStatus();


		cis.setCheckItemStatusId(45);
		cis.setStatus(1);
		cis.setComment("");

		cs.changeCheckitemStatus(cis);

	}




}
