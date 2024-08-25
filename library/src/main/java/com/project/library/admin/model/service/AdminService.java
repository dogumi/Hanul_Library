package com.project.library.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;
import com.project.library.member.model.vo.Application;
import com.project.library.member.model.vo.Member;

public interface AdminService {

	ArrayList<Application> selectApplication(PageInfo pi);

	int getListCount();

	int getAppListCount(int i);

	ArrayList<Application> selectFilterApplication(int i, PageInfo pi);

	Application selectAppDetail(int appNo);

	int deleteApp(int appNo);

	int getNoticeListCount();

	ArrayList<Notice> selectNotice(PageInfo pi);

	int noticeListCount(String cate);

	ArrayList<Notice> selectNoticeList(String cate, PageInfo pi);

}
