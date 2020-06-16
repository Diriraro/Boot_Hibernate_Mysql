package com.iu.s1.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberPage")
	public String memberMypage()throws Exception{
		return "member/memberPage";
	}
	
	@GetMapping("memberUpdate")
	public ModelAndView memberUpdate(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO)session.getAttribute("member");
		mv.addObject("memberVO", memberVO);
		mv.setViewName("member/memberUpdate");
		return mv;
	}
	
	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(@Valid MemberVO memberVO,BindingResult bindingResult)throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean check = true;
		boolean result = memberService.memberError(memberVO, bindingResult,check);
		if(result) {
			mv.setViewName("member/memberUpdate");
		}else {
			memberVO=memberService.memberUpdate(memberVO);
			mv.setViewName("/index");
		}
		return mv;	
	}
	@GetMapping("memberDelete")
	public ModelAndView memberDelete(MemberVO memberVO,RedirectAttributes rd,HttpSession session)throws Exception{
		boolean  check = memberService.memberDelete(memberVO);
		ModelAndView mv = new ModelAndView();
		
		if(check) {
			rd.addFlashAttribute("result","삭제 실패");
			mv.setViewName("redirect:../");
		}else {
			rd.addFlashAttribute("result","삭제 성공");
			session.invalidate();
			mv.setViewName("redirect:../");
		}
		System.out.println(check);
		
		return mv;
	}
	
	@GetMapping("memberLogin")
	public ModelAndView memberLogin()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		return mv;
	}
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO=memberService.memberLogin(memberVO);
		if(memberVO !=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}else {
			mv.setViewName("redirect:./memberLogin");
		}
		return mv;
	}
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		mv.setViewName("member/memberJoin");
		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile [] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean check = false;
		boolean result = memberService.memberError(memberVO, bindingResult,check);
		if(result) {
			mv.setViewName("member/memberJoin");
		}else {
			memberService.memberJoin(memberVO,files);
			mv.setViewName("/index");
		}
		
		return mv;
	}
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}

}
