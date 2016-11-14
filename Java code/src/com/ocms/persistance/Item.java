package com.ocms.persistance;

import java.util.Set;

public class Item {
	private int itemId;
	private String name;
	private double percentage;
	private Set<CheckitemStatus> checkitemStatuses;
	private Item parent;
	private Set<Item> children;

}
