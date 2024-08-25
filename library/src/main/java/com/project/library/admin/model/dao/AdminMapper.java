package com.project.library.admin.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.library.board.model.vo.Notice;
import com.project.library.member.model.vo.Application;

@Mapper
public interface AdminMapper {

	ArrayList<Application> selectApplication(RowBounds rowBounds);

	int getListCount();

	int getAppListCount(int i);

	ArrayList<Application> selectFilterApplication(int i, RowBounds rowBounds);

	Application selectAppDetail(int appNo);

	int deleteApp(int appNo);

	int getNoticeListCount();

	ArrayList<Notice> selectNotice(RowBounds rowBounds);

	ArrayList<Notice> selectNoticeList(String cate, RowBounds rowBounds);

	int noticeListCount(String cate);
	
	
}
