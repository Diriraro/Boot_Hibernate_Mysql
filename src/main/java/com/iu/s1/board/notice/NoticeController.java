package com.iu.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("noticeList")
	//@PageableDefault(page = 0, size = 10, sort = {"num"}, direction = Direction.DESC) Pageable pageable, @RequestParam(defaultValue = "") String search
	public ModelAndView boardList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		//									(page, size, Sort, culumn)
		//Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "num");
		
		Page<NoticeVO> ar = noticeService.boardList(pager);
		
		mv.setViewName("board/boardList");
		mv.addObject("page",ar);
		mv.addObject("pager",pager);
		
		return mv;
	}

}
