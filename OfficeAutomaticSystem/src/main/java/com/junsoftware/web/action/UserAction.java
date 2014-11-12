package com.junsoftware.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.junsoftware.domain.Department;
import com.junsoftware.domain.Role;
import com.junsoftware.domain.User;
import com.junsoftware.extra.ExtraAction;
import com.junsoftware.utils.MD5Utlis;
import com.junsoftware.utils.ServiceUtils;

@Controller
@Scope("prototype")
public class UserAction extends ExtraAction<User> {

	private Long[] roleIds;

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public String list() {
		List<User> users = userServiceImpl.findAll();
		set("list", users);
		return LIST;
	}

	public String saveUI() {

		List<Department> topDepartments = departmentServiceImpl
				.findTopDepartment();

		// 通过工具类来迭代获取数据，并将最终的集合返回

		List<Department> treeDepartments = ServiceUtils.getTreeDepartments(
				topDepartments, null);
		set("departments", treeDepartments);
		// 获取所有的岗位信息
		List<Role> roles = roleServiceImpl.findAll();

		// 要入valuestack中
		set("roles", roles);

		return TOSAVEUI;
	}

	public String findLoginName() {
		Boolean isExist = userServiceImpl.findLoginName(model.getLoginName());

		String flag = "0";
		if (isExist) {
			// 说明存在
			flag = "1";
		}

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String save() {

		// userServiceImpl.add(model);
		Department department = model.getDepartment();

		// 判断部门是否为空
		if (department != null && department.getId() != 0) {
			// 说明此用户有部门
			department = departmentServiceImpl.findById(department.getId());
			model.setDepartment(department);
		}

		if (roleIds != null) {
			// 说明用户选择了岗位信息

			List<Role> roles = new ArrayList<Role>();

			for (int i = 0; i < roleIds.length; i++) {
				Role role = roleServiceImpl.findById(roleIds[i]);
				roles.add(role);
			}

			model.setRoles(new HashSet<Role>(roles));
		}

		// 设置默认的秘密
		model.setPassword(MD5Utlis.md5(User.INITPWD));
		userServiceImpl.add(model);
		return TOLIST;
	}

	public String delete() {

		userServiceImpl.delete(model);

		return TOLIST;
	}

	public String updateUI() {

		List<Department> topDepartments = departmentServiceImpl
				.findTopDepartment();

		// 通过工具类来迭代获取数据，并将最终的集合返回

		List<Department> treeDepartments = ServiceUtils.getTreeDepartments(
				topDepartments, null);
		set("departments", treeDepartments);
		// 获取所有的岗位信息
		List<Role> allRoles = roleServiceImpl.findAll();
		// 要入valuestack中

		set("allRoles", allRoles);

		// 将要修改的当前用户信息压入值栈中
		model = userServiceImpl.findById(model);
		push(model);

		Set<Role> selfRoles = model.getRoles();

		// 设置roleIds的值，这样可以在值栈中去获取到
		int index = 0;
		roleIds = new Long[selfRoles.size()];
		for (Role role : selfRoles) {
			roleIds[index++] = role.getId();
		}

		return TOUPDATEUI;
	}

	public String update() {
		// 先去数据库中查找，确保所有的信息不丢失

		User user = userServiceImpl.findById(model); // 获取持久态的对象

		// 修改基本参数
		user.setDescription(model.getDescription());
		user.setEmail(model.getEmail());
		user.setLoginName(model.getLoginName());
		user.setSex(model.getSex());
		user.setTelephone(model.getTelephone());
		user.setName(model.getName());

		if (model.getDepartment() != null
				&& model.getDepartment().getId() != null) {
			// 说明选择了部门
			Department department = departmentServiceImpl.findById(model
					.getDepartment().getId());
			user.setDepartment(department);
		} else {
			user.setDepartment(null);
		}

		if (model.getRoles() != null && model.getRoles().size() > 0) {
			// 说明用户选择了岗位
			List<Role> roles = new ArrayList<Role>();

			for (int i = 0; i < roleIds.length; i++) {
				Role role = roleServiceImpl.findById(roleIds[i]);
				roles.add(role);
			}

			user.setRoles(new HashSet<Role>(roles));
		} else {
			user.setRoles(null);
		}

		userServiceImpl.update(user);
		return TOLIST;
	}

	public String initPassword() {

		// 先去数据库中查找
		User user = userServiceImpl.findById(model);

		user.setPassword(MD5Utlis.md5(User.INITPWD));

		userServiceImpl.update(user);

		return TOLIST;
	}

	public String logout() {
		// 先销毁session

		ServletActionContext.getRequest().getSession().invalidate();

		return "logoutUI";
	}

	public String login() {

		User user=userServiceImpl.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		
		if(user!=null){
			//说明用户名和密码正确，存入session中去
			HttpSession session = ServletActionContext.getRequest().getSession();
			 if(session.getAttribute("loginuser")!=null && session.getAttribute("loginuser").equals(user)){
				 //说明用户已经登陆
				 session.invalidate();
				 //重新创建一个session
				 session=ServletActionContext.getRequest().getSession();
			 }
			session.setAttribute("loginUser", user);
			return "toIndex";
		}else{
			//说明用户名或秘密不存在
			addActionError("用户名或密码错误！");
			return "loginUI";
		}
		
	}

}
