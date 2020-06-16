package com.iu.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iu.s1.interceptor.CustomInterceptor;
import com.iu.s1.interceptor.MemberCheckInterceptor;
import com.iu.s1.interceptor.NoticeInterceptor;
import com.iu.s1.interceptor.QnaInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	
	@Autowired
	private CustomInterceptor customInterceptor;
	
	@Autowired
	private NoticeInterceptor noticeInterceptor;
	
	@Autowired
	private QnaInterceptor qnaInterceptor;
	
	@Autowired
	private MemberCheckInterceptor memberCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		registry.addInterceptor(noticeInterceptor)
//		.addPathPatterns("notice/noticeDelete")
//		.addPathPatterns("notice/noticeWrite")
//		.addPathPatterns("notice/noticeUpdate");

		//적용할 Interceptor 등록
		registry.addInterceptor(memberCheckInterceptor)
		//Interceptor를 사용할 URL 등록
		.addPathPatterns("/qna/*")
		//Interceptro에서 제외할 URL 등록
		.excludePathPatterns("/qna/qnaList")
		.excludePathPatterns("/qna/qnaSelect")
		.excludePathPatterns("/qna/qnaWrite");
		
		
		// TODO Auto-generated method stub
		// WebMvcConfigurer.super.addInterceptors(registry);
	}

}
