package com.iu.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.board.BoardVO;
import com.iu.s1.board.qna.QnaRepository;
import com.iu.s1.board.qna.QnaVO;
import com.iu.s1.member.MemberVO;


@Component
public class QnaInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private QnaRepository qnaRepository;
	
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse  response, Object handler ) throws Exception{
//		
//		boolean check = false;
//		
//		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
//		if(memberVO != null && memberVO.getId().equals("admin")) {
//			check = true;
//			
//		}else {
//			request.setAttribute("result", "권한이 없음");
//			request.setAttribute("path","../");
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
//			view.forward(request, response);
//
//		}
//		
//		
//		return check;
//
//		
//	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse  response, Object handler, Long id ) throws Exception{
		
		String method = request.getMethod();
		
		boolean check = false;
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		BoardVO boardVO = new BoardVO();
		QnaVO qnaVO = new QnaVO();
		int num = Integer.parseInt(request.getParameter("num"));
		qnaVO=qnaRepository.getOne(id);
		
		if(boardVO.getWriter().equals(memberVO.getId())) {
			check = true;
			
		}else {
			request.setAttribute("result", "권한이 없음");
			request.setAttribute("path","../");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			view.forward(request, response);

		}
		
		
		return check;

		
	}

}
