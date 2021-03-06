package com.gsu.ctrl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gsu.dao.UserDao;
import com.gsu.data.RequestData;
import com.gsu.data.ResponseData;
import com.gsu.orm.GsuUserOrm;
import com.gsu.util.Util;

public class UserServiceCtrl extends ServiceCtrl {
	private ResponseData responseData;
	@Autowired
	private UserDao dao;
	
	public ResponseData getListUser(RequestData input) {
		responseData = new ResponseData();
		GsuUserOrm orm = null;
		Connection conn = null;
		List<GsuUserOrm> listUser = new ArrayList<GsuUserOrm>();
		try {
			conn = getConnection();
			listUser = dao.select(conn, orm);
			responseData.setResult(Util.OK);
			responseData.setData(listUser);
		} catch(SQLException e) {
			e.printStackTrace();
			setSQLExceptionToResponse(responseData, e);
		} finally {
			closeConnection(conn);
		}
		return responseData;
	}
	
	
	
}
