package com.gsu.ctrl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gsu.dao.SessionDao;
import com.gsu.dao.UserDao;
import com.gsu.data.RequestData;
import com.gsu.data.ResponseData;
import com.gsu.orm.GsuUserOrm;
import com.gsu.util.Security;
import com.gsu.util.Util;

public class UserServiceCtrl extends ServiceCtrl {
	private ResponseData responseData;
	@Autowired
	private UserDao userDao;
	@Autowired 
	private SessionDao sessionDao;
	
	public ResponseData getInfoUser(RequestData input) {
		responseData = new ResponseData();
		GsuUserOrm orm = null;
		Connection conn = null;
		List<GsuUserOrm> listUser = new ArrayList<GsuUserOrm>();
		try {
			conn = getConnection();
			listUser = userDao.select(conn, orm);
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
	
	public ResponseData saveUser(RequestData input) {
		responseData = new ResponseData();
		if(!Util.isRequestValid(responseData, input, this)) {
			return responseData;
		}
		GsuUserOrm orm = convertData(input.getData(), GsuUserOrm.class);
		
		String securePassword = Security.getSecurePassword(orm.getGsu_usr_password());
		orm.setGsu_usr_password(securePassword);
		
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			if(userDao.isDuplicate(conn, orm)) {
				setErrorToResponse(responseData, 101, "El registro que intenta guardar ya existe");
			} else {
				Integer v_gsuUserId = Integer.parseInt(
						String.valueOf(userDao.getNextId(conn)));
				orm.setGsu_usr_id(v_gsuUserId);
				Integer v_result = userDao.insert(conn, orm);
				if(v_result == 1) {
					responseData.setResult(Util.OK);
				} else {
					setErrorToResponse(responseData, 102, "Ocurrió un error al guardar el registro");
				}
				connectionCommit(conn);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			connectionRollback(conn);
			setSQLExceptionToResponse(responseData, e);
		} finally {
			closeConnection(conn);
		}
		return responseData;
	}
	
	public ResponseData updateUser(RequestData input) {
		responseData = new ResponseData();
		if(!Util.isRequestValid(responseData, input, this)) {
			return responseData;
		}
		GsuUserOrm orm = convertData(input.getData(), GsuUserOrm.class);
		
		if(orm.getGsu_usr_password() != null || !"".equals(orm.getGsu_usr_password())) {
			String securePassword = Security.getSecurePassword(orm.getGsu_usr_password());
			orm.setGsu_usr_password(securePassword);
		}
		
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			if(userDao.isDuplicate(conn, orm)) {
				setErrorToResponse(responseData, 101, "El registro que intenta guardar ya existe");
			} else {
				Integer v_result = userDao.update(conn, orm);
				if(v_result == 1) {
					responseData.setResult(Util.OK);
				} else {
					setErrorToResponse(responseData, 102, "Ocurrió un error al guardar el registro");
				}
				connectionCommit(conn);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			connectionRollback(conn);
			setSQLExceptionToResponse(responseData, e);
		} finally {
			closeConnection(conn);
		}
		return responseData;
	}
	
	public ResponseData deleteUser(RequestData input) {
		responseData = new ResponseData();
		if(!Util.isRequestValid(responseData, input, this)) {
			return responseData;
		}
		GsuUserOrm orm = convertData(input.getData(), GsuUserOrm.class);
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			if(userDao.validIntegrity(conn, orm) > 0) {
				setErrorToResponse(responseData, 101, "El registro no puede ser eliminado, existen referencias.");
			} else {
				Integer v_result = userDao.delete(conn, orm);
				if(v_result == 1) {
					responseData.setResult(Util.OK);
				} else {
					setErrorToResponse(responseData, 102, "Ocurrió un error al guardar el registro");
				}
				connectionCommit(conn);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			connectionRollback(conn);
			setSQLExceptionToResponse(responseData, e);
		} finally {
			closeConnection(conn);
		}
		return responseData;
	}
	
	public ResponseData getLogin(RequestData input) {
		responseData = new ResponseData();
		if(!Util.isRequestValid(responseData, input, this)) {
			return responseData;
		}
		GsuUserOrm orm = convertData(input.getData(), GsuUserOrm.class);
		Connection conn = null;
		try {
			conn = getConnection();
			List<GsuUserOrm> list = userDao.select(conn, orm);
			
			if(list.size() == 0) {
				setErrorToResponse(responseData, 203, "Credenciales inválidas");
			} else {
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			setSQLExceptionToResponse(responseData, e);
		} finally {
			closeConnection(conn);
		}
		return responseData;
	}
	
}
