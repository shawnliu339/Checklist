package com.ocms.persistance;

import java.util.Set;

import javax.persistence.Entity;

@Entity()
public class Checkitem {
	private int checkitemId;
	private String content;
	private int relatedItemId;
	private int  relatedGoalId;
	private Set<CheckitemStatus> checkitemstatus;

}
