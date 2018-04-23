package com.zrgj.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *	角色实体 
*/
public class Role {

	// 对象主键
	private Integer id;
	
	// 角色名称
	private String rname;
	
	// 角色说明
	private String rdesc;
	
	// 一个角色下面有多个权限
	private List<Privilege> privileges = new ArrayList<Privilege>();
	
	public Role() {

	}
	
	public Role(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRdesc() {
		return rdesc;
	}

	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
}
