package com.project.library.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.admin.model.exception.AdminException;
import com.project.library.admin.model.service.AdminService;
import com.project.library.admin.model.vo.Employee;
import com.project.library.admin.model.vo.Greeting;
import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;
import com.project.library.common.Pagination;
import com.project.library.member.model.vo.Application;
import com.project.library.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	
	// 관리자 페이지 - 공지사항 관리(작성 페이지 이동)
	@GetMapping("writeNoticeView.adm")
	public String writeNoticeView() {
		return "admin/writeNotice";
	}
	
	// 관리자 페이지 - 공지사항 관리 (공지사항 작성)
	@GetMapping("insertNotice.adm")
	public String insertNotice(@RequestParam("category") String category, @RequestParam("title") String title, @RequestParam("content") String content, HttpSession session) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		int memNo = loginUser.getMemNo();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("title", title);
		map.put("content", content);
		map.put("memNo", memNo);
		
		int result = aService.insertNotice(map);
		
		if(result > 0 ) {
			return "redirect:notice.adm";
		} else {
			throw new AdminException("게시글 작성에 실패했습니다.");
		}
	}
	
	// 관리자 페이지 - 공지사항 관리(상세보기)
	@GetMapping("noticeDetail.adm")
	public String noticeDetail(@RequestParam("noticeNo") int noticeNo, @RequestParam("page") int page, HttpSession session, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		Notice notice = aService.noticeDetail(noticeNo);
		
		if(notice != null) {
			model.addAttribute("n", notice);
			model.addAttribute("page", page);
			return "admin/noticeDetail";
		} else {
			throw new AdminException("게시글 상세보기를 실패했습니다.");
		}
	}
	
	//관리자 페이지 - 공지사항 관리 (수정 페이지 이동)
	@GetMapping("updateNoticeView.adm")
	public String updateNoticeView(@RequestParam("noticeNo") int noticeNo, @RequestParam("page") int page, @RequestParam("category") String category,
								@RequestParam("title") String title, @RequestParam("content") String content, HttpSession session, Model model) {
		
		Notice notice = aService.noticeDetail(noticeNo);
		
		if(notice != null) {
			model.addAttribute("n", notice);
			model.addAttribute("page", page);
			model.addAttribute("category", category);
			
			return "admin/noticeUpdate";
		} else {
			throw new AdminException("게시글 불러오기를 실패했습니다.");
		}
	}
	
	// 관리자 페이지 - 공지사항 관리 (게시글 수정)
	@GetMapping("updateNotice.adm")
	public String updateNotice(@RequestParam("title") String title, @RequestParam("content") String content,@RequestParam("page") int page,
								@RequestParam("category") String category, @RequestParam("noticeNo") int noticeNo,
								Model model) {
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("title", title);
		map.put("content", content);
		map.put("category", category);
		map.put("noticeNo", noticeNo);
		
		int result = aService.updateNotice(map);
		
		if(result > 0) {
			return "redirect:noticeDetail.adm?noticeNo=" + noticeNo + "&page=" + page ;
		} else {
			throw new AdminException("게시글 수정에 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 부서관리(운영관리부)
	@GetMapping("deptOper.adm")
	public String deptOper(Model model) {
		int deptNo = 30;
		ArrayList<Employee> list = aService.selectDept(deptNo);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("deptNo", deptNo);
			return "admin/deptOper";
		} else {
			throw new AdminException("부서정보를 불러오는 데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 부서관리(자료관리부)
	@GetMapping("deptData.adm")
	public String deptData(Model model) {
		int deptNo = 40;
		ArrayList<Employee> list = aService.selectDept(deptNo);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("deptNo", deptNo);
			return "admin/deptData";
		} else {
			throw new AdminException("부서정보를 불러오는 데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 부서관리(정보서비스부)
	@GetMapping("deptInfo.adm")
	public String deptInfo(Model model) {
		int deptNo = 50;
		ArrayList<Employee> list = aService.selectDept(deptNo);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("deptNo", deptNo);
			return "admin/deptInfo";
		} else {
			throw new AdminException("부서정보를 불러오는 데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 부서관리(프로그램기획부)
	@GetMapping("deptProg.adm")
	public String deptProg(Model model) {
		int deptNo = 60;
		ArrayList<Employee> list = aService.selectDept(deptNo);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("deptNo", deptNo);
			return "admin/deptProg";
		} else {
			throw new AdminException("부서정보를 불러오는 데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 부서관리(이용자지원부)
	@GetMapping("deptSuppt.adm")
	public String deptSuppt(Model model) {
		int deptNo = 70;
		ArrayList<Employee> list = aService.selectDept(deptNo);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("deptNo", deptNo);
			return "admin/deptSuppt";
		} else {
			throw new AdminException("부서정보를 불러오는 데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 부서관리(부서수정)
	@PostMapping("updateDept.adm")
	public String updateDept(@RequestParam("deptNo") int deptNo, @RequestParam("position[]") List<String> positions, @RequestParam("phone[]") List<String> phones,
							@RequestParam("duties[]") List<String> duties, @RequestParam("empNo[]") List<String> empNos, @RequestParam("path") String path) {
		int result = 0;
		
		for (int i = 0; i < positions.size(); i++) {
			String position = positions.get(i);
			String phone = phones.get(i);
			String duty = duties.get(i);
			String empNo = empNos.get(i);
			
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("position", position);
			map.put("phone", phone);
			map.put("duty", duty);
			map.put("deptNo", deptNo);
			map.put("empNo", empNo);
			
			result = aService.updateDept(map);
		}
		
		if(result > 0) {
			return "redirect:" + path + ".adm" ;
		} else {
			throw new AdminException("부서정보 업데이트에 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 직원추가 폼으로 이동
	@GetMapping("addEmp.adm")
	public String addEmp(@RequestParam("path") String path, @RequestParam("deptNo") String deptNo, Model model) {
		
		String deptName = null;
		if(deptNo.equals("30")) {
			deptName = "운영관리부";
		} else if (deptNo.equals("40")) {
			deptName = "자료관리부";
		} else if (deptNo.equals("50")) {
			deptName = "정보서비스부";
		} else if (deptNo.equals("60")) {
			deptName = "프로그램기획부";
		} else if (deptNo.equals("70")) {
			deptName = "이용자지원부";
		}
		
		model.addAttribute("path", path);
		model.addAttribute("deptNo", deptNo);
		model.addAttribute("deptName", deptName);
		
		return "admin/addEmp";
	}
	
	// 관리자페이지 - 직원추가
	@PostMapping("insertEmp.adm")
	public String insertEmp(@RequestParam("deptNo") String deptNo, @RequestParam("empName") String name, @RequestParam("empPhone") String phone,
							@RequestParam("empPosition") String position, @RequestParam("empDuties") String duty, @RequestParam("path") String path ,Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deptNo", deptNo);
		map.put("name", name);
		map.put("phone", phone);
		map.put("position",	position);
		map.put("duty", duty);
		
		int result = aService.insertEmp(map);
		
		if(result > 0) {
			return "redirect:" + path + ".adm";
		} else {
			throw new AdminException("직원추가 작업 중 오류가 발생했습니다.");
		}
	}
	
	// 관리자페이지 - 직원 삭제
	@GetMapping("deleteEmp.adm")
	public String delteEmp(@RequestParam("checked") List<String> checkedEmpNos, @RequestParam("path") String path) {
		
		int result = 0;
		for (int i = 0; i < checkedEmpNos.size(); i++) {
			String empNo = checkedEmpNos.get(i);
			
			result = aService.deleteEmp(empNo);
		}
		
		if(result > 0) {
			return "redirect:" + path + ".adm";
		} else {
			throw new AdminException("직원정보 삭제 중 오류가 발생했습니다.");
		}
	}
	
	// 관리자 페이지 - 인사말 관리 이동
	@GetMapping("greeting.adm")
	public String greeting(Model model) {
		Greeting g = aService.selectGreeting();
		
		if(g != null) {
			model.addAttribute("g", g);
			return "admin/greetingAdmin";
		} else {
			throw new AdminException("인사말을 조회하는 데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 인사말 관리 (수정)
	@PostMapping("updateGreeting.adm")
	public String updateGreeting(@RequestParam("content") String content, Model model) {
		int result = aService.updateGreeting(content);
		
		if(result > 0) {
			return "redirect:greeting.adm";
		} else {
			throw new AdminException("인사말 업데이트 중 오류가 발생했습니다.");
		}
	}
	
	// 관리자페이지 - 회원관리 (회원)
	@GetMapping("memConsumer.adm")
	public String memConsumer(@RequestParam(value="page", defaultValue="1") int currentPage, Model model, HttpServletRequest request) {
		String grade = "CONSUMER";
		
		int listCount = aService.getMemCount(grade);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 15);
		ArrayList<Member> m = aService.selectMem(grade, pi);
		
		if(m != null) {
			model.addAttribute("m", m);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			
			return "admin/memConsumer";
		} else {
			throw new AdminException("회원정보를 불러오는 데 실패했습니다.");
		}
		
	}
	
	// 관리자페이지 - 회원관리 (관리지)
	@GetMapping("memManager.adm")
	public String memManager(@RequestParam(value="page", defaultValue="1") int currentPage, Model model, HttpServletRequest request) {
		String grade = "MANAGER";
		
		int listCount = aService.getMemCount(grade);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 15);
		ArrayList<Member> m = aService.selectMem(grade, pi);
		
		if(m != null) {
			model.addAttribute("m", m);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			
			return "admin/memManager";
		} else {
			throw new AdminException("관리자 정보를 불러오는 데 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 회원관리 (계정비활성화)
	@GetMapping("activeOff.adm")
	public String activeOff(@RequestParam("memberNumbers") List<String> memberNumbers, @RequestParam("page") int page, @RequestParam("path") String path, Model model) {
		String state = "N";
		int result = 0;

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		for(int i = 0; i < memberNumbers.size(); i++) {
			String memNum = memberNumbers.get(i);
			map.put("memNum", memNum);
			map.put("state", state);
			
			result = aService.updateActive(map);
		}
		
		if(result > 0) {
			return "redirect:" + path + ".adm";
		} else {
			throw new AdminException("계정 비활성화 중 오류가 발생했습니다.");
		}
	}
	
	// 관리자페이지 - 회원관리 (계정활성화)
	@GetMapping("activeOn.adm")
	public String activeOn(@RequestParam("memberNumbers") List<String> memberNumbers, @RequestParam("page") int page, @RequestParam("path") String path, Model model) {
		String state = "Y";
		int result = 0;

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		for(int i = 0; i < memberNumbers.size(); i++) {
			String memNum = memberNumbers.get(i);
			map.put("memNum", memNum);
			map.put("state", state);
			
			result = aService.updateActive(map);
		}
		
		if(result > 0) {
			return "redirect:" + path + ".adm";
		} else {
			throw new AdminException("계정 비활성화 중 오류가 발생했습니다.");
		}
	}
	
	// 관리자페이지 - 회원관리 (회원등급 변경)
	@GetMapping("changeGrade.adm")
	public String changeGrade(@RequestParam("memberNumbers") List<String> memberNumbers, @RequestParam("page") int page, @RequestParam("path") String path, Model model) {
		
		String grade = null;
		int result = 0;
		if(path.equals("memConsumer")) {
			grade = "MANAGER";
		} else if (path.equals("memManager")) {
			grade = "CONSUMER";
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for(int i = 0; i < memberNumbers.size(); i++) {
			String memNo = memberNumbers.get(i);
			
			map.put("grade", grade);
			map.put("memNo", memNo);
			
			result = aService.changeGrade(map);
		}
		
		if(result > 0) {
			return "redirect:" + path + ".adm";
		} else {
			throw new AdminException("회원 등급 변경에 실패했습니다.");
		}
	}
	
	// 관리자페이지 - 회원관리 (회원검색)
	@GetMapping("searchMem.adm")
	public String searchMem(@RequestParam("search") String search, @RequestParam("path") String path, Model model, @RequestParam(value="page", defaultValue="1") int currentPage
			, HttpServletRequest request) {
		String grade = null;
		
		if(path.equals("memConsumer")) {
			grade = "CONSUMER";
		} else if(path.equals("memManager")) {
			grade = "MANAGER";
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("grade", grade);
		map.put("search", search);
		
		int listCount = aService.searchMemCount(map);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 15);
		System.out.println(listCount);
		map.put("pi", pi);
		
		ArrayList<Member> m = aService.searchMem(map);
		if( m != null) {
			model.addAttribute("m", m);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			return "admin/" + path;
		} else {
			throw new AdminException("검색에 실패했습니다.");
		}
		
	}
}
