package com.iu.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	
	@GetMapping("qnaList")
	public ModelAndView boardList(
	@PageableDefault(size = 10, page = 0, direction = Direction.DESC, sort = {"num"})Pageable pageable,
	String kind, String search)throws Exception{
		ModelAndView mv = new ModelAndView();
		Page<QnaVO> page = qnaService.boardList(pageable,kind,search);
		
		System.out.println(page.getContent().size());
		System.out.println(page.getSize());
		System.out.println("Elements : "+page.getTotalElements());
		System.out.println("Pages : "+page.getTotalPages());
		System.out.println("Next : "+page.hasNext());
		System.out.println("Privious : "+page.hasPrevious());
		System.out.println("NUmber : "+page.getNumber());
		System.out.println("Content : "+page.hasContent());
		System.out.println("First : "+page.isFirst());
		System.out.println("Last : "+page.isLast());
		
		if(page.hasContent()) {
			mv.addObject("page", page);
			mv.setViewName("board/boardList");			
		}else {
			
			mv.setViewName("redirect:./qnaList");
		}
		return mv;
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView boardWrite()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardVO", new QnaVO());
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	

}
