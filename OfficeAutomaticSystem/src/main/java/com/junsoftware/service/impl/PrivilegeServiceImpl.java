package com.junsoftware.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.PrivilegeDAO;
import com.junsoftware.domain.Privilege;
import com.junsoftware.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeDAO privilegeDAoImpl;
	
	@Override
	public List<Privilege> findAll() {
		// TODO Auto-generated method stub
		return privilegeDAoImpl.findAll();
	}

	@Override
	public List<Privilege> findByIds(Long[] privilegeIds) {
		
		List<Privilege> list=new ArrayList<Privilege>();
		
		if(privilegeIds!=null&&privilegeIds.length>0){
			
			for (int i = 0; i < privilegeIds.length; i++) {
				Privilege privilege = privilegeDAoImpl
						.findById(privilegeIds[i]);
				list.add(privilege);
			}
		}
		
		return list;
	}

	@Override
	public List<Privilege> findTopPrivileges() {
		return privilegeDAoImpl.findTopPrivileges();
	}

	@Override
	public List<String> findAllPrivileges() {
		// TODO Auto-generated method stub
		return privilegeDAoImpl.findAllPrivileges();
	}
	
}
