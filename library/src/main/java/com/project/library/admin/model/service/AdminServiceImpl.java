package com.project.library.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.admin.model.dao.AdminMapper;
import com.project.library.admin.model.vo.Employee;
import com.project.library.admin.model.vo.Greeting;
import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;
import com.project.library.member.model.vo.Application;
import com.project.library.member.model.vo.Member;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper aMapper;

	@Override
	public ArrayList<Application> selectApplication(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return aMapper.selectApplication(rowBounds);
	}

	@Override
	public int getListCount() {
		return aMapper.getListCount();
	}

	@Override
	public int getAppListCount(int i) {
		return aMapper.getAppListCount(i);
	}

	@Override
	public ArrayList<Application> selectFilterApplication(int i, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return aMapper.selectFilterApplication(i, rowBounds);
	}

	@Override
	public Application selectAppDetail(int appNo) {
		return aMapper.selectAppDetail(appNo);
	}

	@Override
	public int deleteApp(int appNo) {
		return aMapper.deleteApp(appNo);
	}

	@Override
	public int getNoticeListCount() {
		return aMapper.getNoticeListCount();
	}

	@Override
	public ArrayList<Notice> selectNotice(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return aMapper.selectNotice(rowBounds);
	}

	@Override
	public int noticeListCount(String cate) {
		return aMapper.noticeListCount(cate);
	}

	@Override
	public ArrayList<Notice> selectNoticeList(String cate, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return aMapper.selectNoticeList(cate, rowBounds);
	}

	@Override
	public int insertNotice(HashMap<String, Object> map) {
		return aMapper.insertNotice(map);
	}

	@Override
	public Notice noticeDetail(int noticeNo) {
		return aMapper.noticeDetail(noticeNo);
	}

	@Override
	public int updateNotice(HashMap<Object, Object> map) {
		return aMapper.updateNotice(map);
	}

	@Override
	public ArrayList<Employee> selectEmployee() {
		return aMapper.selectEmployee();
	}

	@Override
	public ArrayList<Employee> selectDept(int deptNo) {
		return aMapper.selectDept(deptNo);
	}

	@Override
	public int updateDept(HashMap<Object, Object> map) {
		return aMapper.updateDept(map);
	}

	@Override
	public int insertEmp(HashMap<String, Object> map) {
		return aMapper.insertEmp(map);
	}

	@Override
	public int deleteEmp(String empNo) {
		return aMapper.deleteEmp(empNo);
	}

	@Override
	public Greeting selectGreeting() {
		return aMapper.selectGreeting();
	}

	@Override
	public int updateGreeting(String content) {
		return aMapper.updateGreeting(content);
	}

	@Override
	public ArrayList<Member> selectMem(String grade, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return aMapper.selectMem(grade, rowBounds);
	}

	@Override
	public int getMemCount(String grade) {
		return aMapper.getMemCount(grade);
	}

	@Override
	public int updateActive(HashMap<String, Object> map) {
		return aMapper.updateActive(map);
	}

	@Override
	public int changeGrade(HashMap<String, Object> map) {
		return aMapper.changeGrade(map);
	}

	@Override
	public int searchMemCount(HashMap<String, Object> map) {
		return aMapper.searchMemCount(map);
	}

	@Override
	public ArrayList<Member> searchMem(HashMap<String, Object> map) {
		return aMapper.searchMem(map);
	}



	
	
	
}
