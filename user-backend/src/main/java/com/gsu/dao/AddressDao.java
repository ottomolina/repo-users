package com.gsu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gsu.orm.GsuAddressOrm;

public class AddressDao extends GeneralDao {
	
	public Long getNextId(Connection conn) throws SQLException {
		String sql = "select ifnull(max(GSU_ADR_ID), 0) + 1 ";
		sql = sql + " from " + GsuAddressOrm.TABLENAME;
		return getOneColumnRow(conn, sql, null, Long.class);
	}
	
	public boolean isDuplicate(Connection conn, GsuAddressOrm orm) throws SQLException {
		/**
		 * De momento no existe razon para realizar esta verificacion, queda
		 * codificada la estructura del metodo por si mas adelante es necesaria
		 */
		return false;
	}
	
	public Integer validIntegrity(Connection conn, GsuAddressOrm orm) throws SQLException {
		/**
		 * De momento no existe razon para realizar esta verificacion, queda
		 * codificada la estructura del metodo por si mas adelante es necesaria
		 */
		return 0;
	}
	
	public Integer insert(Connection conn, GsuAddressOrm orm) throws SQLException {
		String sql = "insert into " + GsuAddressOrm.TABLENAME + "(";
		sql = sql + "GSU_USR_ID, GSU_ADR_ID, GSU_ADR_ADDRESS, ";
		sql = sql + "GSU_ADR_TYPE, GSU_ADR_MAIN) ";
		sql = sql + "values(?, ?, ?, ?, ?)";
		Object[] params = {orm.getGsu_usr_id(), orm.getGsu_adr_id(), orm.getGsu_adr_address(), 
				orm.getGsu_adr_type(), orm.getGsu_adr_main()};
		return insert(conn, sql, params);
	}
	
	public List<GsuAddressOrm> select(Connection conn, GsuAddressOrm filter) throws SQLException {
		String sql = "select ";
		sql = sql + "GSU_USR_ID, GSU_ADR_ID, GSU_ADR_ADDRESS, GSU_ADR_TYPE, GSU_ADR_MAIN ";
		sql = sql + "from " + GsuAddressOrm.TABLENAME;
		
		Object[] params = null;
		if(filter != null) {
			List<Object> p = new ArrayList<Object>();
			String where = "";
			if(filter.getGsu_usr_id() != null) {
				where = where + " GSU_USR_ID=? and";
				p.add(filter.getGsu_usr_id());
			}
			if(filter.getGsu_adr_id() != null) {
				where = where + " GSU_ADR_ID=? and";
				p.add(filter.getGsu_adr_id());
			}
			if(filter.getGsu_adr_address() != null || !"".equals(filter.getGsu_adr_address())) {
				where = where + " GSU_ADR_ADDRESS=? and";
				p.add(filter.getGsu_adr_address());
			}
			if(filter.getGsu_adr_type() != null || !"".equals(filter.getGsu_adr_type())) {
				where = where + " GSU_ADR_TYPE=? and";
				p.add(filter.getGsu_adr_type());
			}
			if(filter.getGsu_adr_main() != null) {
				where = where + " GSU_ADR_MAIN=? and";
				p.add(filter.getGsu_adr_main());
			}
			if(!"".equals(where)) {
				sql = sql + " where " + where.substring(0, where.length()-4);
				params = p.toArray();
			}
		}
		return selectStatement(conn, sql, params, GsuAddressOrm.class);
	}
	
	public Integer update(Connection conn, GsuAddressOrm orm) throws SQLException {
		String sql = "update " + GsuAddressOrm.TABLENAME;
		String set = "";
		List<Object> params = new ArrayList<Object>();
		if(orm.getGsu_adr_address() != null) {
			set = set + " GSU_ADR_ADDRESS=?,";
			params.add(orm.getGsu_adr_address());
		}
		if(orm.getGsu_adr_type() != null) {
			set = set + " GSU_ADR_TYPE=?,";
			params.add(orm.getGsu_adr_type());
		}
		if(orm.getGsu_adr_main() != null) {
			set = set + " GSU_ADR_MAIN=?,";
			params.add(orm.getGsu_adr_main());
		}
		sql = sql + " set" + set.substring(0, set.length()-2);
		sql = sql + " where GSU_USR_ID=?";
		sql = sql + " and GSU_ADR_ID=?";
		params.add(orm.getGsu_usr_id());
		params.add(orm.getGsu_adr_id());
		return update(conn, sql, params.toArray());
	}
	
	public Integer delete(Connection conn, GsuAddressOrm orm) throws SQLException {
		String sql = "delete from " + GsuAddressOrm.TABLENAME;
		sql = sql + " where GSU_USR_ID=?";
		sql = sql + " and GSU_ADR_ID=?";
		Object[] params = { orm.getGsu_usr_id(), orm.getGsu_adr_id() };
		return delete(conn, sql, params);
	}
	
}
