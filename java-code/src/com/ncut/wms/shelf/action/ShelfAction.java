package com.ncut.wms.shelf.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ncut.wms.shelf.model.Shelf;
import com.ncut.wms.shelf.service.ShelfService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("shelfAction")
@Scope("prototype")
public class ShelfAction extends ActionSupport {

	/* ======以下业务逻辑======== */
	public String getShelfList() {
		List<Shelf> sList = sService.getShelfList();
		String shelfJson = JSONArray.fromObject(sList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(shelfJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	
	/* ======以下声明======== */
	private ShelfService sService;

	@Resource
	public void setsService(ShelfService sService) {
		this.sService = sService;
	}
}
