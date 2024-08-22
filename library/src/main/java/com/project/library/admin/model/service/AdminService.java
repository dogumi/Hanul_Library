package com.project.library.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.library.board.model.vo.PageInfo;
import com.project.library.member.model.vo.Application;
import com.project.library.member.model.vo.Member;

public interface AdminService {

	ArrayList<Application> selectApplication(PageInfo pi);

	int getListCount();

}
