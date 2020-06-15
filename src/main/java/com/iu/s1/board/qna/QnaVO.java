package com.iu.s1.board.qna;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iu.s1.board.BoardVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "qna")

public class QnaVO extends BoardVO {
	
	
	private Long ref;
	private Long step;
	private Long depth;
	
	@OneToMany(mappedBy = "qnaVO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<QnaFileVO> boadFiles;
	
	public Long getRef() {
		if(ref==null) {
			this.ref=0L;
		}
		return ref;
	}
	public Long getStep() {
		if(step==null) {
			this.step=0L;
		}
		return ref;
	}

	public Long getDepth() {
		if(depth==null) {
			this.depth=0L;
		}
		return depth;
	}


}
