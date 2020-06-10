package com.iu.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
//	@Test
	void loginTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iuaaa");
		memberVO.setPw("iuaa");
		memberVO=memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
		System.out.println(memberVO.getMemberFileVO().getFileName());
		System.out.println(memberVO.getMemberFileVO().getOriName());
		assertNotNull(memberVO);
	}
	
//	@Test
	void idCheck() {
		MemberVO memberVO = new MemberVO();
//		boolean check = memberRepository.exists(example);
//		System.out.println(check);
	}
	
	//@Test
	void insertTest() {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId("iua");
		memberVO.setPw("iuaa");
		memberVO.setName("iuaa");
		memberVO.setPhone("0102345790");
		memberVO.setEmail("iu@ui.iu");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");
		
		memberVO.setMemberFileVO(memberFileVO);
		
		memberRepository.save(memberVO);
		//member insert 성공
		//memberFile 에 ID는 null
		
//		memberVO.setId("suji");
//		memberVO.setPw("suji");
//		memberVO.setName("suji");
//		memberVO.setPhone("01999999999");
//		memberVO.setEmail("suji@suji.suji");
//
//		
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setId("hani");
//		memberVO2.setPw("hani");
//		memberVO2.setName("hani");
//		memberVO2.setPhone("99999999999");
//		memberVO2.setEmail("hani@hani.hani");
//		
//		
//		List<MemberVO> memberVOs = new ArrayList<>();
//		memberVOs.add(memberVO);
//		memberVOs.add(memberVO2);
//		
//		memberVOs=memberRepository.saveAll(memberVOs);
//		assertNotNull(memberVOs);
	}
	
//	@Test
	void insertTest2() {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId("iu3");
		memberVO.setPw("iu3");
		memberVO.setName("iu3");
		memberVO.setPhone("3");
		memberVO.setEmail("iu@ui.iu");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		
		memberRepository.save(memberVO);
		//member insert 성공
		//memberFile 에 ID는 null
		
	}

	@Test
	void updateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu3");
		memberVO.setEmail("iu@sdadasd");
		
//		MemberFileVO memberFileVO = new MemberFileVO();
//		memberFileVO.setFileName("change2 File Name");
//		memberFileVO.setOriName("change2 Ori Name");
//		memberFileVO.setFileNum(2);
//		memberVO.setMemberFileVO(memberFileVO);
//		memberFileVO.setMemberVO(memberVO);
		
		memberVO=memberRepository.save(memberVO);
		
		assertNotNull(memberVO);
	}
//	@Test
	void deleteTest() {
		memberRepository.deleteById("iua");
		
//		MemberVO memberVO = new MemberVO();
//		MemberVO memberVO2 = new MemberVO();
////		memberVO.setId("iu");
////		memberRepository.delete(memberVO);
//		List<MemberVO> memberVOs = new ArrayList<>();
//		memberVO.setId("hani");
//		memberVOs.add(memberVO);
//		memberVO2.setId("suji");
//		memberVOs.add(memberVO2);
//		memberRepository.deleteInBatch(memberVOs);
//		
	}
}
