package com.sic.ocms.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("checklist")
@Scope("prototype")
public class ChecklistAction extends ActionSupport {
	
	public String execute() {
		return "show";
	}

}
