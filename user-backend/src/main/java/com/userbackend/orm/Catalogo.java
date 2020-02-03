package com.userbackend.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Catalogo {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class TablaOrm {
		public static final String TABLENAME = "TBL_TABLA";
		private Integer tablaid;
		private String tablaname;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CatalogoOrm{
		public static final String TABLENAME = "TBL_CATALOGO";
		private Integer tablaid;
		private Integer catalogoid;
		private String codigo;
		private String valor;
	}
	
}
