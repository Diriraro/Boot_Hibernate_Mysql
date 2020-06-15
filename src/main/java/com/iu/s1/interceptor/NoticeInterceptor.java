package com.iu.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.board.notice.NoticeRepository;
import com.iu.s1.member.MemberVO;
@Component
public class NoticeInterceptor extends HandlerInterceptorAdapter {
	

	//Controller 진입전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {	
		boolean check = false;
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		if(memberVO!=null) {
			if(memberVO.getId().equals("admin")) {
				check=true;
			}
//			}else {
//				request.setAttribute("result", "권한이 없음");
//				request.setAttribute("path", "../");
//
//				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
//				view.forward(request, response);
//			}
		}else {
			response.sendRedirect("../message/messageResult?result=admin이아닙니다&path=../notice/noticeList");
			//homeController에 메서드를 추가해서 파라미터로 넘기는 방법
		}
		
		
		return check;
	}
	
}
