package com.project.library.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


// 설정 파일 역할을 하는 Bean 생성
@Configuration
public class TemplateResolverConfig {
	
	@Bean 
	// 반환값을 Bean 으로 등록하겠다.
	public ClassLoaderTemplateResolver dotDoResolver() {
		
		ClassLoaderTemplateResolver dotDo = new ClassLoaderTemplateResolver();
		dotDo.setPrefix("templates/views/");					// 접두사 붙이는 애
		dotDo.setSuffix(".html");								// 접미사 붙이는 애
		dotDo.setTemplateMode(TemplateMode.HTML);				// 내가 넘길 곳이 어떤 Template를 가지고 있나?
		dotDo.setCharacterEncoding("UTF-8");					
		dotDo.setCacheable(false);								
		dotDo.setOrder(1);	
		dotDo.setCheckExistence(true);							// 여러개를 만들면 다 읽어올 수 있도록 하는 설정
		
		return dotDo;
	}
	
	@Bean
	public ClassLoaderTemplateResolver doMeResolver() {
		ClassLoaderTemplateResolver dotMe = new ClassLoaderTemplateResolver();
		dotMe.setPrefix("templates/views/member/");
		dotMe.setSuffix(".html");
		dotMe.setTemplateMode(TemplateMode.HTML);
		dotMe.setCharacterEncoding("UTF-8");
		dotMe.setCacheable(false);
		dotMe.setOrder(1);
		dotMe.setCheckExistence(true);
		
		return dotMe;
	}
	
	@Bean
	public ClassLoaderTemplateResolver dotBoResolver() {
		ClassLoaderTemplateResolver dotbo = new ClassLoaderTemplateResolver();
		dotbo.setPrefix("templates/views/board/");
		dotbo.setSuffix(".html");
		dotbo.setTemplateMode(TemplateMode.HTML);
		dotbo.setCharacterEncoding("UTF-8");
		dotbo.setCacheable(false);
		dotbo.setOrder(2);
		dotbo.setCheckExistence(true);
		
		return dotbo;
	}
	
	@Bean
	public ClassLoaderTemplateResolver dotAtResolver() {
		ClassLoaderTemplateResolver dotAt = new ClassLoaderTemplateResolver();
		dotAt.setPrefix("templates/views/attm/");
		dotAt.setSuffix(".html");
		dotAt.setTemplateMode(TemplateMode.HTML);
		dotAt.setCharacterEncoding("UTF-8");
		dotAt.setCacheable(false);
		dotAt.setOrder(2);
		dotAt.setCheckExistence(true);
		
		return dotAt;
	}
	
	@Bean
	public ClassLoaderTemplateResolver dotAdmResolver() {
		ClassLoaderTemplateResolver dotAdm = new ClassLoaderTemplateResolver();
		dotAdm.setPrefix("templates/views/admin/");
		dotAdm.setSuffix(".html");
		dotAdm.setTemplateMode(TemplateMode.HTML);
		dotAdm.setCharacterEncoding("UTF-8");
		dotAdm.setCacheable(false);
		dotAdm.setOrder(2);
		dotAdm.setCheckExistence(true);
		
		return dotAdm;
	}
}

