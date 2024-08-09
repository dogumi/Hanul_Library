package com.project.library.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

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

import jakarta.servlet.http.HttpSession;


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
		if(id != null) {
			model.addAttribute("mem", id);
			return "findIdSuccess";
		} else {
			return "findIdFail";
		}
	}
	
	// 비밀번호 찾기 페이지로 이동
	@GetMapping("findPwdView.me")
	public String findPwdView() {
		return "findPwdView";
	}
	
	// 비밀번호 찾기
	@PostMapping("findPwd.me")
	public String findPwd(@ModelAttribute Member m, Model model) {
		Member result = mService.findPwd(m);
		if(result != null) {
			model.addAttribute("mem", result);
			return "updatePwd";
		} else {
			return "findPwdFail";
		}
	}
	
	// 비밀번호 재설정
	@PostMapping("updatePwd.me")
	public String updatePwd(@RequestParam ("memPwd") String memPwd, @RequestParam("memId") String memId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String newPwd = (bcrypt.encode(memPwd));
		map.put("newPwd", newPwd);
		map.put("id", memId);
		
		int result = mService.updatePwd(map);
		System.out.println(result);
		if(result > 0) {
			return "findPwdSuccess";
		} else {
			return "findPwdFail";
		}
	}
	
	// 내정보수정 이동
	@GetMapping("myPage.me")
	public String myPageView(Model model, HttpSession session) {
		String id = ((Member)session.getAttribute("loginUser")).getMemId();
	    ArrayList<HashMap<String,Object>> list = mService.selectMyList(id);
	    model.addAttribute("list", list);
		return "myPage";
	} 

	// 내정보수정
	@PostMapping("updateMember.me")  
	  public String updateMember(@ModelAttribute Member m, @RequestParam("emailId") String emailId, 
	  							@RequestParam("emailDomain") String emailDomain, @RequestParam("memPhone") String memPhone, 
								@RequestParam("memName") String memName, Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String email = null;
		if(!emailId.trim().equals("")) {
			email = emailId + "@" + emailDomain;
		}
		m.setMemEmail(email);
		 
		map.put("name", memName);
		map.put("email", email);
		map.put("phone", memPhone);
		map.put("id", m.getMemId());

		 int result = mService.updateMember(map);
		 if(result > 0) {
			 model.addAttribute("loginUser", mService.login(m));
			 return "home";
		 }else {
			 throw new MemberException("정보수정에 실패했습니다.");
		 }
	}

	

	// 회원 탈퇴
	@GetMapping("deleteMember.me")
	public String deleteMember(HttpSession session, SessionStatus status) {
		String id = ((Member)session.getAttribute("loginUser")).getMemId();

		int result = mService.deleteMember(id);

		if(result > 0) {
			status.setComplete();
			return "home";
		} else {
			throw new MemberException("회원 탈퇴에 실패했습니다. 다시 시도해주세요.");
		}
	}
	
	// 내정보 - 비밀번호 변경 페이지 이동
	@GetMapping("changePwdView.me")
	public String changePwdView(){
		return "changePwd";
	}

	// 내정보 - 비밀번호 변경
	@PostMapping("changePwd.me")
	public String changePwd(@RequestParam String memPwd, HttpSession session) {
		String id = ((Member)session.getAttribute("loginUser")).getMemId();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String newPwd = (bcrypt.encode(memPwd));
		
		map.put("newPwd", newPwd);
		map.put("id", id);
		
		int result = mService.updatePwd(map);
		System.out.println(result);
		if(result > 0) {
			return "findPwdSuccess";
		} else {
			return "findPwdFail";
		}
	}
}
