package com.gsu.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GsuAddressOrm {
	public static final String TABLENAME = "GSU_ADDRESS";
	private Integer gsu_usr_id;
	private Integer gsu_adr_id;
	private String gsu_adr_address;
	private String gsu_adr_type;
	private Integer gsu_adr_main;
	
}
