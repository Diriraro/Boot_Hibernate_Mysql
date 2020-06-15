package com.iu.s1.board.notice;

import java.util.List;
import java.util.Optional;

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
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	
	
	//Pageable pageable,String search
	public List<NoticeVO> noticeList(Pager pager) throws Exception{ 
	
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getSize(), Sort.Direction.DESC, "num");
		
		if(pager.getKind().equals("writer")) {
			pager.makeRow();
			pager.makePage(noticeRepository.countByWriterContaining(pager.getSearch()));
			
			return noticeRepository.findByWriterContaining(pager.getSearch(), pageable);

		}else if(pager.getKind().equals("contents")){
			pager.makeRow();
			pager.makePage(noticeRepository.countByContentsContaining(pager.getSearch()));
			
			return noticeRepository.findByContentsContaining(pager.getSearch(), pageable);

		}else {
			pager.makeRow();
			pager.makePage(noticeRepository.countByTitleContaining(pager.getSearch()));
			
			return noticeRepository.findByTitleContaining(pager.getSearch(), pageable);

		}
	}
	
	public int noticeWrite(NoticeVO noticeVO)throws Exception{
		int result=0;
		noticeVO = noticeRepository.save(noticeVO);
		if(noticeVO!=null) {
			result=1;
		}
		return result;
	}
	
	public NoticeVO	noticeSelect(NoticeVO noticeVO)throws Exception{
		Optional<NoticeVO> opt = noticeRepository.findById(noticeVO.getNum());
		noticeVO = opt.get();
		return noticeVO;
	}

}
