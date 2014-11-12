package com.junsoftware.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	public static final String INITPWD = "1234";

	private Long id;
	private Department department;
	private String name;
	private String loginName;
	private String email;
	private Integer sex;
	private String telephone;
	private String description;
	private String password;
	private Set<Role> roles = new HashSet<Role>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/** full constructor */
	public User(Long id, Department department, String name, String loginName,
			String email, Integer sex, String telephone, String description,
			String password, Set<Role> roles) {
		this.id = id;
		this.department = department;
		this.name = name;
		this.loginName = loginName;
		this.email = email;
		this.sex = sex;
		this.telephone = telephone;
		this.description = description;
		this.password = password;
		this.roles = roles;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	// 定义一个方法，用来检测此用户是否有指定的权限
	public Boolean checkPrivileges(String priName) {
		
		if("admin".equals(loginName)){
			return true;
		}
		
		if (roles != null && roles.size() > 0) {
			// 遍历用户的每一个角色
			for (Role role : roles) {
				Set<Privilege> privileges = role.getPrivileges();

				if (privileges != null && privileges.size() > 0) {
					// 遍历此角色的所有的权限
					for (Privilege privilege : privileges) {
						if (priName.equals(privilege.getName())) {
							return true;
						}
					}

				}
			}
		}
		return false;
	}

	public Boolean checkPrivlegesUrl(String url) {
		
		if("admin".equals(loginName)){
			return true;
		}
		
		if (roles != null && roles.size() > 0) {
			// 遍历用户的每一个角色
			for (Role role : roles) {
				Set<Privilege> privileges = role.getPrivileges();

				if (privileges != null && privileges.size() > 0) {
					// 遍历此角色的所有的权限
					for (Privilege privilege : privileges) {
						if (url.equals(privilege.getUrl())) {
							return true;
						}
					}

				}
			}
		}
		return false;
	}
}