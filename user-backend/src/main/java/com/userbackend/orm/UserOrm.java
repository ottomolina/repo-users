package com.userbackend.orm;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserOrm {
	public static final String TABLENAME = "TBL_USER";
	private int userid;
	private String username;
	private String userlast_name;
	private String userlogin;
	private String userpassword;
	private Date usercreation;
	private String useremail;
}
