package com.junsoftware.dao;

import java.util.List;

import org.apache.struts2.components.ExtraParameterProvider;

import com.junsoftware.domain.Role;
import com.junsoftware.extra.IExtraDAO;

public interface RoleDAO extends IExtraDAO<Role> {

	Boolean findByRoleName(String name);

	
}
