package com.project.library.admin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.admin.model.exception.AdminException;
import com.project.library.admin.model.service.AdminService;
import com.project.library.board.model.vo.PageInfo;
import com.project.library.common.Pagination;
import com.project.library.member.model.vo.Application;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService aService;
	
	// 관리자 페이지 - 신청관리 목록 전체 조회
	@GetMapping("admin.adm")
	public String applicationAdminView(@RequestParam(value="page", defaultValue= "1") int currentPage, Model model, HttpServletRequest request) {
		int listCount = aService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 15);
		ArrayList<Application> list = aService.selectApplication(pi);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			
			return "admin/application";
		} else {
			throw new AdminException("신청목록을 불러오는 데 실패했습니다.");
		}
	}
}
