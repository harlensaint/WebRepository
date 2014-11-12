package com.junsoftware.dao;

import java.util.List;

import com.junsoftware.domain.Department;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.extra.IExtraDAO;
import com.junsoftware.utils.HqlUtils;

public interface DepartmentDAO extends IExtraDAO<Department> {

	List<Department> findChildern(Long id);

	List<Department> findTopDepartment();

	PaginationBean findPaginationBean(HqlUtils hql, int currentPage);

}
