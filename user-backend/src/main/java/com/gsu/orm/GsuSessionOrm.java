package com.gsu.orm;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GsuSessionOrm {
	public static final String TABLENAME = "GSU_SESSION";
	private Integer gsu_usr_id;
	private Integer gsu_ses_id;
	private Timestamp gsu_ses_date;
	private String gsu_ses_ip;
	private String gsu_ses_token;
	private Timestamp gsu_ses_end_date;
}
