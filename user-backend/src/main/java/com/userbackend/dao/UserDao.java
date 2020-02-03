package com.userbackend.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.userbackend.orm.UserOrm;
import com.userbackend.util.Util;

public class UserDao extends GeneralSqlDao {
	
	public Integer nextId(Connection conn) throws SQLException {
		String sql = "select nvl(max(userid), 0) + 1 from " + UserOrm.TABLENAME;
		return selectOneColumn(conn, sql, Integer.class);
	}
	
	public int insertUser(Connection conn, UserOrm user) throws SQLException {
		String sql = "insert into " + UserOrm.TABLENAME + "(";
		sql = sql + " userid,username,userlast_name,userlogin,userpassword,usercreation,useremail,userestado)";
		sql = sql + " values(?,?,?,?,?,?,?,?)";
		
		Object[] params = {user.getUserid(), user.getUsername(), user.getUserlast_name(), user.getUserlogin(),
				user.getUserpassword(), user.getUsercreation(), user.getUseremail(), user.getUserestado() };
		return insert(conn, sql, params);
	}
	
	public List<UserOrm> getListUser(Connection conn, UserOrm filter) throws SQLException {
		String sql = "select userid,username,userlast_name,userlogin,userpassword,usercreation,useremail,userestado ";
		sql = sql + " from " + UserOrm.TABLENAME;
		
		Object[] params = null;
		if(filter != null) {
			List<Object> p = new ArrayList<Object>();
			String where = "";
			if(filter.getUserid() != null) {
				where = where + " userid=? and";
				p.add(filter.getUserid());
			}
			if(filter.getUsername() != null) {
				where = where + " username=? and";
				p.add(filter.getUsername());
			}
			if(filter.getUserlast_name() != null) {
				where = where + " userlast_name=? and";
				p.add(filter.getUserlast_name());
			}
			if(filter.getUserlogin() != null) {
				where = where + " userlogin=? and";
				p.add(filter.getUserlogin());
			}
			if(filter.getUserpassword() != null) {
				where = where + " userpassword=? and";
				p.add(filter.getUserpassword());
			}
			if(filter.getUseremail() != null) {
				where = where + " useremail=? and";
				p.add(filter.getUseremail());
			}
			if(filter.getUserestado() != null) {
				where = where + " usereestado=?    ";
				p.add(filter.getUserestado());
			}
			if(!"".equals(where)) {
				sql = sql + " where " + where.substring(0, where.length()-4);
				params = p.toArray();
			}
		}
		System.out.println("UserDao.getListUser(): " + sql);
		return select(conn, sql, params, UserOrm.class);
	}
	
	public int updateUser(Connection conn, UserOrm user) throws SQLException {
		String sql = "update " + UserOrm.TABLENAME;
		String set = "";
		List<Object> params = new ArrayList<Object>();
		if(user.getUsername() != null) {
			set = set + " username=?,";
			params.add(user.getUsername());
		}
		if(user.getUserlast_name() != null) {
			set = set + " userlast_name=?,";
			params.add(user.getUserlast_name());
		}
		if(user.getUserlogin() != null) {
			set = set + " userlogin=?,";
			params.add(user.getUserlogin());
		}
		if(user.getUserpassword() != null) {
			set = set + " userpassword=?,";
			params.add(user.getUserpassword());
		}
		if(user.getUseremail() != null) {
			set = set + " useremail=?,";
			params.add(user.getUseremail());
		}
		if(user.getUserestado() != null) {
			set = set + " userestado=?,";
			params.add(user.getUserestado());
		}
		sql = sql + " set" + set.substring(0, set.length()-2);
		sql = sql + " where userid=? ";
		params.add(user.getUserid());
		return update(conn, sql, params.toArray());
	}
	
	public int deleteUser(Connection conn, UserOrm user) throws SQLException {
		String sql = "update " + UserOrm.TABLENAME;
		sql = sql + " set userestado = ( select codigo " + 
										"  from tbl_catalogo " + 
										" where tablaid = (select tablaid " + 
										"                    from tbl_tabla " + 
										"                   where tablaname like '" + Util.CAT_ESTADO_USER + "') " + 
										"   and upper(valor) like 'ELIMINADO') ";
		sql = sql + " where userid=?";
		return update(conn, sql, new Object[] {user.getUserid()});
	}
	
}
