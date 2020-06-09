package com.iu.s1.member;

import java.util.Optional;

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
	public ModelAndView memberJoin(@Valid MemberVO memberVO, BindingResult bindingResult,MultipartFile [] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean re = memberService.memberError(memberVO, bindingResult);
		
		if(re) {
			
			mv.setViewName("member/memberJoin");
			
		 }else {
			int result = memberService.memberJoin(memberVO,files);
			
			if(result!=0) {
				mv.addObject("result", "success");
				mv.addObject("path", "../");
				mv.setViewName("common/result");				
			}			
		 }
		return mv;
	}
	
	@GetMapping("memberLogin")
	public ModelAndView memberLogin()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		mv.setViewName("member/memberLogin");
		return mv;
	}
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();		
		
		int result = memberService.memberLogin(memberVO);
		
		if(result!=0) {
			memberVO = memberService.inSession(memberVO);
			session.setAttribute("member", memberVO);
			mv.addObject("result", "success");
			mv.addObject("path", "../");
			mv.setViewName("common/result");	
		}else {				
			mv.addObject("result", "login fail");
			mv.addObject("path", "member/memberLogin");
			mv.setViewName("common/result");	
		}		
		
		return mv;
	}
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	@GetMapping("memberPage")
	public ModelAndView memberPage(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO)session.getAttribute("member");
		System.out.println(memberVO.getId());
		memberVO = memberService.inSession(memberVO);
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName(memberVO.getMemberFileVO().getFileName());
		memberFileVO.setOriName(memberVO.getMemberFileVO().getOriName());
		mv.addObject("memberFile", memberFileVO);	
		mv.setViewName("member/memberPage");
		return mv;
	}
	
	@GetMapping("memberUpdate")
	public ModelAndView memberUpdate()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		mv.setViewName("member/memberUpdate");
		return mv;
	}
	
	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO, HttpSession session,MultipartFile [] files)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberService.memberUpdate(memberVO,files);
		if(result!=0) {			
			session.setAttribute("member", memberVO);
			mv.addObject("result", "success");
			mv.addObject("path", "../");
			mv.setViewName("common/result");	
		}else {				
			mv.addObject("result", "fail");
			mv.addObject("path", "member/memberPage");
			mv.setViewName("common/result");	
		}		
		
		return mv;
	}
	
	@GetMapping("fileDelete")
	public ModelAndView fileDelete(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		int result = memberService.fileDelete(memberVO);
		
		if(result!=0) {			
			session.setAttribute("member", memberVO);
			mv.addObject("result", "success");
			mv.addObject("path", "../");
			mv.setViewName("common/result");	
		}else {				
			mv.addObject("result", "fail");
			mv.addObject("path", "member/memberPage");
			mv.setViewName("common/result");	
		}		
		
		return mv;
	}
	
}
