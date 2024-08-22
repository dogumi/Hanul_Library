package com.project.library.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.library.member.model.vo.Application;
import com.project.library.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int insertMember(Member m);

	Member login(Member m);

	int checkId(String id);

	Member findId(Member m);

	Member findPwd(Member m);

	int updatePwd(HashMap<String, Object> map);

	ArrayList<HashMap<String, Object>> selectMyList(String id);

	int updateMember(HashMap<String, Object> map);

    int deleteMember(String id);

	ArrayList<Application> selectRentalApp(int writerNo, RowBounds rowBounds);

	int selectRentalAppCount(int writerNo);

	int selectVolunteerAppCount(int writerNo);

	ArrayList<Application> selectVolunteerApp(int writerNo, RowBounds rowBounds);

	Application selectRentalAppDetail(int app);

	int deleteApp(int appNo);

	Application selectVolunteerAppDetail(int app);


}
