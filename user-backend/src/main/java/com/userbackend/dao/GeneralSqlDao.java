package com.userbackend.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneralSqlDao {
	@Autowired
	private QueryRunner qr;
	
	private int execute(Connection conn, String sql, Object[] params) throws SQLException {
		if(params != null) {
			return qr.update(conn, sql, params);
		} else {
			return qr.update(conn, sql);
		}
	}
	
	protected int insert(Connection conn, String sql, Object[] params) throws SQLException {
		return execute(conn, sql, params);
	}
	
	protected int update(Connection conn, String sql, Object[] params) throws SQLException {
		return execute(conn, sql, params);
	}
	
	protected int delete(Connection conn, String sql, Object[] params) throws SQLException {
		return execute(conn, sql, params);
	}
	
	protected <T> List<T> select(Connection conn, String sql, Object[] params, Class<T> type) throws SQLException {
		if(params == null) {
			return qr.query(conn, sql, new BeanListHandler<>(type));
		} else {
			return qr.query(conn, sql, new BeanListHandler<>(type), params);
		}
	}
	
	protected <T> T selectOneColumn(Connection conn, String sql, Class<T> type) throws SQLException {
		return qr.query(conn, sql, new BeanHandler<>(type));
	}
	
}