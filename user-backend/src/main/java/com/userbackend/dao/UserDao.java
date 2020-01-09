package com.userbackend.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.userbackend.orm.UserOrm;

public class UserDao extends GeneralSqlDao {
	
	public int insertUser(Connection conn, UserOrm user) throws SQLException {
		String sql = "insert into " + UserOrm.TABLENAME + "(";
		sql = sql + " userid,username,userlast_name,userlogin,userpassword,usercreation,useremail)";
		sql = sql + " values(?,?,?,?,?,?,?)";
		
		Object[] params = {user.getUserid(), user.getUsername(), user.getUserlast_name(), user.getUserlogin(),
				user.getUserpassword(), user.getUsercreation(), user.getUseremail() };
		return insert(conn, sql, params);
	}
	
	public List<UserOrm> getListUser(Connection conn, UserOrm filter) throws SQLException {
		String sql = "select userid,username,userlast_name,userlogin,userpassword,usercreation,useremail ";
		sql = sql + " from " + UserOrm.TABLENAME;
		
		if(filter != null) {
			
		}
		
		return select(conn, sql, null, UserOrm.class);
	}
	
}
