package com.junsoftware.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.junsoftware.dao.DepartmentDAO;
import com.junsoftware.domain.Department;
import com.junsoftware.extra.ExtraDAOImpl;

@Repository
@SuppressWarnings("all")
public class DepartmentDAOImpl extends ExtraDAOImpl<Department> implements
		DepartmentDAO {

	@Override
	public List<Department> findChildern(Long id) {

		List<Department> list = getHibernateTemplate().findByNamedQuery(
				"findChildren", id);
		return list;
	}

	@Override
	public List<Department> findTopDepartment() {
		List<Department> topDepartments = getHibernateTemplate()
				.findByNamedQuery("findTopDepartment");
		return topDepartments;
	}

}
