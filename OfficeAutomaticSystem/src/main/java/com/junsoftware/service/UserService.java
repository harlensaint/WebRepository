package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.User;

public interface UserService {

	Boolean findLoginName(String loginName);

	void add(User model);

	List<User> findAll();

	void delete(User model);

	User findById(User model);

	void update(User model, Long[] roleIds);

	void update(User user);

	User findByLoginNameAndPassword(String loginName, String password);
	
}
