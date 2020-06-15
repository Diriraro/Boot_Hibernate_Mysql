package com.iu.s1.board.notice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.s1.board.BoardVO;
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
	//@PageableDefault(page = 0,size = 10,sort = {"num"},direction = Direction.DESC)Pageable pageable, @RequestParam(defaultValue = "") String search
	public ModelAndView noticeList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		//						page size		 sort		 컬럼명
//		pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"num");
		List<NoticeVO> noticeVOs = noticeService.noticeList(pager);
		mv.addObject("list", noticeVOs);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView noticeSelect(long num)throws Exception{
		ModelAndView mv = new ModelAndView();
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setNum(num);
		noticeVO = noticeService.noticeSelect(noticeVO);
		
		for (NoticeFileVO noticeFileVO : noticeVO.getNoticeFileVOs()) {
			System.out.println(noticeFileVO.getFileName());
			System.out.println(noticeFileVO.getOriName());
		}
		
		mv.addObject("boardVO", noticeVO);
		mv.setViewName("board/boardSelect");
		
		return mv;
	}
	
	@GetMapping("noticeWrite")
	public ModelAndView noticeWrite()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("path", "Write");
		mv.addObject("boardVO", new BoardVO());
		
		return mv;
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView setInsert(@Valid NoticeVO noticeVO, BindingResult bindingResult, 
			MultipartFile [] files, RedirectAttributes rd)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//에러가 있을경우 / 없을경우를 if문으로
		if(bindingResult.hasErrors()) { //에러 검증하는 방법은 boardVO가서 하기
			mv.addObject("path", "Write");
			mv.setViewName("board/boardWrite");
		}else {
			int result = noticeService.noticeWrite(noticeVO);
			rd.addFlashAttribute("result", result);
			mv.setViewName("redirect:./noticeList");
		}
		
		return mv;
	}
	
}
