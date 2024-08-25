package com.project.library.admin.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.admin.model.dao.AdminMapper;
import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;
import com.project.library.member.model.vo.Application;

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

	
	
}
