package com.zrgj.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zrgj.bean.Privilege;
import com.zrgj.dao.PrivilegeDao;
import com.zrgj.exception.DBException;
import com.zrgj.utils.CollectionUtils;
import com.zrgj.utils.DataSouceUtils;

/**
 * 	权限Dao实现
*/
public class PrivilegeDaoImpl implements PrivilegeDao {

	private QueryRunner qr = new QueryRunner(DataSouceUtils.getDataSource());
	
	// 增加权限
	public void insert(Privilege privilege) {
		
		try {
			String sql = "insert into t_privilege(pname,pdesc,puri,piscommon,pmodel) values(?,?,?,?,?)";
			Object[] params = new Object[]{privilege.getPname(),privilege.getPdesc(),
										   privilege.getPuri(),privilege.isPiscommon(),privilege.getPmodel()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new DBException("插入权限异常", e);
		}
	}
	
	// 查询所有的权限
	@SuppressWarnings("unchecked")
	public List<Privilege> getPrivileges() {
		
		List<Privilege> privileges = null;
		try {
			String sql = "select id,pname,pdesc,puri,piscommon,pmodel from t_privilege";
			privileges =  qr.query(sql, new BeanListHandler<Privilege>(Privilege.class));
		} catch (Exception e) {
			throw new DBException("查询所有权限异常", e);
		}
		return (List<Privilege>) CollectionUtils.returnCollection(privileges);
	}

	// 根据角色id查询所有的权限
	@SuppressWarnings("unchecked")
	public List<Privilege> getPrivilegesByRoleId(int roleId) {
		
		List<Privilege> privileges = null;
		
		try {
			String sql = "select * from t_privilege p , t_role_privilege rp where rp.role_id = ? and rp.privilege_id = p.id";
			Object[] params = new Object[]{roleId};
			privileges = qr.query(sql, new BeanListHandler<Privilege>(Privilege.class),params);
		} catch (Exception e) {
			throw new DBException("根据角色id查询所有的权限", e);
		}
		return (List<Privilege>) CollectionUtils.returnCollection(privileges);
	}

	// 根据角色id删除此角色下面所有的权限
	public void deletePrivilegesByRoleId(int roleId) {
		
		try {
			String sql = "delete from t_role_privilege where role_id = ?";
			Object[] params = new Object[]{roleId};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new DBException("根据角色id删除此角色下面所有的权限", e);
		}
	}

	// 根据角色的id添加权限
	public void insertPrivilegesByRoleId(int roleId,List<Privilege> privileges) {
		
		try {
			
			if(privileges != null && privileges.size() > 0){
				String sql = "insert into t_role_privilege(role_id,privilege_id) values(?,?)";
				
				Object[][] params = new Object[privileges.size()][];
				
				for(int i = 0 ; i < privileges.size() ;i++){
					Privilege p = privileges.get(i);
					params[i] = new Object[]{roleId,p.getId()};
				}
				qr.batch(sql, params);
			}
		} catch (Exception e) {
			throw new DBException("根据角色的id添加权限", e);
		}
	}

	// 根据uri查询权限
	public Privilege getPrivilegeByURI(String model,String uri) {
		Privilege p = null;
		try {
			String sql = "select id,pname,pdesc,puri,piscommon,pmodel from t_privilege where puri = ? and pmodel=?";
			Object[] params = new Object[]{uri,model};
			p = qr.query(sql, new BeanHandler<Privilege>(Privilege.class),params);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return p;
	}

}
