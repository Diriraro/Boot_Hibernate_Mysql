package com.iu.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iu.s1.interceptor.CustomInterceptor;
import com.iu.s1.interceptor.NoticeInterceptor;
import com.iu.s1.interceptor.QnaInterceptor;
import com.iu.s1.interceptor.QnaInterceptor2;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	//springBoot 2.0 이후부터는 Deprecated
	//WebMvcConfigurerAdapter
	
	//springBoot 2.0 이후는 다음을 사용
	//WebMvcConfigurer
	
	@Autowired
	private CustomInterceptor customInterceptor;
	@Autowired
	private QnaInterceptor qnaInterceptor;
	@Autowired
	private QnaInterceptor2 qnaInterceptor2;
	@Autowired
	private NoticeInterceptor noticeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		//적용할 Interceptor 등록
//		registry.addInterceptor(customInterceptor)
//		//Interceptor를 사용할 URL 등록
//		.addPathPatterns("/notice/*")
//		//Interceptor에서 제외할 URL 등록
//		.excludePathPatterns("/notice/noticeList");
		
		registry.addInterceptor(qnaInterceptor)
		.addPathPatterns("/qna/qnaSelect")
		.addPathPatterns("/qna/qnaWrite")
		.addPathPatterns("/qna/qnaReply");
		
		registry.addInterceptor(qnaInterceptor2)
		.addPathPatterns("/qna/qnaUpdate")
		.addPathPatterns("/qna/qnaDelete");
		
//		registry.addInterceptor(noticeInterceptor)
//		.addPathPatterns("/notice/*")		
//		.excludePathPatterns("/notice/noticeList")
//		.excludePathPatterns("/notice/noticeSelect");
		
		
		// TODO Auto-generated method stub
		// WebMvcConfigurer.super.addInterceptors(registry);
	}

}
