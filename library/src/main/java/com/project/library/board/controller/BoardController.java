package com.project.library.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.library.board.model.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService bService;

    @GetMapping("greetingView.bo")
    public String greetingView() {
        return "intro/greeting";
    }
    
    @GetMapping("departmentView.bo")
    public String departmentView() {
    	return "intro/department";
    }
}
