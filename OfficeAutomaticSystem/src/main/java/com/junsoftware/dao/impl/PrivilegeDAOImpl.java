package com.junsoftware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.junsoftware.dao.PrivilegeDAO;
import com.junsoftware.domain.Privilege;
import com.junsoftware.extra.ExtraDAOImpl;

@Repository
@SuppressWarnings("all")
public class PrivilegeDAOImpl extends ExtraDAOImpl<Privilege> implements
		PrivilegeDAO {

	@Override
	public List<Privilege> findTopPrivileges() {

		List<Privilege> topPrivileges = getHibernateTemplate()
				.findByNamedQuery("findTopPrivileges");
		return topPrivileges;
	}

	@Override
	public List<String> findAllPrivileges() {
		List<String> list = getHibernateTemplate().findByNamedQuery("findAllPrivileges");
		return list;
	}

}
