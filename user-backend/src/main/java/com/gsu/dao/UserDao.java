package com.gsu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gsu.orm.GsuAddressOrm;
import com.gsu.orm.GsuUserOrm;

public class UserDao extends GeneralDao {
	
	public Long getNextId(Connection conn) throws SQLException {
		String sql = "select ifnull(max(userid), 0) + 1 ";
		sql = sql + " from " + GsuUserOrm.TABLENAME;
		return getOneColumnRow(conn, sql, null, Long.class);
	}
	
	public boolean isDuplicate(Connection conn, GsuUserOrm orm) throws SQLException {
		String sql = "select count(1) from " + GsuAddressOrm.TABLENAME;
		sql = sql + " where upper(gsu_usr_nombre) = upper(?) ";
		
		Object[] param = null;
		if(orm.getGsu_usr_id() != null) {
			sql = sql + " and gsu_usr_id != ?";
			param = new Object[] { orm.getGsu_usr_nombre(), orm.getGsu_usr_id() };
		} else {
			param = new Object[] { orm.getGsu_usr_nombre() };
		}
		if(getOneColumnRow(conn, sql, param, Integer.class) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Integer validIntegrity(Connection conn, GsuUserOrm orm) throws SQLException {
		String sql = "select count(1) from " + GsuAddressOrm.TABLENAME;
		sql = sql + " where gsu_usr_id = ? ";
		Object[] param = { orm.getGsu_usr_id() };
		return getOneColumnRow(conn, sql, param, Integer.class);
	}
	
	public int insert(Connection conn, GsuUserOrm user) throws SQLException {
		String sql = "insert into " + GsuUserOrm.TABLENAME + "(";
		sql = sql + " GSU_USR_ID, GSU_USR_NOMBRE, GSU_USR_APELLIDO, GSU_USR_LOGIN,";
		sql = sql + " GSU_USR_PASSWORD, GSU_USR_DATE, GSU_USR_EMAIL, GSU_USR_ESTADO)";
		sql = sql + " values(?,?,?,?,?,?,?,?)";
		
		Object[] params = {user.getGsu_usr_id(), user.getGsu_usr_nombre(), user.getGsu_usr_apellido(), user.getGsu_usr_login(),
				user.getGsu_usr_password(), user.getGsu_usr_date(), user.getGsu_usr_email(), user.getGsu_usr_estado() };
		return insert(conn, sql, params);
	}
	
	public List<GsuUserOrm> select(Connection conn, GsuUserOrm filter) throws SQLException {
		String sql = "select ";
		sql = sql + " GSU_USR_ID, GSU_USR_NOMBRE, GSU_USR_APELLIDO, GSU_USR_LOGIN,";
		sql = sql + " GSU_USR_PASSWORD, GSU_USR_DATE, GSU_USR_EMAIL, GSU_USR_ESTADO";
		sql = sql + " from " + GsuUserOrm.TABLENAME;
		
		Object[] params = null;
		if(filter != null) {
			List<Object> p = new ArrayList<Object>();
			String where = "";
			if(filter.getGsu_usr_id() != null) {
				where = where + " GSU_USR_ID=? and";
				p.add(filter.getGsu_usr_id());
			}
			if(filter.getGsu_usr_nombre() != null || !"".equals(filter.getGsu_usr_nombre())) {
				where = where + " GSU_USR_NOMBRE=? and";
				p.add(filter.getGsu_usr_nombre());
			}
			if(filter.getGsu_usr_apellido() != null || !"".equals(filter.getGsu_usr_apellido())) {
				where = where + " GSU_USR_APELLIDO=? and";
				p.add(filter.getGsu_usr_apellido());
			}
			if(filter.getGsu_usr_login() != null || !"".equals(filter.getGsu_usr_login())) {
				where = where + " GSU_USR_LOGIN=? and";
				p.add(filter.getGsu_usr_login());
			}
			if(filter.getGsu_usr_password() != null || !"".equals(filter.getGsu_usr_password())) {
				where = where + " GSU_USR_PASSWORD=? and";
				p.add(filter.getGsu_usr_password());
			}
			if(filter.getGsu_usr_email() != null || !"".equals(filter.getGsu_usr_email())) {
				where = where + " GSU_USR_EMAIL=? and";
				p.add(filter.getGsu_usr_email());
			}
			if(filter.getGsu_usr_estado() != null || !"".equals(filter.getGsu_usr_estado())) {
				where = where + " GSU_USR_ESTADO=?    ";
				p.add(filter.getGsu_usr_estado());
			}
			if(!"".equals(where)) {
				sql = sql + " where " + where.substring(0, where.length()-4);
				params = p.toArray();
			}
		}
		System.out.println("GsuUserDao.select(): " + sql);
		return selectStatement(conn, sql, params, GsuUserOrm.class);
	}
	
	public int update(Connection conn, GsuUserOrm user) throws SQLException {
		String sql = "update " + GsuUserOrm.TABLENAME;
		String set = "";
		List<Object> params = new ArrayList<Object>();
		if(user.getGsu_usr_nombre() != null) {
			set = set + " GSU_USR_NOMBRE=?,";
			params.add(user.getGsu_usr_nombre());
		}
		if(user.getGsu_usr_apellido() != null) {
			set = set + " GSU_USR_APELLIDO=?,";
			params.add(user.getGsu_usr_apellido());
		}
		if(user.getGsu_usr_login() != null) {
			set = set + " GSU_USR_LOGIN=?,";
			params.add(user.getGsu_usr_login());
		}
		if(user.getGsu_usr_password() != null) {
			set = set + " GSU_USR_PASSWORD=?,";
			params.add(user.getGsu_usr_password());
		}
		if(user.getGsu_usr_email() != null) {
			set = set + " GSU_USR_EMAIL=?,";
			params.add(user.getGsu_usr_email());
		}
		if(user.getGsu_usr_estado() != null) {
			set = set + " GSU_USR_ESTADO=?,";
			params.add(user.getGsu_usr_estado());
		}
		sql = sql + " set" + set.substring(0, set.length()-2);
		sql = sql + " where GSU_USR_ID=? ";
		params.add(user.getGsu_usr_id());
		return update(conn, sql, params.toArray());
	}
	
	public int delete(Connection conn, GsuUserOrm user) throws SQLException {
		String sql = "delete from " + GsuUserOrm.TABLENAME;
		sql = sql + " where GSU_USR_ID=?";
		Object[] params = {user.getGsu_usr_id()};
		return delete(conn, sql, params);
	}
	
}
