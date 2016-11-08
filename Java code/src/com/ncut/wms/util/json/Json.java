package com.ncut.wms.util.json;

public class Json {
	private boolean success = false;
	private String message = "";
	private Object obj = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
