package com.project.library.board.model.service;

import java.util.ArrayList;

import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;

public interface BoardService {

	int getListCount();

	ArrayList<Notice> selectBoardList(PageInfo pi);

	Notice selectBoard(int noticeNo, int id);

}
