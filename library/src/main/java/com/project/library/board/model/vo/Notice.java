package com.project.library.board.model.vo;

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
public class Notice {
	private int noticeNo;
	private int noticeWriterNo;
	private String noticeTitle;
	private String noticeCategory;
	private String noticeContent;
	private String noticeStatus;
	private int noticeCount;
	private Date noticeCreateDate;
	private Date noticeModifyDate;
	
}
