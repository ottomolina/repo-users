package com.userbackend.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.userbackend.orm.Catalogo.TablaOrm;

public class TablaDao extends GeneralSqlDao {
	
	public Integer nextId(Connection conn) throws SQLException {
		String sql = "select nvl(max(userid), 0) + 1 from " + TablaOrm.TABLENAME;
		return selectOneColumn(conn, sql, Integer.class);
	}
	
	public Integer validIntegrity(Connection conn, TablaOrm orm) throws SQLException {
		String sql = "select count(1) from " + TablaOrm.TABLENAME;
		sql = sql + " where upper(tablaname) like upper(" + orm.getTablaname() + ") ";
		
		if(orm.getTablaid() != null) {
			// Esta es validacion para update
			sql = sql + "and tablaid != " + orm.getTablaid();
		}
		return selectOneColumn(conn, sql, Integer.class);
	}
	
	public Integer insertTabla(Connection conn, TablaOrm tabla) throws SQLException {
		String sql = "insert into " + TablaOrm.TABLENAME;
		sql = sql + "(tablaid,tablaname)";
		sql = sql + " values(?,?)";
		
		Object[] params = { tabla.getTablaid(), tabla.getTablaname() };
		return insert(conn, sql, params);
	}
	
	public List<TablaOrm> getListTabla(Connection conn, TablaOrm filter) throws SQLException {
		String sql = "select tablaid, tablaname";
		sql = sql + " from " + TablaOrm.TABLENAME;
		
		Object[] params = null;
		if(filter != null) {
			List<Object> p = new ArrayList<Object>();
			String where = "";
			if(filter.getTablaid() != null) {
				where = where + " tablaid=? and";
				p.add(filter.getTablaid());
			}
			if(filter.getTablaname() != null) {
				where = where + " tablaname=? and";
				p.add(filter.getTablaname());
			}
			if(!"".equals(where)) {
				sql = sql + " where " + where.substring(0, where.length()-4);
				params = p.toArray();
			}
		}
		System.out.println("TablaDao.getListTabla(): " + sql);
		return select(conn, sql, params, TablaOrm.class);
	}
	
	public Integer updateTabla(Connection conn, TablaOrm tabla) throws SQLException {
		String sql = "update " + TablaOrm.TABLENAME;
		sql = sql + " set tablaname=? ";
		sql = sql + " where tablaid = ? ";
		
		Object[] params = { tabla.getTablaname(), tabla.getTablaid() };
		return update(conn, sql, params);
	}
	
}
