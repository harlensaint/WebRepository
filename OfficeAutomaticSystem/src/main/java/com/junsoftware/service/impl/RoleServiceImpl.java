package com.junsoftware.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.RoleDAO;
import com.junsoftware.domain.Privilege;
import com.junsoftware.domain.Role;
import com.junsoftware.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAOImpl;

	@Override
	public List<Role> findAll() {

		return roleDAOImpl.findAll();
	}

	@Override
	public Boolean findByRoleName(String name) {
		return roleDAOImpl.findByRoleName(name);

	}

	@Override
	public void save(Role model) {
		roleDAOImpl.add(model);
	}

	@Override
	public void delete(Role model) throws Exception {
		// TODO Auto-generated method stub
		Role role = roleDAOImpl.findById(model.getId());
		if (role.getUsers() != null && role.getUsers().size() > 0) {
			// 说明是此岗位中存在用户，不能删除
			throw new Exception("此岗位中存在用户，不能删除！");
		} else {
			// 说明此岗位中不存在用户，可以删除
			roleDAOImpl.delete(role);
		}
	}

	@Override
	public Role findById(Long id) {
		Role role = roleDAOImpl.findById(id);
		return role;
	}

	@Override
	public void update(Role model) {

		Role role = roleDAOImpl.findById(model.getId());

		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleDAOImpl.update(role);
	}


}
