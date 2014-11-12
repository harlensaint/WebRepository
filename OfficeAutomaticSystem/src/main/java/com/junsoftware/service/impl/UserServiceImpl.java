package com.junsoftware.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.DepartmentDAO;
import com.junsoftware.dao.RoleDAO;
import com.junsoftware.dao.UserDAO;
import com.junsoftware.domain.Department;
import com.junsoftware.domain.Role;
import com.junsoftware.domain.User;
import com.junsoftware.service.UserService;

@Service
@SuppressWarnings("all")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAOImpl;

	@Autowired
	private DepartmentDAO departmentDAOImpl;

	@Autowired
	private RoleDAO roleDAOImpl;

	@Override
	public Boolean findLoginName(String loginName) {

		return userDAOImpl.findLoginName(loginName);
	}

	@Override
	public void add(User model) {
		userDAOImpl.add(model);
	}

	@Override
	public List<User> findAll() {
		return userDAOImpl.findAll();
	}

	@Override
	public void delete(User model) {
		// 先查出数据库中的值
		model = userDAOImpl.findById(model.getId());

		if (model != null) {
			userDAOImpl.delete(model);
		}
	}

	@Override
	public User findById(User model) {
		return userDAOImpl.findById(model.getId());
	}

	@Override
	public void update(User model,Long[] roleIds) {
		User user = userDAOImpl.findById(model.getId()); // 获取持久态的对象

		// 修改基本参数
		user.setDescription(model.getDescription());
		user.setEmail(model.getEmail());
		user.setLoginName(model.getLoginName());
		user.setSex(model.getSex());
		user.setTelephone(model.getTelephone());
		user.setName(model.getName());

		if (model.getDepartment() != null
				&& model.getDepartment().getId() != null) {
			// 说明选择了部门
			Department department = departmentDAOImpl.findById(model
					.getDepartment().getId());
			user.setDepartment(department);
		} else {
			user.setDepartment(null);
		}

		if (model.getRoles() != null && model.getRoles().size() > 0) {
			// 说明用户选择了岗位
			List<Role> roles = new ArrayList<Role>();
			
			for (int i = 0; i < roleIds.length; i++) {
				Role role = roleDAOImpl.findById(roleIds[i]);
				roles.add(role);
			}
			
			user.setRoles(new HashSet<Role>(roles));
		} else {
			user.setRoles(null);
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDAOImpl.update(user);
	}

	@Override
	public User findByLoginNameAndPassword(String loginName,String password) {
		// TODO Auto-generated method stub
		return userDAOImpl.findByLoginNameAndPassword(loginName,password);
	}

}
