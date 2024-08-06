package com.project.library.member.controller;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.project.library.member.model.exception.MemberException;
import com.project.library.member.model.service.MemberService;
import com.project.library.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class MemberController {

	@Autowired
	MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	// 로그인 페이지 이동
	@GetMapping("loginView.me")
	public String moveLoginView() {
		return "login";
	}
	
	// 로그인
	@PostMapping("login.me")
	public String login(@ModelAttribute Member m, Model model) {
		Member loginUser = mService.login(m);

		if (bcrypt.matches(m.getMemPwd(), loginUser.getMemPwd())) {
			model.addAttribute("loginUser", loginUser);
			return "redirect:home.do";
		} else {
			throw new MemberException("로그인을 실패하였습니다.");
		}
	}
	
	// 회원가입 페이지 이동
	@GetMapping("enroll.me")
	public String enroll() {
		return "enroll";
	}
	
	//회원가입
	@PostMapping("insertMember.me")
	public String insertMember(@ModelAttribute Member m, 
							   @RequestParam("emailId") String emailId, 
							   @RequestParam("emailDomain")String emailDomain){
		String email = null;
		if(!emailId.equals("")) {
			email = emailId + "@" + emailDomain;
		}
		m.setMemEmail(email);
		
		m.setMemPwd(bcrypt.encode(m.getMemPwd()));
		
		int result = mService.insertMember(m);
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원가입에 실패했습니다.");
		}
	}
	
	// 로그아웃
	@GetMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}
	
	// 아이디 중복 체크
	@GetMapping("checkId.me")
	public void checkId(@RequestParam("id") String id, PrintWriter out) {
		int count = mService.checkId(id);
		String result = count == 0 ? "yes" : "no";
		out.print(result);
	}
	
	// 아이디 찾기 페이지 이동
	@GetMapping("findIdView.me")
	public String findIdView() {
		return "findIdView";
	}
	
	// 아이디 찾기
	@PostMapping("findId.me")
	public String findId(@ModelAttribute Member m, Model model) {
		Member id = mService.findId(m);
		System.out.println(id);
		if(id != null) {
			model.addAttribute("mem", id);
			return "findIdSuccess";
		} else {
			return "findIdFail";
		}
	}
}
