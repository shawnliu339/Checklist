package com.ncut.wms.supplier.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.supplier.dao.SupplierDAO;
import com.ncut.wms.supplier.dto.SupplierDTO;
import com.ncut.wms.supplier.model.Supplier;
import com.ncut.wms.util.easyui.DataGrid;
import com.ncut.wms.util.system.ToExcel;

@Service("supplierService")
public class SupplierService {
	
	/* ======以下声明======== */
	private SupplierDAO supplierDAO;

	@Resource
	public void setSupplierDAO(SupplierDAO supplierDAO) {
		this.supplierDAO = supplierDAO;
	}

	/* ======以下业务逻辑======== */
	public DataGrid<SupplierDTO> datagrid(SupplierDTO supplierDTO) {
		DataGrid<SupplierDTO> dg = new DataGrid<SupplierDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Supplier supplier";
		if(supplierDTO.getSupplierName()!=null && !"".equals(supplierDTO.getSupplierName().trim())){
			hql+=" where supplier.supplierName like :supplierName";
			map.put("supplierName", "%"+supplierDTO.getSupplierName().trim()+"%");
		}
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(supplierDTO.getSort()!=null){
			hql+=" order by "+supplierDTO.getSort()+" "+supplierDTO.getOrder();
		}
		List<Supplier> suppliers = supplierDAO.list(hql, map, supplierDTO.getPage(), supplierDTO.getRows());
		List<SupplierDTO> supplerDTOs = new ArrayList<SupplierDTO>();
		for(Supplier supp:suppliers){
			SupplierDTO suppDTO = new SupplierDTO();
			BeanUtils.copyProperties(supp, suppDTO);
			
			supplerDTOs.add(suppDTO);
		}
		dg.setTotal(supplierDAO.count(totalHql, map));
		dg.setRows(supplerDTOs);
		return dg;
	}

	public void add(Supplier supplier) {
		supplierDAO.add(supplier);
	}
	
	public void update(Supplier supplier) {
		supplierDAO.update(supplier);
	}
	
	public void delete(SupplierDTO supplierDTO) {
		
		String ids[] = supplierDTO.getIds().split(",");

		for (int i = 0; i < ids.length; i++) {
			supplierDAO.delete(Integer.valueOf(ids[i]));
		}

	}

	public List<Supplier> getSupplierList() {
		List<Supplier> supplierList = supplierDAO.list("from Supplier");
		return supplierList;
	}
	
	public void toExcel() {
		ToExcel<Supplier> supplierToExcel = new ToExcel<Supplier>();
		//添加表头信息
		Vector<String> columnName = new Vector<String>();
		columnName.add("供应商ID");
		columnName.add("供应商名称");
		columnName.add("联系人姓名");
		columnName.add("联系人电话");
		columnName.add("联系人地址");
		columnName.add("备注");
		//获取桌面路径
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String path = fsv.getHomeDirectory().toString();
		//excel导出
		supplierToExcel.writeExcel(getSupplierList(), path + "\\supplier.xls", "供应商信息", columnName);
	}
	
	public Supplier findById(Integer id) {
		return supplierDAO.load(id);
		
	}

}
