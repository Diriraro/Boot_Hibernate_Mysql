package com.iu.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MemberCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		Object obj = request.getSession().getAttribute("member");
		
		if (obj != null) {
			return true;
		}else {
			request.setAttribute("result", "권한이 없음");
			request.setAttribute("path","../");
			return false;
		}
	}
	
}
