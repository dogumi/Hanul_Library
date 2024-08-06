package com.project.library.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.library.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int insertMember(Member m);

	Member login(Member m);

	int checkId(String id);

	Member findId(Member m);

}
