package com.zrgj.bean;

/**
 * 	Ȩ��ʵ��
*/
public class Privilege {

	// ��������id
	private int id;
	
	// Ȩ������
	private String pname;
	
	// Ȩ��˵��
	private String pdesc;
	
	// Ȩ�޵�uri
	private String puri;
	
	// ��Ȩ���Ƿ��ǹ���Ȩ��(true���ǹ���Ȩ�ޱ�ʾ��Ȩ�޲���Ҫ��Ȩ��false��ʾ��Ȩ����Ҫ��Ȩ������)
	private boolean piscommon;
	
	// Ȩ��������ģ��
	private String pmodel;
	
	public Privilege() {
		
	}
	
	public Privilege(String uri,String model) {
		this.puri = uri;
		this.pmodel = model;
	}

	public Privilege(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public String getPuri() {
		return puri;
	}

	public void setPuri(String puri) {
		this.puri = puri;
	}

	public boolean isPiscommon() {
		return piscommon;
	}

	public void setPiscommon(boolean piscommon) {
		this.piscommon = piscommon;
	}

	public String getPmodel() {
		return pmodel;
	}

	public void setPmodel(String pmodel) {
		this.pmodel = pmodel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pmodel == null) ? 0 : pmodel.hashCode());
		result = prime * result + ((puri == null) ? 0 : puri.hashCode());
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
		Privilege other = (Privilege) obj;
		if (pmodel == null) {
			if (other.pmodel != null)
				return false;
		} else if (!pmodel.equals(other.pmodel))
			return false;
		if (puri == null) {
			if (other.puri != null)
				return false;
		} else if (!puri.equals(other.puri))
			return false;
		return true;
	}
}
