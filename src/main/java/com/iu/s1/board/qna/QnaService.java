package com.iu.s1.board.qna;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackOn = Exception.class)
public class QnaService {
	@Autowired
	private QnaRepository qnaRepository;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileManager fileManager;
	@Value("${board.qna}")
	private String filepath;
	
	
	public boolean boardDelete(QnaVO qnaVO)throws Exception{
		qnaRepository.deleteById(qnaVO.getNum());
		return qnaRepository.existsById(qnaVO.getNum());
	}
	
	public QnaVO boardSelect(QnaVO qnaVO)throws Exception{
		
		 //qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		 qnaVO = qnaRepository.qnaSelect(qnaVO.getNum());
		 qnaVO.setHit(qnaVO.getHit()+1);
		 return qnaRepository.save(qnaVO);
	}
	
	public Page<QnaVO> boardList(Pager pager)throws Exception{
		pager.makeRow();
		Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(), Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> page=null;
		if(pager.getKind().equals("title")) {
			page = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			page = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}else {
			page = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		pager.makePage(page.getTotalPages());
		
		return page;
	}
	
	public QnaVO boardWrite(QnaVO qnaVO, MultipartFile [] files)throws Exception{
		//원본글 
		//ref = 자기자신의 글번호
		//step, depth 0
		
		List<QnaFileVO> list = new ArrayList<>();
		
		//저장할 폴더 경로
		File file =  filePathGenerator.getUserResourceLoader(filepath);
		
		//HDD에 저장
		for(MultipartFile multipartFile:files) {
			QnaFileVO qnaFileVO = new QnaFileVO();
			String fileName=fileManager.saveFileCopy(multipartFile, file);
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(multipartFile.getOriginalFilename());
			qnaFileVO.setQnaVO(qnaVO);
			list.add(qnaFileVO);
		}
		
		qnaVO.setBoadFiles(list);
		
		qnaVO=qnaRepository.save(qnaVO);
		
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO);
	}
	
	public void boardUpdate(QnaVO qnaVO, MultipartFile [] files)throws Exception{
		qnaRepository.qnaUpdate(qnaVO.getTitle(), qnaVO.getContents(), qnaVO.getNum());
	}
	
	public QnaVO boardReply(QnaVO qnaVO)throws Exception{
		QnaVO childVO = new QnaVO();
		childVO.setTitle(qnaVO.getTitle());
		childVO.setWriter(qnaVO.getWriter());
		childVO.setContents(qnaVO.getContents());
		
		// update
		// ref 부모의 ref 같고 step이 부모의 step보다 큰것들 
		// step 1 씩 증가
		
		//1. 부모의 정보 조회
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreaterThan(qnaVO.getRef(), qnaVO.getStep());
		for(QnaVO q: ar) {
			q.setStep(q.getStep()+1);
		}
		
		qnaRepository.saveAll(ar);
		
		
		// 자기자신의 ref 는 부모의 ref
		// 자기자신의 step 는 부모의 step+1
		// 자기자신의 depth 는 부모의 depth+1
		childVO.setRef(qnaVO.getRef());
		childVO.setStep(qnaVO.getStep()+1);
		childVO.setDepth(qnaVO.getDepth()+1);
		qnaRepository.save(childVO);
		
		return childVO;
	}

}
