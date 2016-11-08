package com.ncut.wms.commodity.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.commodity.dao.CommodityCategoryDAO;
import com.ncut.wms.commodity.dto.CommodityCategoryDTO;
import com.ncut.wms.commodity.model.CommodityCategory;
import com.ncut.wms.util.easyui.DataGrid;

@Service("commodityCategoryService")
public class CommodityCategoryService {

	/* ======以下声明======== */
	private CommodityCategoryDAO cmdtCtgrDAO;

	@Resource
	public void setCmdtCtgrDAO(CommodityCategoryDAO cmdtCtgrDAO) {
		this.cmdtCtgrDAO = cmdtCtgrDAO;
	}

	/*======以下业务逻辑========*/
	public DataGrid<CommodityCategoryDTO> datagrid(
			CommodityCategoryDTO cmdtCtgrDTO) {
		DataGrid<CommodityCategoryDTO> dg = new DataGrid<CommodityCategoryDTO>();
		List<CommodityCategory> categories = cmdtCtgrDAO.list(
				"from CommodityCategory", cmdtCtgrDTO.getPage(),
				cmdtCtgrDTO.getRows());
		List<CommodityCategoryDTO> ctgrDTOs = new ArrayList<CommodityCategoryDTO>();
		for(CommodityCategory cC : categories) {
			CommodityCategoryDTO cCDTO = new CommodityCategoryDTO();
			BeanUtils.copyProperties(cC, cCDTO);
			//读取父类名字
			CommodityCategory pCategory = cmdtCtgrDAO.load(cCDTO.getPid());
			if(pCategory != null) {
				String pname = pCategory.getCname();
				cCDTO.setPname(pname);
			}
			
			ctgrDTOs.add(cCDTO);
		}
		int totals = cmdtCtgrDAO.count("select count (*) from CommodityCategory");
		dg.setRows(ctgrDTOs);
		dg.setTotal(totals);
		return dg;
	}

	public List<CommodityCategory> getCategoryList() {
		return cmdtCtgrDAO.list("from CommodityCategory");
	}

	public void update(CommodityCategory cmdCtgrt) {
		cmdtCtgrDAO.update(cmdCtgrt);
	}

	public void add(CommodityCategory cmdCtgrt) {
		cmdtCtgrDAO.add(cmdCtgrt);
	}
	
	public void delete(CommodityCategory cmdCtgrt) {
		cmdtCtgrDAO.delete(cmdCtgrt.getCid());
	}

}
