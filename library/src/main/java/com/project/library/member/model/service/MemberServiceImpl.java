package com.project.library.member.model.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.member.model.dao.MemberMapper;
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


	
}
