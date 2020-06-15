package com.iu.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.board.BoardVO;
import com.iu.s1.board.qna.QnaRepository;
import com.iu.s1.member.MemberVO;
@Component
public class QnaInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private QnaRepository qnaRepository;
	
	//Controller 진입전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {	
		//return true 라면 controller 전송
		//return false라면 controller 진입 불가 - redirect 로 전송
		String method =request.getMethod();
		
		boolean check = false;
		if(method.equals("GET")) {
			
			MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
			if(memberVO==null) {
				request.setAttribute("result", "권한이 없음");
				request.setAttribute("path", "../");
				
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
				view.forward(request, response);
			}else {
				check=true;
			}
			
		}
		return check;
		
//		Object obj = request.getSession().getAttribute("member");
//		
//		if(obj != null) {
//			System.out.println("Member");
//			return true;
//		}else {
//			System.out.println("로그인이 필요");
//			response.sendRedirect("./qnaList");
//			
//			return false;
//		} 													//////이렇게도 할 수 있음
		
	}
		
}
