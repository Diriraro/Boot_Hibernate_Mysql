package com.iu.s1.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberJoin");
		mv.addObject("memberVO", new MemberVO());
		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean re = memberService.memberError(memberVO, bindingResult);
		
		if(re) {
			
			mv.setViewName("member/memberJoin");
			
		 }else {
			int result = memberService.memberJoin(memberVO);
			
			if(result!=0) {
				mv.addObject("result", "success");
				mv.addObject("path", "../");
				mv.setViewName("common/result");				
			}			
		 }
		return mv;
	}
	
}
