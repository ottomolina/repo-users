package com.gsu.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.gsu.orm.GsuSessionOrm;

public class SessionDao extends GeneralDao {
	
	public Long getNextId(Connection conn, GsuSessionOrm orm) throws SQLException {
		String sql = "select ifnull(max(GSU_SES_ID), 0) + 1 ";
		sql = sql + " from " + GsuSessionOrm.TABLENAME;
		return getOneColumnRow(conn, sql, null, Long.class);
	}
	
	public Integer insert(Connection conn, GsuSessionOrm orm) throws SQLException {
		String sql = "insert into " + GsuSessionOrm.TABLENAME + "(";
		sql = sql + "GSU_USR_ID, GSU_SES_ID, GSU_SES_DATE, ";
		sql = sql + "GSU_SES_IP, GSU_SES_TOKEN, GSU_SES_END_DATE) ";
		sql = sql + "values(?, ?, ?, ?, ?, ?)";
		Object[] params = { orm.getGsu_usr_id(), orm.getGsu_ses_id(), orm.getGsu_ses_date(),
				orm.getGsu_ses_ip(), orm.getGsu_ses_token(), orm.getGsu_ses_end_date() };
		return insert(conn, sql, params);
	}
	
}
