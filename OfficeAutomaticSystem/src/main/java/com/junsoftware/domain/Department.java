package com.junsoftware.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private Long id;
	private Department parent;
	private String name;
	private String description;
	private Set children = new HashSet(0);
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	public Department(Long id, Department parent, String name,
			String description, Set children, Set users) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
		this.description = description;
		this.children = children;
		this.users = users;
	}

	/** minimal constructor */
	public Department(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}

	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	// Property accessors

}