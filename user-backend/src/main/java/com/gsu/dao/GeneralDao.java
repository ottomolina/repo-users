package com.gsu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class GeneralDao {
	private QueryRunner qr;
	
	/**
	 * Obtiene un valor escalar de una consulta hacia una tabla en base de datos
	 * @param <T>
	 * @param conn : Connection
	 * @param sql : String
	 * @param param : Object[]
	 * @param type : Class<T>
	 * @return <T>
	 * @throws SQLException
	 */
	protected <T> T getOneColumnRow(Connection conn, String sql, Object[] param, Class<T> type) throws SQLException {
		qr = new QueryRunner();
		T ret;
		if(param == null) {
			ret = qr.query(conn, sql, new ScalarHandler<T>());
		} else {
			ret = qr.query(conn, sql, new ScalarHandler<T>(), param);
		}
		return ret;
	}
	
	/**
	 * Obtiene varias filas de una sola columna de una consulta hacia una tabla en base de datos
	 * @param <T>
	 * @param conn
	 * @param sql
	 * @param param
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	protected <T> List<T> getOneColumn(Connection conn, String sql, Object[] param, Class<T> type) throws SQLException {
		qr = new QueryRunner();
		List<T> ret;
		if(param == null) {
			ret = qr.query(conn, sql, new ColumnListHandler<T>());
		} else {
			ret = qr.query(conn, sql, new ColumnListHandler<T>(), param);
		}
		return ret;
	}
	
	protected <T> List<T> selectStatement(Connection conn, String sql, Object[] param, Class<T> type) throws SQLException {
		qr = new QueryRunner();
		List<T> ret;
		if(param == null) {
			ret = qr.query(conn, sql, new BeanListHandler<T>(type));
		} else {
			ret = qr.query(conn, sql, new BeanListHandler<T>(type), param);
		}
		return ret;
	}
	
	protected Integer insert(Connection conn, String sql, Object[] params) throws SQLException {
		return updateSQL(conn, sql, params);
	}
	
	protected Integer update(Connection conn, String sql, Object[] params) throws SQLException {
		return updateSQL(conn, sql, params);
	}
	
	protected Integer delete(Connection conn, String sql, Object[] params) throws SQLException {
		return updateSQL(conn, sql, params);
	}
	
	private Integer updateSQL(Connection conn, String sql, Object[] params) throws SQLException {
		qr = new QueryRunner();
		Integer ret = new Integer(0);
		if(params == null) {
			ret = qr.update(conn, sql);
		} else {
			ret = qr.update(conn, sql, params);
		}
		return ret;
	}
	
	public static void closeConnection(Connection conn) {
		DbUtils.closeQuietly(conn);
	}
	
}
