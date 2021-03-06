package com.gsu.ctrl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.gsu.dao.GeneralDao;
import com.gsu.data.ResponseData;
import com.gsu.data.ResponseData.Error;
import com.gsu.util.Util;

public class ServiceCtrl {
	@Autowired
	private DataSource dataSource;
	
	protected Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = this.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Ocurrió un error al obtener la conexion a base de datos");
		}
		return conn;
	}
	
	protected void closeConnection(Connection conn) {
		GeneralDao.closeConnection(conn);
	}
	
	protected void connectionCommit(Connection conn) throws SQLException {
		if(!conn.getAutoCommit()) {
			conn.commit();
		}
	}
	
	protected void connectionRollback(Connection conn) {
		try {
			if(!conn.getAutoCommit()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected <T> T convertData(Object data, Class<T> type) {
		return new Gson().fromJson(String.valueOf(data), type);
	}
	
	public void setSQLExceptionToResponse(ResponseData data, SQLException e) {
		List<Error> err = new ArrayList<Error>();
		err.add(new Error(e.getErrorCode(), e.getMessage()));
		data.setListError(err);
		data.setResult("ERROR");
	}
	
	public void setErrorToResponse(ResponseData data, Integer code, String error) {
		if(data.getListError() == null) {
			data.setListError(new ArrayList<Error>());
		}
		data.getListError().add(new Error(code, error));
		data.setResult(Util.ERROR);
	}
}
