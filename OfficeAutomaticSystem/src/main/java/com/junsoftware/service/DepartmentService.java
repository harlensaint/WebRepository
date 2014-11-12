package com.junsoftware.service;

import java.util.List;

import com.junsoftware.domain.Department;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.utils.HqlUtils;

public interface DepartmentService {

	List<Department> findAll();

	void save(Department model);

	void delete(Department model) throws Exception;

	Department findById(Long id);

	void update(Department model);

	List<Department> findChildren(Long id);

	List<Department> findTopDepartment();

	PaginationBean findPaginationBean(HqlUtils hql, int currentPage);

}
