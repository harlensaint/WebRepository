package com.junsoftware.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoftware.dao.DepartmentDAO;
import com.junsoftware.domain.Department;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.service.DepartmentService;
import com.junsoftware.utils.HqlUtils;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDAO departmentDAOImpl;

	@Override
	public List<Department> findAll() {
		List<Department> list = departmentDAOImpl.findAll();
		return list;
	}

	@Override
	public void save(Department model) {
		departmentDAOImpl.add(model);
	}

	@Override
	public void delete(Department model) throws Exception {
		model = departmentDAOImpl.findById(model.getId());
		Set children = model.getChildren();
		if (!children.isEmpty()) {
			// 说明不是空的，有子部门存在，不能删除！
			throw new Exception("此部门存在子部门，不能删除！");
		} else {
			// 说明可以删除
			departmentDAOImpl.delete(model);
		}
	}

	@Override
	public Department findById(Long id) {
		return departmentDAOImpl.findById(id);
	}

	@Override
	public void update(Department model) {
		// 先去数据库中查找
		Department department = departmentDAOImpl.findById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());

		if (model.getParent() != null && model.getParent().getId() != null) {
			// 说明用户选择了上级部门，重新设定上级部门
			Department parent = departmentDAOImpl.findById(model.getParent()
					.getId());
			department.setParent(parent);
		} else {
			// 说明用户没有选择上级部门，说明此部门没有上级部门
			department.setParent(null);
			;
		}
	}

	@Override
	public List<Department> findChildren(Long id) {

		return departmentDAOImpl.findChildern(id);
	}

	@Override
	public List<Department> findTopDepartment() {

		return departmentDAOImpl.findTopDepartment();
	}

	@Override
	public PaginationBean findPaginationBean(HqlUtils hql, int currentPage) {
		// TODO Auto-generated method stub
		return departmentDAOImpl.findPaginationBean(hql, currentPage);
	}

}
