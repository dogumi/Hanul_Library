package com.project.library.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.library.board.model.vo.PageInfo;
import com.project.library.member.model.vo.Application;
import com.project.library.member.model.vo.Member;

public interface MemberService {

	int insertMember(Member m);

	Member login(Member m);

	int checkId(String id);

	Member findId(Member m);

	Member findPwd(Member m);

	int updatePwd(HashMap<String, Object> map);

    ArrayList<HashMap<String, Object>> selectMyList(String id);

	int updateMember(HashMap<String, Object> map);

	int deleteMember(String id);

	ArrayList<Application> selectRentalApp(int writerNo, PageInfo pi);

	int selecrRentalAppCount(int writerNo);

	int selecrVolunteerAppCount(int writerNo);

	ArrayList<Application> selectVolunteerApp(int writerNo, PageInfo pi);

	Application selectRentalAppDetail(int app);

	int deleteApp(int appNo);

	Application selectVolunteerAppDetail(int app);



}
