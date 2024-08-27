package com.project.library.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.project.library.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckAdminInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null || loginUser.getMemGrade().equals("CONSUMER")) {
			String url = request.getRequestURI();
			
			String msg = null;
			if(url.contains("admin.adm") ||  url.contains("searchFilter.adm") || url.contains("selectAppDetail.adm") || url.contains("notice.adm") ) {
				msg = "접근 권한이 없습니다.";
			} 
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script>alert('" + msg + "'); location.href='home.do';</script>");
			
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
