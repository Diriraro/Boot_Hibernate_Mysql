package com.iu.s1.board.qna;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	@Query("select q from QnaVO q where q.num=:num")
	QnaVO qnaSelect(Long num);
	
//	@Query("select q.title, q.writer from QnaVO q where ")
//	Object[] qnaSelect2(Long num);
	
	//Update
	@Transactional
	@Modifying
	@Query(value = "update QnaVO q set q.title=?1, q.contents=?2 where q.num=?3")
	void qnaUpdate(String title,String contents, Long num);
	
	List<QnaVO> findByRefAndStepGreaterThan(long ref, long step);
	
	//title 검색
	Page<QnaVO> findByTitleContaining(String search, Pageable pageable);
	
	//contents 검색
	Page<QnaVO> findByContentsContaining(String search, Pageable pageable);
	
	//writer 검색
	Page<QnaVO> findByWriterContaining(String search, Pageable pageable);
}
