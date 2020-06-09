package com.iu.s1.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVO, String> {
	//내장 메서드
	//save() : insert
	//쿼리문 대신 메서드가 제공
	//메서드를 통해서 쿼리문이 자동으로 생성
	// 기본 제공 메서드
	// S save(T)								: insert, update
	// <S extends T> S save(Interable<S>)		: 다중 insert, update
	// void deleteById(ID)						: PK를 통한 삭제
	// void delete(T)							: 주어진 Entity delete
	// void deleteAll(Interable<? extends T>)	: 주어진 모든 Entity 삭제
	// void deleteAll()							: 모든 Entity 삭제
	// List<T> findAll()						: 모든 Entity 조회
}
