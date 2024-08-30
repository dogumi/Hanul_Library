package com.project.library.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.admin.model.vo.Employee;
import com.project.library.admin.model.vo.Greeting;
import com.project.library.board.model.dao.BoardMapper;
import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper bMapper;
	
	@Override
	public int getListCount() {
		return bMapper.getListCount();
	}

	@Override
	public ArrayList<Notice> selectBoardList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		return bMapper.selectBoardList(rowBounds);
	}

	@Override
	public Notice selectBoard(int noticeNo, int id) {
		Notice n = bMapper.selectBoard(noticeNo);
		if(n != null) {
			int result = bMapper.updateCount(noticeNo);
			if(result > 0) {
				n.setNoticeCount(n.getNoticeCount() + 1);
			}
		}
		return n;
	}

	@Override
	public int insertRentalApplication(HashMap<Object, String> map) {
		return bMapper.insertRentalApplication(map);
	}

	@Override
	public int insertVolunteerApp(HashMap<Object, String> map) {
		return bMapper.insertVolunteerApp(map);
	}

	@Override
	public ArrayList<Employee> selectDept() {
		return bMapper.selectDept();
	}

	@Override
	public ArrayList<Employee> selectDeptName() {
		return bMapper.selectDeptName();
	}

	@Override
	public Greeting selectGreeting() {
		return bMapper.selectGreeting();
	}

}
