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
	
	@Test
	void idCheck() {
		MemberVO memberVO = new MemberVO();
//		boolean check = memberRepository.exists(example);
//		System.out.println(check);
	}
	
//	@Test
	void insertTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("suji");
		memberVO.setPw("suji");
		memberVO.setName("suji");
		memberVO.setPhone("01999999999");
		memberVO.setEmail("suji@suji.suji");
		
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setId("hani");
		memberVO2.setPw("hani");
		memberVO2.setName("hani");
		memberVO2.setPhone("99999999999");
		memberVO2.setEmail("hani@hani.hani");
		
		
		List<MemberVO> memberVOs = new ArrayList<>();
		memberVOs.add(memberVO);
		memberVOs.add(memberVO2);
		
		memberVOs=memberRepository.saveAll(memberVOs);
		assertNotNull(memberVOs);
	}

//	@Test
	void updateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu");
		memberVO.setEmail("iu@iuiu.iuiu");
		memberVO=memberRepository.save(memberVO);
		assertNotNull(memberVO);
	}
//	@Test
	void deleteTest() {
		MemberVO memberVO = new MemberVO();
		MemberVO memberVO2 = new MemberVO();
//		memberVO.setId("iu");
//		memberRepository.delete(memberVO);
		List<MemberVO> memberVOs = new ArrayList<>();
		memberVO.setId("hani");
		memberVOs.add(memberVO);
		memberVO2.setId("suji");
		memberVOs.add(memberVO2);
		memberRepository.deleteInBatch(memberVOs);
		
	}
}
