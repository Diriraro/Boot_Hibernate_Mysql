package com.iu.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class QnaService {
	@Autowired
	private QnaRepository qnaRepository;
	
	public Page<QnaVO> boardList(Pager pager)throws Exception{
		pager.makeRow();
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(), Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> page=null;
		if(pager.getKind().equals("title")) {
			page=qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			page=qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}else {
			page=qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		return page;
	}
	
//	public Page<QnaVO> boardList(Pageable pageable,String kind, String search)throws Exception{
//		if(kind.equals("title")) {
//			return qnaRepository.findByTitleContaining(search, pageable);
//		}else if (kind.equals("writer")) {
//			return qnaRepository.findByWriterContaining(search, pageable);
//		}else {
//			return qnaRepository.findByContentsContaining(search, pageable);
//		}
//		
//	}
	
	public QnaVO boardWrite(QnaVO qnaVO)throws Exception{
		//원본글 
		//ref = 자기자신의 글번호
		//step, depth 0
		qnaVO.setRef(0L);
		qnaVO.setDepth(0L);
		qnaVO.setStep(0L);
		qnaVO=qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO);
	}
	
}
