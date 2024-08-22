package com.project.library.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.board.model.vo.PageInfo;
import com.project.library.member.model.dao.MemberMapper;
import com.project.library.member.model.vo.Application;
import com.project.library.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	
	@Autowired
	private MemberMapper mMapper;

	// 회원가입
	@Override
	public int insertMember(Member m) {
		return mMapper.insertMember(m);
	}

	
	// 로그인
	@Override
	public Member login(Member m) {
		return mMapper.login(m);
	}


	@Override
	public int checkId(String id) {
		return mMapper.checkId(id);
	}


	@Override
	public Member findId(Member m) {
		return mMapper.findId(m);
	}


	@Override
	public Member findPwd(Member m) {
		return mMapper.findPwd(m);
	}


	@Override
	public int updatePwd(HashMap<String, Object> map) {
		return mMapper.updatePwd(map);
	}


	@Override
	public ArrayList<HashMap<String, Object>> selectMyList(String id) {
		return mMapper.selectMyList(id);
	}


	@Override
	public int updateMember(HashMap<String, Object> map) {
		return mMapper.updateMember(map);
	}


	@Override
	public int deleteMember(String id) {
		return mMapper.deleteMember(id);
	}


	@Override
	public int selecrRentalAppCount(int writerNo) {
		return mMapper.selectRentalAppCount(writerNo);
	}


	@Override
	public ArrayList<Application> selectRentalApp(int writerNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		return mMapper.selectRentalApp(writerNo, rowBounds);
	}


	@Override
	public int selecrVolunteerAppCount(int writerNo) {
		return mMapper.selectVolunteerAppCount(writerNo);
	}


	@Override
	public ArrayList<Application> selectVolunteerApp(int writerNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		return mMapper.selectVolunteerApp(writerNo, rowBounds);
	}


	@Override
	public Application selectRentalAppDetail(int app) {
		return mMapper.selectRentalAppDetail(app);
	}


	@Override
	public int deleteApp(int appNo) {
		return mMapper.deleteApp(appNo);
	}


	@Override
	public Application selectVolunteerAppDetail(int app) {
		return mMapper.selectVolunteerAppDetail(app);
	}


	
}
