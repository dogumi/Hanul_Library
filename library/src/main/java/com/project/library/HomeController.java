package com.project.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index() {
		return "home";
	}
	
	@GetMapping("home.do")
	public String home() {
		return "home";
	}
}
