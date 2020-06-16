package com.iu.s1.member;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;
	
	@Value("")
	private String filePath;
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
	}

	public MemberVO memberJoin(MemberVO memberVO,MultipartFile [] files)throws Exception{
		File file = pathGenerator.getUseClassPathResource(filePath);
		
		for(MultipartFile multipartFile : files) {
			if(multipartFile.getSize()<=0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			MemberFileVO  memberFileVO = new MemberFileVO();
			memberFileVO.setFileNum(memberFileVO.getFileNum());
			memberFileVO.setFileName(fileName);
			memberFileVO.setOriName(multipartFile.getOriginalFilename());
			
			memberVO.setMemberFileVO(memberFileVO);
			memberFileVO.setMemberVO(memberVO);
			memberVO = memberRepository.save(memberVO);
		}
		
		return memberVO;
	}
	public MemberVO memberUpdate(MemberVO memberVO)throws Exception{
		return memberRepository.save(memberVO);
	}
	public boolean memberDelete(MemberVO memberVO)throws Exception{
		 memberRepository.deleteById(memberVO.getId());
		 return memberRepository.existsById(memberVO.getId());
	}
	
	public boolean memberError(MemberVO memberVO,BindingResult bindingResult,boolean check)throws Exception{
		boolean result = false; // 에러가 없음.
		boolean resultPw = false;
		boolean resultId = false;
		
		//1. 기본 제공 검증 
		//result = bindingResult.hasErrors();
		
		if(bindingResult.hasErrors()) {
			 result = true;
		}
		//2. pw 일치하는 검증
		if(memberVO.getPw().equals(memberVO.getPwCheck())) {
			resultPw = false;
			
		}else{
			bindingResult.rejectValue("pwCheck", "memberVO.pw.notSame");
			resultPw = true;
		}
		//3. ID 중복 검증
		if(!check) {
			if(memberRepository.existsById(memberVO.getId())) {
				bindingResult.rejectValue("id", "memberVO.id.same");
				resultId = true;
			}else {
				resultId = false;
			}
		}
		//DB에서 조회
		
		return result||resultPw||resultId;
	}
	
}
