package com.project.library.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.library.admin.model.vo.Employee;
import com.project.library.admin.model.vo.Greeting;
import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;

public interface BoardService {

	int getListCount();

	ArrayList<Notice> selectBoardList(PageInfo pi);

	Notice selectBoard(int noticeNo, int id);

	int insertRentalApplication(HashMap<Object, String> map);

	int insertVolunteerApp(HashMap<Object, String> map);

	ArrayList<Employee> selectDept();

	ArrayList<Employee> selectDeptName();

	Greeting selectGreeting();

}
