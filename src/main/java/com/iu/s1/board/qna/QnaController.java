package com.iu.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s1.board.BoardVO;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView boardWrite()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardVO", new QnaVO());
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	

}
