package com.iu.s1.util;

import org.springframework.stereotype.Component;

//=========================
// 사용
//
// pager.setPerPage(16);
// pager.makeRow();
// int totalNum = marketDAO.marketTotalNum(pager);
// pager.makePage(totalNum);
// return marketDAO.marketList(pager);
//
//=========================

@Component
public class Pager {

	private Long curPage;
	private Integer perPage = 10;
	
	private Long perBlock = 5L;
	private Long curBlock;
	
	private long startRow;
	private long lastRow;
	
	private Long totalPage;
	private Long totalBlock;
	
	private long startNum;
	private long lastNum;
	
	private String kind;
	private String search;

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public void setLastRow(long lastRow) {
		this.lastRow = lastRow;
	}
	
	// 현재 페이지에서 보여줘야하는 데이터 시작 Row / 끝 Row
	// 예) 1p = 1 ~ 10 / 2p = 11 ~ 20 
	public void makeRow() {
		this.startRow = this.getCurPage() - 1;
		//this.lastRow = this.getCurPage() * this.getPerPage();		// mysql에서는 사용할 필요 X
	}

	// 페이지 갯수 만들기
	public void makePage(long totalCount) {
		// totalCount로 totalPage 계산
		// 예시 ) 게시글 전체가 105개 / 페이지당 게시글 10개  = 10p,
		// 나누어 떨어지는게 아니면 1페이지 더 필요하므로 총 11p
		totalPage = totalCount/this.perPage;
		if(totalCount%this.perPage != 0) {
			totalPage++;
		}
		
		// totalPage로 totalBlock 계산
		// 블록은 한번에 보여줄 페이지탭의 갯수
		// 예시 ) 전체 총 11p / 노출 페이지 5p = 2block
		// 나누어 떨어지는게 아니면 1block 더 필요하므로 총 3block
		this.totalBlock = totalPage/perBlock;
		if(totalPage%perBlock != 0) {
			this.totalBlock++;
		}
		
		// 현재 블록 구하기
		// 예시 ) 현재 페이지가 11p 중 7p, / 블록당 5p
		// 현재페이지가 블록 갯수로 나눠떨어지지 않으면 + 1block 현재 2block
		this.curBlock = this.curPage/this.perBlock;
		if(this.curPage%this.perBlock != 0) {
			this.curBlock++;
		}
		
		// startNum = 5 * 1 + 1 = 6;
		// lastNum = 5 * 2 = 10;
		// 현재 노출되는 페이지 : 6 7 8 9 10
		// block에서 보여줄 pageNum : 6 ~ 10
		this.startNum = this.perBlock*(this.curBlock-1) + 1;
		this.lastNum = this.perBlock*this.curBlock;
		if(curBlock == this.totalBlock) {
			this.lastNum = this.totalPage;
		}
	}
	
	public long getStartRow() {
		return startRow;
	}


	public long getLastRow() {
		return lastRow;
	}

	public Long getCurPage() {
		if(this.curPage == null || this.curPage == 0)
			this.curPage = 1L;
		return curPage;
	}
	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
	public Integer getPerPage() {
		if(this.perPage == null || this.perPage == 0)
			this.setPerPage(10);
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalBlock() {
		return totalBlock;
	}

	public Long getCurBlock() {
		return curBlock;
	}

	public long getStartNum() {
		return startNum;
	}

	public long getLastNum() {
		return lastNum;
	}
	public String getKind() {
		if(this.kind==null)
			this.kind="title";
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSearch() {
		
		if(this.search == null)
			this.search = "";
		
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
}