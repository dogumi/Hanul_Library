package com.project.library.admin.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.library.member.model.vo.Application;

@Mapper
public interface AdminMapper {

	ArrayList<Application> selectApplication(RowBounds rowBounds);

	int getListCount();
	
}
