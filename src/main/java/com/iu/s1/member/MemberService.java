package com.iu.s1.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public int memberJoin(MemberVO memberVO)throws Exception{
		int result=0;
		
		
		
		memberVO = memberRepository.save(memberVO);
		if(memberVO!=null) {
			result=1;
		}
		return result;
	}
	
	//검증 메서드 추가
		public boolean memberError(@Valid MemberVO memberVO, BindingResult bindingResult)throws Exception{
			boolean result=false; // false = 에러가 없음

			//1. 기본 제공 검증
//			result = bindingResult.hasErrors();
			if(bindingResult.hasErrors()) {
				result = true;
			}
			
			if(memberVO.getId()!=null) {
				
				//2. password 일치하는 검증
				if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
					bindingResult.rejectValue("pwCheck", "memberVO.pw.notSame");
					result=true;
				}
				
				//3. id 중복 검증
				if(memberRepository.findById(memberVO.getId())!=null) {
					if(memberRepository.existsById(memberVO.getId())) {
						bindingResult.rejectValue("id", "memberVO.id.same");
						result=true;
					}
				}else {
					result = true;
				}
			}else {
				result = true;
			}
			
			return result;
		}

	
}
