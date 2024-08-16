package com.project.library.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.library.board.model.vo.Notice;

@Mapper
public interface BoardMapper {

	int getListCount();

	ArrayList<Notice> selectBoardList(RowBounds rowBounds);

	Notice selectBoard(int noticeNo);

	int updateCount(int noticeNo);

}
