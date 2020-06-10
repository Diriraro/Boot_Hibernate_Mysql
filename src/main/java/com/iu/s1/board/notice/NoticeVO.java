package com.iu.s1.board.notice;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.iu.s1.board.BoardVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="notice")
@DynamicUpdate(value = true)
public class NoticeVO extends BoardVO{

	//one < 자기 자신 / many < 자기를 참조하는 테이블
	//fetch = FetchType.EAGER >> noticeVO를 조회하면 noticeFileVO까지 같이 조회
	//					.LAZY >> noticeVO를 조회하면 noticeVO만 조회/noticeFileVO를 조회하려고 할 때 그때 조회
	@OneToMany(mappedBy = "noticeVO", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private List<NoticeFileVO> noticeFileVOs;
	
}
