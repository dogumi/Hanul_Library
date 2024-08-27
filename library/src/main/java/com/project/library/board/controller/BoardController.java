package com.project.library.board.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.admin.model.vo.Employee;
import com.project.library.board.model.exception.BoardException;
import com.project.library.board.model.service.BoardService;
import com.project.library.board.model.vo.Notice;
import com.project.library.board.model.vo.PageInfo;
import com.project.library.common.Pagination;
import com.project.library.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    private BoardService bService;
    
    
    // 인사말 이동
    @GetMapping("greetingView.bo")
    public String greetingView() {
        return "intro/greeting";
    }
    
    // 부서 안내 이동
    @GetMapping("departmentView.bo")
    public String departmentView(Model model) {
    	ArrayList<Employee> list = bService.selectDept();
    	ArrayList<Employee> deptName = bService.selectDeptName();
    	
    	if(list != null) { 
    		model.addAttribute("list", list);
    		model.addAttribute("d", deptName);
    		return "intro/department";
    	} else {
    		throw new BoardException("부서정보를 불러올 수 없습니다.");
    	}
    }
    
    
    // 시설대관 이동
    @GetMapping("facillityRentalView.bo")
    public String facillityRentalView() {
    	return "application/facillityRental";
    }
    
    // 자원봉사 이동
    @GetMapping("volunteerView.bo")
    public String volunteerView() {
    	return "application/volunteer";
    }
    
    
    // 공지사항 이동
    @GetMapping("noticeView.bo")
    public String noticeView(@RequestParam(value = "page", defaultValue = "1") int currentPage, Model model, HttpServletRequest request) {
    	int listCount = bService.getListCount();
    	PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 15);
    	ArrayList<Notice> list = bService.selectBoardList(pi);
    	
    	if(list != null) {
    		model.addAttribute("list", list);
    		model.addAttribute("pi", pi);
    		model.addAttribute("loc", request.getRequestURI());
    		
    		return "information/noticeList";
    	} else {
    		throw new BoardException("게시글 조회를 실패했습니다.");
    	}
    }
    
    // 공지사항 게시글 상세보기
    @GetMapping("selectBoard.bo")
    public String noticeDetail(@RequestParam("noticeNo") int noticeNo, @RequestParam("page") int page, HttpSession session, Model model) {
    	Member loginUser = (Member) session.getAttribute("loginUser");
    	int id = 0;
    	if (loginUser != null) {
    		id = loginUser.getMemNo();
    	}
    	
    	Notice notice = bService.selectBoard(noticeNo, id);
    	
    	
    	if (notice != null) {
    		model.addAttribute("n", notice);
    		model.addAttribute("page", page);
    		return "information/noticeDetail"; 
    	} else {
    		throw new BoardException("게시글 상세보기를 실패하였습니다.");
    	}
    }
    
    // 시설대관 신청서 작성 페이지 이동
    @GetMapping("rentalApplicationView.bo")
    public String rentalApplicationView() {
    	return "application/rentalApplication";
    }
    
    
    // 시설대관 신청
    @GetMapping("insertRentalApplication.bo")
    public String insertRentalApplication(@RequestParam("appTitle") String appTitle, @RequestParam("appContent") String appContent, HttpSession session, Model model) {
    	Member loginUser = (Member)session.getAttribute("loginUser");
    	String writerNo = null;
    	if(loginUser != null) {
    		writerNo = loginUser.getMemNo() + "";
    	}
    	
    	HashMap<Object, String> map = new HashMap<Object, String>();
    	map.put("appTitle", appTitle);
    	map.put("appContent", appContent);
    	map.put("writerNo", writerNo);
    	
    	
    	int result = bService.insertRentalApplication(map);
    	
    	if(result > 0 ) {
    		model.addAttribute("result", "success");
    		return "application/facillityRental";
    	} else {
    		throw new BoardException("신청서 작성 중 오류가 발생하였습니다. 다시 작성해주세요.");
    	}
    }
    
    // 자원봉사 신청서 작성 페이지로 이동
    @GetMapping("volunteerAppView.bo")
    public String volunteerAppView() {
    	return "application/volunteerApp";
    }
    
    // 자원봉사 신청
    @GetMapping("volunteerApp.bo")
    public String volunteerApp(@RequestParam("appTitle") String appTitle, @RequestParam("appContent") String appContent, HttpSession session, Model model) {
    	Member loginUser = (Member)session.getAttribute("loginUser");
    	String writerNo = null;
    	if(loginUser != null) {
    		writerNo = loginUser.getMemNo() + "";
    	}
    	
    	HashMap<Object, String> map = new HashMap<Object, String>();
    	map.put("appTitle", appTitle);
    	map.put("appContent", appContent);
    	map.put("writerNo", writerNo);
    	
    	int result = bService.insertVolunteerApp(map);
    	
    	if(result > 0 ) {
    		model.addAttribute("result", "success");
    		return "application/volunteer";
    	} else {
    		throw new BoardException("신청서 작성 중 오류가 발생했습니다. 다시 작성해주세요.");
    	}
    	
    }
    
}
