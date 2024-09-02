package com.project.library.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.library.admin.model.vo.Employee;
import com.project.library.admin.model.vo.Greeting;
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

	int insertNotice(HashMap<String, Object> map);

	Notice noticeDetail(int noticeNo);

	int updateNotice(HashMap<Object, Object> map);

	ArrayList<Employee> selectEmployee();

	ArrayList<Employee> selectDept(int deptNo);

	int updateDept(HashMap<Object, Object> map);

	int insertEmp(HashMap<String, Object> map);

	int deleteEmp(String empNo);

	Greeting selectGreeting();

	int updateGreeting(String content);

	ArrayList<Member> selectMem(String grade, PageInfo pi);

	int getMemCount(String grade);

	int updateActive(HashMap<String, Object> map);

	int changeGrade(HashMap<String, Object> map);

	int searchMemCount(HashMap<String, Object> map);

	ArrayList<Member> searchMem(HashMap<String, Object> map);

	int deleteNotice(String noticeNo);

	// 회원관리 Y 정렬
	int getActiveYCount(HashMap<String, Object> map);

	// 회원관리 N 정렬
	int getActiveNCount(HashMap<String, Object> map);

	ArrayList<Member> activeY(HashMap<String, Object> map, PageInfo pi);

	ArrayList<Member> activeN(HashMap<String, Object> map, PageInfo pi);

	int checkDeleteNotice(String noticeNo);



}
