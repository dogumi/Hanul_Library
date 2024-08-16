package com.project.library.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.library.board.model.service.BoardService;

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
    public String departmentView() {
    	return "intro/department";
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
    public String noticeView() {
    	return "information/notice";
    }
}
