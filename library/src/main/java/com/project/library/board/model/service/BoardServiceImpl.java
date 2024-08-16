package com.project.library.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
