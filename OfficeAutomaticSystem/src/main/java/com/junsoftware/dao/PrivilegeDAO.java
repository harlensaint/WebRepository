package com.junsoftware.dao;

import java.util.List;

import com.junsoftware.domain.Privilege;
import com.junsoftware.extra.IExtraDAO;

public interface PrivilegeDAO extends IExtraDAO<Privilege>{

	List<Privilege> findTopPrivileges();

	List<String> findAllPrivileges();

}
