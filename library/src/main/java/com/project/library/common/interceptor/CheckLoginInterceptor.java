package com.project.library.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.project.library.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckLoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			String url = request.getRequestURI();
			
			String msg = null;
			if(url.contains("myPage.me") || url.contains("updateMember.me") || url.contains("deleteMember.me") || url.contains("changePwdView.me") 
					|| url.contains("changePwd.me") || url.contains("rentalApplicationView.bo") || url.contains("volunteerAppView.bo")
					|| url.contains("myRentalApp.me") || url.contains("myVolunteerApp.me") || url.contains("selectRentalApp.me")
					|| url.contains("selectVolunteerApp.me") || url.contains("deleteRentalApp.me") || url.contains("deleteVolunteerApp.me")) {
				
				msg = "로그인 후 이용하세요";
			} else {
				msg = "로그인 세션이 만료되어 로그인 화면으로 넘어갑니다.";
			}
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("<script>alert('" + msg + "'); location.href='loginView.me';</script>");
			// <script> alert(로그인 후 이용하세요);</script>
			
			
			
			
			return false;
		
		}
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
