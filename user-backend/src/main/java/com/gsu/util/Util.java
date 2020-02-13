package com.gsu.util;

import com.gsu.ctrl.ServiceCtrl;
import com.gsu.data.RequestData;
import com.gsu.data.ResponseData;

public class Util {
	public static final String OK = "OK";
	public static final String ERROR = "ERROR";
	
	// Generar catálogo de error de sistema en properties o lo que sea
	public static final String ERROR_INSERT = "";
	public static final String ERROR_UPDATE = "";
	public static final String ERROR_SELECT = "";
	public static final String ERROR_DELETE = "";
	
//	public static List<Error> verificaRequest(RequestData requestData) {
//		List<Error> err = new ArrayList<Error>();
//		if(requestData.getToken() == null || "".equals(requestData.getToken())) {
//			err.add(new Error(101, "Debe enviar el token de autenticación de usuario."));
//		}
//		if(requestData.getData() == null) {
//			err.add(new Error(101, "Debe enviar el objeto 'data'."));
//		}
//		return err;
//	}
	
	public static boolean isRequestValid(ResponseData responseData, RequestData requestData, ServiceCtrl obj) {
		boolean ret = true;
		if(requestData.getToken() == null || "".equals(requestData.getToken())) {
			obj.setErrorToResponse(responseData, 101, "Debe enviar el token de autenticación de usuario.");
			ret = false;
		}
		if(requestData.getData() == null) {
			obj.setErrorToResponse(responseData, 101, "Debe enviar el objeto 'data'.");
			ret = false;
		}
		return ret;
	}
}
