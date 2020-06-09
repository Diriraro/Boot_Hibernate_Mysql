package com.iu.s1.member;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberFileRepository memberFileRepository;
	@Autowired
	private FilePathGenerator pathGenerator; // 저장할 폴더의 경로
	@Autowired
	private FileManager fileManager; // 실제 파일을 저장

	@Value("${board.notice.filePath}")
	private String filePath; // application.properties에서 키 값을 받아와 value값을 집어넣음
	
	public int fileDelete(MemberVO memberVO)throws Exception{
		int result =1;
		long denum = memberVO.getMemberFileVO().getFileNum();
		memberFileRepository.deleteById(denum);
		
		return result;
	}

	public int memberUpdate(MemberVO memberVO, MultipartFile[] files) throws Exception {
		int result = 0;

		File file = pathGenerator.getUseClassPathResource(filePath);
		memberVO = memberRepository.save(memberVO);
		if (memberVO != null) {
			result = 1;
			for (MultipartFile multipartFile : files) {
				if (multipartFile.getSize() <= 0) {
					continue;
				}
				String fileName = fileManager.saveFileCopy(multipartFile, file);
				MemberFileVO vo = new MemberFileVO();

				vo.setFileName(fileName);
				vo.setOriName(multipartFile.getOriginalFilename());

				memberVO.setMemberFileVO(vo);
				vo.setMemberVO(memberVO);

				System.out.println(fileName);
			}
		}
		return result;
	}

	public MemberVO inSession(MemberVO memberVO) throws Exception {
		memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
		return memberVO;
	}

	public int memberLogin(MemberVO memberVO) throws Exception {
		int result = 0;

		memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());

		if (memberVO != null) {
			result = 1;
		}
		return result;
	}

	public int memberJoin(MemberVO memberVO, MultipartFile[] files) throws Exception {
		int result = 0;

		File file = pathGenerator.getUseClassPathResource(filePath);
		memberVO = memberRepository.save(memberVO);
		if (memberVO != null) {
			result = 1;
			for (MultipartFile multipartFile : files) {
				if (multipartFile.getSize() <= 0) {
					continue;
				}
				String fileName = fileManager.saveFileCopy(multipartFile, file);
				MemberFileVO vo = new MemberFileVO();

				vo.setFileName(fileName);
				vo.setOriName(multipartFile.getOriginalFilename());

				memberVO.setMemberFileVO(vo);
				vo.setMemberVO(memberVO);

				System.out.println(fileName);
			}
		}
		return result;
	}

	// 검증 메서드 추가
	public boolean memberError(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean result = false; // false = 에러가 없음

		// 1. 기본 제공 검증
//			result = bindingResult.hasErrors();
		if (bindingResult.hasErrors()) {
			result = true;
		}

		if (memberVO.getId() != null) {

			// 2. password 일치하는 검증
			if (!memberVO.getPw().equals(memberVO.getPwCheck())) {
				bindingResult.rejectValue("pwCheck", "memberVO.pw.notSame");
				result = true;
			}

			// 3. id 중복 검증
			if (memberRepository.findById(memberVO.getId()) != null) {
				if (memberRepository.existsById(memberVO.getId())) {
					bindingResult.rejectValue("id", "memberVO.id.same");
					result = true;
				}
			} else {
				result = true;
			}
		} else {
			result = true;
		}

		return result;
	}

}
