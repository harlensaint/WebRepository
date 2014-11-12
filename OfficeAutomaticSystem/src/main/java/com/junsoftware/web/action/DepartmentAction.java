package com.junsoftware.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.junsoftware.domain.Department;
import com.junsoftware.domain.PaginationBean;
import com.junsoftware.extra.ExtraAction;
import com.junsoftware.utils.HqlUtils;
import com.junsoftware.utils.ServiceUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends ExtraAction<Department> {

	public String list() {

		/* List<Department> departments = departmentServiceImpl.findAll(); */
		/*
		 * List<Department> departments = null; if (model.getParent() != null &&
		 * model.getParent().getId() != null) { // 说明不是顶级部门，要查询的是子部门 departments
		 * = departmentServiceImpl.findChildren(model.getParent() .getId()); //
		 * 将parent.id对于的Department压入值栈中 Department parent =
		 * departmentServiceImpl.findById(model .getParent().getId());
		 * set("parent", parent); } else { // 说明要查找的是顶级的部门 departments =
		 * departmentServiceImpl.findTopDepartment(); }
		 * 
		 * set("departments", departments);
		 */

		HqlUtils hql = new HqlUtils(Department.class);
		if (model.getParent() != null && model.getParent().getId() != null) {
			// 说明要查询的是子部门
			hql.addWhereCondition("o.parent.id = ? ", model.getParent().getId());
		} else {
			// 说明要查询的是顶级部门
			hql.addWhereCondition("o.parent is null");
		}

		PaginationBean paginationBean = departmentServiceImpl
				.findPaginationBean(hql, currentPage);
		
		push(paginationBean);

		return LIST;
	}

	public String saveUI() {

		// List<Department> all = departmentServiceImpl.findAll();
		// 将数据库中的所有的顶级部门信息全部查找出来
		List<Department> topDepartments = departmentServiceImpl
				.findTopDepartment();

		// 通过工具类来迭代获取数据，并将最终的集合返回

		List<Department> treeDepartments = ServiceUtils.getTreeDepartments(
				topDepartments, null);

		set("all", treeDepartments);
		return TOSAVEUI;
	}

	public String save() {

		if (model.getParent().getId() != null) {
			// 说明不是顶级部门，要与父类部门关联起来
			Department parent = departmentServiceImpl.findById(model
					.getParent().getId());
			model.setParent(parent);// 瞬态对象与持久态对象关联起来
		} else {
			model.setParent(null);
		}
		departmentServiceImpl.save(model);
		return TOLIST;
	}

	public String delete() {
		try {
			departmentServiceImpl.delete(model);
			// 说明删除成功了
			return TOLIST;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 发生异常，说明不能删除
			addActionError(e.getMessage());
			ServletActionContext.getRequest().setAttribute("errMsg",
					e.getMessage());
			// List<Department> departments = departmentServiceImpl.findAll();
			return "listChain";
		}
	}

	public String updateUI() {
		Department department = departmentServiceImpl.findById(model.getId());
		push(department);

		// List<Department> all = departmentServiceImpl.findAll();
		// 将数据库中的所有的顶级部门信息全部查找出来
		List<Department> topDepartments = departmentServiceImpl
				.findTopDepartment();
		List<Department> treeDepartments = ServiceUtils.getTreeDepartments(
				topDepartments, department.getId());
		set("all", treeDepartments);
		return TOUPDATEUI;
	}

	public String update() {

		departmentServiceImpl.update(model);

		return TOLIST;
	}
}
