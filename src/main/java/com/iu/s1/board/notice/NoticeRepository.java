package com.iu.s1.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {
	
	public int countByTitleContaining(String search);
	public int countByContentsContaining(String search);
	public int countByWriterContaining(String search);
	
	public List<NoticeVO> findByContentsContaining(String search, Pageable pageable);
	public List<NoticeVO> findByWriterContaining(String search, Pageable pageable);
	public List<NoticeVO> findByTitleContaining(String search, Pageable pageable);
	
	//select * from notice where num>0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long num);
	
	//select * from notice where num between 6 and 10
	public List<NoticeVO> findByNumBetween(long n1, long n2);
	
	//select * from notice where title like ?? order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String search);
}
