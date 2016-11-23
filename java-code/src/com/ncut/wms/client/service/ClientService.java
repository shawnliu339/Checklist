package com.ncut.wms.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ncut.wms.client.dao.impl.ClientDAO;
import com.ncut.wms.client.dto.ClientDTO;
import com.ncut.wms.client.model.Client;
import com.ncut.wms.util.easyui.DataGrid;

@Service("clientService")
public class ClientService {

	/* ======以下业务逻辑======== */
	public DataGrid<ClientDTO> datagrid(ClientDTO sDTO) {
		DataGrid<ClientDTO> dg = new DataGrid<ClientDTO>();
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Client c";
		if(sDTO.getClientName()!=null && !"".equals(sDTO.getClientName().trim())){
			hql+=" where c.clientName like :clientName";
			map.put("clientName", "%"+sDTO.getClientName().trim()+"%");
		}
		String totalHql = "select count(*) "+hql;
		//实现排序
		if(sDTO.getSort()!=null){
			hql+=" order by "+sDTO.getSort()+" "+sDTO.getOrder();
		}
		List<Client> clients = cDAO.list(hql, map, sDTO.getPage(), sDTO.getRows());
		List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
		for(Client client:clients){
			ClientDTO cDTO = new ClientDTO();
			BeanUtils.copyProperties(client, cDTO);
			
			clientDTOs.add(cDTO);
		}
		dg.setTotal(cDAO.count(totalHql, map));
		dg.setRows(clientDTOs);
		return dg;
	}

	public void add(Client client) {
		cDAO.add(client);
	}
	
	public void update(Client client) {
		cDAO.update(client);
	}
	
	public void delete(ClientDTO sDTO) {
		
		String ids[] = sDTO.getIds().split(",");

		for (int i = 0; i < ids.length; i++) {
			cDAO.delete(Integer.valueOf(ids[i]));
		}

	}

	public List<Client> getClientList() {
		List<Client> clientList = cDAO.list("from Client");
		return clientList;
	}
	/* ======以下声明======== */
	private ClientDAO cDAO;
	
	@Resource
	public void setcDAO(ClientDAO cDAO) {
		this.cDAO = cDAO;
	}
}
