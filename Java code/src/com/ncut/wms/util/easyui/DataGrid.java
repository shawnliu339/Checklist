package com.ncut.wms.util.easyui;

import java.util.ArrayList;
import java.util.List;

public class DataGrid<T> {

	private int total = 0;
	private List<T> rows = new ArrayList<T>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int totals) {
		this.total = totals;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}


}
