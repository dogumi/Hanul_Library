package com.project.library.admin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.admin.model.exception.AdminException;
import com.project.library.admin.model.service.AdminService;
import com.project.library.board.model.vo.Notice;
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
	
	// 관리자 페이지 - 신청관리 필터 조회
	@GetMapping("searchFilter.adm")
	public String searchFilter(@RequestParam("category") String category, @RequestParam(value="page", defaultValue="1") int currentPage, Model model, HttpServletRequest request) {
		
		PageInfo pi = null;
		ArrayList<Application> list;
		
		if (category.equals("rental")) {
			int listCount = aService.getAppListCount(10);
			pi = Pagination.getPageInfo(currentPage, listCount, 15);
			list = aService.selectFilterApplication(10, pi);
		} else if (category.equals("volunteer")) {
			int listCount = aService.getAppListCount(20);
			pi = Pagination.getPageInfo(currentPage, listCount, 15);
			list = aService.selectFilterApplication(20, pi);
		} else {
			int listCount = aService.getListCount();
			pi = Pagination.getPageInfo(currentPage, listCount, 15);
			list = aService.selectApplication(pi);
		}
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			model.addAttribute("category", category);
			model.addAttribute("loc", request.getRequestURI());
			
			return "admin/application";
		} else {
			throw new AdminException("신청 목록을 불러오는데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 신청관리 상세보기
	@GetMapping("selectAppDetail.adm")
	public String selectAppDetail(@RequestParam("appNo") int appNo, @RequestParam("page") int page, Model model) {
		
		Application content = aService.selectAppDetail(appNo);
		
		if(content != null) {
			model.addAttribute("content", content);
			model.addAttribute("page", page);
			return "admin/applicationDetail";
		} else {
			throw new AdminException("신청 상세보기를 불러오는데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 신청관리 상세보기 삭제
	@GetMapping("deleteApp.adm")
	public String deleteApp(@RequestParam("appNo") int appNo) {
		int result = aService.deleteApp(appNo);
		
		if(result > 0) {
			return "redirect:admin.adm";
		} else {
			throw new AdminException("신청정보를 삭제하는 데 실패했습니다.");
		}
	}
	
	
	// 관리자 페이지 - 공지사항 관리 목록 조회
	@GetMapping("notice.adm")
	public String noticeView(@RequestParam(value="page", defaultValue="1") int currentPage, Model model, HttpServletRequest request) {
		int listCount = aService.getNoticeListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 15);
		ArrayList<Notice> list = aService.selectNotice(pi);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			return "admin/notice";
		} else {
			throw new AdminException("공지사항 목록을 불러오는 데 실패했습니다.");
		}
	}
	
	// 관리자 페이지 - 공지사항 말머리
	@GetMapping("searchFilterNotice.adm")
	public String searchFilterNotice(@RequestParam("category") String category, @RequestParam(value="page", defaultValue="1") int currentPage, Model model, HttpServletRequest request) {
		
		ArrayList<Notice> list = null;
		PageInfo pi = null;
		String cate = null;
		
		if(category.equals("notice")) {
			cate = "공지사항";
			
		} else if(category.equals("event")) {
			cate = "이벤트";
			
		} else if(category.equals("news")){
			cate = "새소식";
			
		} else if (category.equals("all")){
			return "redirect:notice.adm";
		}
		
		int listCount = aService.noticeListCount(cate);
		pi = Pagination.getPageInfo(currentPage, listCount, 15);
		list = aService.selectNoticeList(cate, pi);
		
		if (list != null) {
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			model.addAttribute("category", category);
			
			return "admin/notice";
		} else {
			throw new AdminException("공지사항 목록을 불러오는 데 실패했습니다.");
		}
		
	}
	
	// 관리자 페이지 - 공지사항 작성
	@GetMapping("writeNoticeView.adm")
	public String writeNoticeView() {
		return "admin/writeNotice";
	}
	
}
