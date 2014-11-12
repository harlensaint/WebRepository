package com.junsoftware.domain;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	// Fields

	private Long id;
	private Privilege parent;
	private String name;
	private String url;
	private Set children = new HashSet(0);
	private Set roles = new HashSet(0);
	
	
	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** full constructor */
	public Privilege(Privilege parent, String name, String url, Set children,
			Set roles) {
		this.parent = parent;
		this.name = name;
		this.url = url;
		this.children = children;
		this.roles = roles;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Privilege getParent() {
		return this.parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getRoles() {
		return this.roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
	public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}
	
	//提过一个pId的方法
	public Long getpId(){
		if(parent!=null){
			return parent.getId();
		}else{
			return 0L;
		}
	}
}