package com.iu.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iu.s1.board.qna.QnaVO;
import com.iu.s1.util.Pager;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public Page<NoticeVO> boardList(Pager pager)throws Exception{
		pager.makeRow();
		
		
		Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(), Sort.Direction.DESC, "num");

		Page<NoticeVO> page=null;
		if(pager.getKind().equals("title")) {
			page = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			page = noticeRepository.findByContentsContaining(pager.getSearch(), pageable);
		}else {
			page = noticeRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		pager.makePage(page.getTotalPages());
		
		
		return page;
	}
}
