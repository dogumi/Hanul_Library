package com.project.library.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	private int memNo;
	private String memName;
	private String memId;
	private String memPwd;
	private String memPhone;
	private String memEmail;
	private String memActive;
	private String memGrade;
	private Date memEnrollDate;
}
