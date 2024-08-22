package com.project.library.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.library.board.model.vo.Notice;

@Mapper
public interface BoardMapper {

	int getListCount();

	ArrayList<Notice> selectBoardList(RowBounds rowBounds);

	Notice selectBoard(int noticeNo);

	int updateCount(int noticeNo);

	int insertRentalApplication(HashMap<Object, String> map);

	int insertVolunteerApp(HashMap<Object, String> map);

}
