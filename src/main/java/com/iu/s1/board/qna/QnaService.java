package com.iu.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QnaService {
	@Autowired
	private QnaRepository qnaRepository;
	
	public Page<QnaVO> boardList(Pageable pageable)throws Exception{
		return qnaRepository.findAll(pageable);
	}
	
	public QnaVO boardWrite(QnaVO qnaVO)throws Exception{
		//원본글 
		//ref = 자기자신의 글번호
		//step, depth 0
		qnaVO=qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO);
	}
	
}
