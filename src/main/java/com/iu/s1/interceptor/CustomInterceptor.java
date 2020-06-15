package com.iu.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {

	//Controller 진입전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Controller 진입 전");
		// TODO Auto-generated method stub
		//return true 라면 controller 전송
		//return false라면 controller 진입 불가 - redirect 로 전송
		return true;
	}
	
	//Controller 진입 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Controller 진입 후");
	}
	
	//JSP 렌더링 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Jsp 렌더링 후");
	}
	
	//비동기 요청시 PostHandle과 afterCompletion 수행하지 않고 이 메서드를 실행
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
