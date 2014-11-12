package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.Privilege;

public interface PrivilegeService {

	List<Privilege> findAll();

	List<Privilege> findByIds(Long[] privilegeIds);

	List<Privilege> findTopPrivileges();

	List<String> findAllPrivileges();
	
}
