package com.gsu.interfaces;

import com.gsu.data.*;

public interface IService {
	
	public ResponseData save(RequestData request);
	
	public ResponseData getInfo(RequestData request);
	
	public ResponseData update(RequestData request);
	
	public ResponseData delete(RequestData request);	
	
}
