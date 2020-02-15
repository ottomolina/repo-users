package com.gsu.service;

import javax.ws.rs.Consumes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gsu.data.RequestData;
import com.gsu.data.ResponseData;
import com.gsu.interfaces.IService;

@RestController
@RequestMapping(value = "/gsu-backend/rest/api")
@Consumes("application/json")
public class UserServiceImpl implements IService {
	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	@RequestMapping(value = "/pruebalog", method = RequestMethod.GET)
	public ResponseData pruebaLog4j() {
		ResponseData data = new ResponseData();
		data.setData("Esto es una prueba");
		data.setResult("OK");
		data.setListError(null);
		
		if(log.isDebugEnabled()) {
			log.debug("Está debuggeando, por lo tanto me muestro");
		}
		log.error("This is an error message");
		log.warn("This is a warning message");
		log.fatal("This is a fatal message");
		log.info("This is an info message");
		return data;
	}
	
	@Override
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseData save(RequestData request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/getinfo", method = RequestMethod.POST)
	public ResponseData getInfo(RequestData request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseData update(RequestData request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseData delete(RequestData request) {
		// TODO Auto-generated method stub
		return null;
	}

}
