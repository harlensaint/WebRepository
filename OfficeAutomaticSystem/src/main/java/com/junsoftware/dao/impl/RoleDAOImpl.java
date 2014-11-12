package com.junsoftware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.junsoftware.dao.RoleDAO;
import com.junsoftware.domain.Role;
import com.junsoftware.extra.ExtraDAOImpl;

@SuppressWarnings("all")
@Repository
public class RoleDAOImpl extends ExtraDAOImpl<Role> implements RoleDAO {

	@Override
	public Boolean findByRoleName(String name) {

		List<Role> list = getHibernateTemplate().findByNamedQuery(
				"findByRoleName", name);

		return list.size() > 0 ? true : false;
	}

}
