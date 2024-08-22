package com.project.library.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.library.common.interceptor.CheckLoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("file:///Users/hayeong/uploadFiles/", "classpath:static/"); // 매핑 URI 설정
		// 이미지에 접근하기 위해서 image/** 이라고 쓸거야 라는 의미
		// image로 시작을 안하고 싶다면 registry.addResourceHandler("/**"); 이렇게 작성해도 됨
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 일반 계정용
		registry.addInterceptor(new CheckLoginInterceptor())
				.addPathPatterns("/myPage.me", "/updateMember.me", "/deleteMember.me", "/changePwdView.me", 
						"/changePwd.me", "/rentalApplicationView.bo", "/volunteerAppView.bo",
						"/myRentalApp.me", "/myVolunteerApp.me", "/selectRentalApp.me", "/selectVolunteerApp.me",
						"/deleteRentalApp.me", "/deleteVolunteerApp.me");
		// 관리자용
//		registry.addInterceptor(new CheckAdminInterceptor())
//				.addPathPatterns("/*.adm");
//		
				
	}
}
