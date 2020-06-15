package com.iu.s1.board.qna;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("qnaDelete")
	public ModelAndView boardDelete(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean result = qnaService.boardDelete(qnaVO);
		if(!result) {
			
		}
		mv.setViewName("redirect:./qnaList");
		return mv;
	}
	
	@GetMapping("qnaSelect")
	public ModelAndView boardSelect(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		qnaVO = qnaService.boardSelect(qnaVO);
		
		if(qnaVO != null) {
			mv.setViewName("board/boardSelect");
			mv.addObject("vo", qnaVO);
		}
		
		
		return mv;
	}
	
	@GetMapping("qnaList")
	public ModelAndView boardList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		Page<QnaVO> page = qnaService.boardList(pager);
		
		System.out.println(page.getContent().size());
		System.out.println(page.getSize());
		System.out.println("Elements : "+page.getTotalElements());
		System.out.println("Pages : "+page.getTotalPages());
		System.out.println("Next : "+page.hasNext());
		System.out.println("Previous : "+page.hasPrevious());
		System.out.println("number : "+page.getNumber());
		System.out.println("Content : "+page.hasContent());
		System.out.println("First : "+page.isFirst());
		System.out.println("Last : "+page.isLast());
		
		mv.addObject("page", page);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	
	@GetMapping("qnaWrite")
	public ModelAndView boardWrite()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("path", "Write");
		mv.addObject("boardVO", new QnaVO());
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView boardWrite(QnaVO qnaVO, MultipartFile [] files)throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardWrite(qnaVO, files);
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
	
	@GetMapping("qnaUpdate")
	public ModelAndView boardUpdate(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		qnaVO = qnaService.boardSelect(qnaVO);
		mv.addObject("boardVO", qnaVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		
		return mv;
	} 
	
	@PostMapping("qnaUpdate")
	public ModelAndView boardUpdate(ModelAndView mv, QnaVO qnaVO, MultipartFile [] files)throws Exception{
		
		 qnaService.boardUpdate(qnaVO, files);
		 mv.setViewName("redirect:./qnaList");
		
		
		return mv;
	} 
	
	@GetMapping("qnaReply")
	public ModelAndView boardReply(ModelAndView mv, QnaVO qnaVO)throws Exception{
		
		mv.addObject("path", "Reply");
		mv.addObject("boardVO", qnaVO);
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public ModelAndView boardReply(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardReply(qnaVO);
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
	
}

//@GetMapping("qnaList")
//public ModelAndView boardList(@PageableDefault(size = 10, page = 0, direction = Direction.DESC, sort = {"num"})Pageable pageable,
//HttpServletRequest request)throws Exception{
//	ModelAndView mv = new ModelAndView();
//	String kind="";
//	String search="";
//	if(request.getParameter("kind")!=null || request.getParameter("search")!=null) {
//		kind=request.getParameter("kind");
//		search = request.getParameter("search");
//	}
//	Page<QnaVO> page = qnaService.boardList(pageable,kind,search);
//	
//	System.out.println(page.getContent().size());
//	System.out.println(page.getSize());
//	System.out.println("Elements : "+page.getTotalElements());
//	System.out.println("Pages : "+page.getTotalPages());
//	System.out.println("Next : "+page.hasNext());
//	System.out.println("Privious : "+page.hasPrevious());
//	System.out.println("NUmber : "+page.getNumber());
//	System.out.println("Content : "+page.hasContent());
//	System.out.println("First : "+page.isFirst());
//	System.out.println("Last : "+page.isLast());
//	
//	if(page.hasContent()) {
//		mv.addObject("page", page);
//		mv.setViewName("board/boardList");			
//	}else {
//		
//		mv.setViewName("redirect:./qnaList");
//	}
//	return mv;
//}
