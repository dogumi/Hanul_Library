package com.project.library.admin.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.admin.model.dao.AdminMapper;
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
	
}
