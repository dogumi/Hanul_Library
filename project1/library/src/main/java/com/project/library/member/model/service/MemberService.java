package com.project.library.member.model.service;

import com.project.library.member.model.vo.Member;

public interface MemberService {

	int insertMember(Member m);

	Member login(Member m);

	int checkId(String id);

	Member findId(Member m);

}
