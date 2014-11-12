package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.Privilege;
import com.junsoftware.domain.Role;

public interface RoleService {

	List<Role> findAll();

	Boolean findByRoleName(String name);

	void save(Role model);

	void delete(Role model) throws Exception;

	Role findById(Long id);

	void update(Role model);


}
