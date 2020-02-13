package com.gsu.orm;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GsuUserOrm {
	public static final String TABLENAME = "GSU_USER";
	private Integer gsu_usr_id;
	private String gsu_usr_nombre;
	private String gsu_usr_apellido;
	private String gsu_usr_login;
	private String gsu_usr_password;
	private Timestamp gsu_usr_date;
	private String gsu_usr_email;
	private String gsu_usr_estado;
}
