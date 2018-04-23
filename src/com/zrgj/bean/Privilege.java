package com.zrgj.bean;

/**
 * 	权限实体
*/
public class Privilege {

	// 对象主键id
	private int id;
	
	// 权限名称
	private String pname;
	
	// 权限说明
	private String pdesc;
	
	// 权限的uri
	private String puri;
	
	// 此权限是否是公共权限(true就是公共权限表示此权限不需要授权，false表示此权限需要授权来访问)
	private boolean piscommon;
	
	// 权限所属的模块
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
