package com.junsoftware.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.junsoftware.domain.Department;

@SuppressWarnings("all")
public class ServiceUtils {

	public static List<Department> getTreeDepartments(
			List<Department> topDepartments,Long id) {
		List<Department> orderDepartments = new ArrayList<Department>();
		if (topDepartments != null && topDepartments.size() > 0) {
			// 说明有顶级部门
			showTree(topDepartments, orderDepartments, "┠",id);
		}
		return orderDepartments;
	}

	private static void showTree(List<Department> topDepartments,
			List<Department> orderDepartment, String preffix,Long id) {
		
		for (Department department : topDepartments) {
			
			//首先判断是否要进行id的过滤
			
			if(id!=null){
				if(department!=null&department.getId()==id){
					//说明是要过滤的department对象
					continue;
				}
			}
			
			// 上级部门,这里要修改department中的name值，但是不能直接修改数据库中的，所以要新建一个department对象
			Department dept=new Department();
			dept.setId(department.getId());
			dept.setName(preffix+department.getName());
			
			orderDepartment.add(dept);
			
			// 下级部门
			Set<Department> children = department.getChildren();
			// 将此子部门进行循环迭代出来
			showTree(new ArrayList<Department>(children), orderDepartment,"　　"+preffix,id);
		}
	}

}
