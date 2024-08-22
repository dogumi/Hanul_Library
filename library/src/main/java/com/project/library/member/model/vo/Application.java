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
public class Application {
	private int appNo;
	private int appWriterNo;
	private int appCateNo;
	private String appTitle;
	private String appContent;
	private Date appCreateDate;
	private Date appModifyDate;
}
