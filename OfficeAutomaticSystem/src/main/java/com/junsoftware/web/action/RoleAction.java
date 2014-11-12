package com.junsoftware.web.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.junsoftware.domain.Privilege;
import com.junsoftware.domain.Role;
import com.junsoftware.extra.ExtraAction;

@Controller
@Scope("prototype")
public class RoleAction extends ExtraAction<Role> {

	// 提供熟悉驱动来封装权限
	private Long[] privilegeIds;

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String list() {

		List<Role> roles = roleServiceImpl.findAll();

		// 压入值栈中去
		set("list", roles);

		return LIST;
	}

	public String findByRoleName() {

		Boolean returnVal = roleServiceImpl.findByRoleName(model.getName());

		String flag = "0";

		if (returnVal) {
			// 说明是有的
			flag = "1";
		}

		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return NONE;
	}

	public String save() {

		roleServiceImpl.save(model);

		return TOLIST;
	}

	public String delete() {

		try {
			// 此岗位没有用户，可以删除
			roleServiceImpl.delete(model);
			return TOLIST;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addActionError(e.getMessage());
			List<Role> list = roleServiceImpl.findAll();

			set("list", list);
			return LIST;
		}
	}

	public String updateUI() {

		Role role = roleServiceImpl.findById(model.getId());

		if (role != null) {
			push(role);
		}

		return TOUPDATEUI;
	}

	public String update() {

		roleServiceImpl.update(model);

		return TOLIST;
	}

	public String setPrivilegeUI() {

		// 先查找当前要修改的岗位存在数据库中吗
		Role role = roleServiceImpl.findById(model.getId());
		if (role != null) {
			// 说明用户存在
			push(role);
			// 准备数据库中的所有的权限数据
			// List<Privilege> privileges = privilegeServiceImpl.findAll();

			List<Privilege> privileges = privilegeServiceImpl
					.findTopPrivileges();

			set("privileges", privileges);

			// 准备权限数组，以便回显数据

			Set<Privilege> role_privileges = role.getPrivileges();

			if (role_privileges != null && role_privileges.size() > 0) {
				// 说明有权限数据
				privilegeIds = new Long[role_privileges.size()];

				int index = 0;
				for (Privilege privilege : role_privileges) {
					privilegeIds[index++] = privilege.getId();
				}
			}

			// 将权限数组压入到值栈中去
			set("privilegeIds", privilegeIds);

			return "toPrivilegeUI";
		} else {

			// 说明用户已被删除，不存在

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter()
						.print("此岗位已经删除！<a href='${pageContext.request.contextPath}/role_list.action'>返回</a>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return NONE;
		}

	}

	public String setPrivilege() {

		// 修改岗位中的权限
		Role role = roleServiceImpl.findById(model.getId());// 持久态的对象

		// 获取全部的权限
		List<Privilege> privileges = privilegeServiceImpl
				.findByIds(privilegeIds);

		role.setPrivileges(new HashSet<Privilege>(privileges));

		roleServiceImpl.update(role);
		return TOLIST;
	}

	// 测试代码，后期要删除
	public String setPrivilege2() {

		List<Privilege> all = privilegeServiceImpl.findAll();

		push(all);

		return "json";
	}

	// 测试代码后期要删除
	public String toTempUI() {

		Role role = roleServiceImpl.findById(model.getId());
		if (role != null) {
			// 说明用户存在
			push(role);
			// 准备权限数组，以便回显数据

			Set<Privilege> role_privileges = role.getPrivileges();

			if (role_privileges != null && role_privileges.size() > 0) {
				// 说明有权限数据
				privilegeIds = new Long[role_privileges.size()];

				int index = 0;
				for (Privilege privilege : role_privileges) {
					privilegeIds[index++] = privilege.getId();
				}
			}

			ServletActionContext.getRequest().setAttribute("privilegeIds",
					privilegeIds);
			// set("privilegeIds", privilegeIds);
		}
		return "toTempUI";
	}

}
