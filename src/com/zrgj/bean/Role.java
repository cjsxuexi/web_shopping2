package com.zrgj.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *	��ɫʵ�� 
*/
public class Role {

	// ��������
	private Integer id;
	
	// ��ɫ����
	private String rname;
	
	// ��ɫ˵��
	private String rdesc;
	
	// һ����ɫ�����ж��Ȩ��
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
