package com.gsu.data;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ResponseData {
	private Object data;
	private String result;
	private List<Error> listError;
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Error {
		private Integer code;
		private String desc;
	}
	
}
