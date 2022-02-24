package com.example.storeweb.dto;

public class Pagination {

	private int pageNo;
	private int size;
	private int pageSize;
	private int totalPages;
	
	public Pagination(int pageNo, int size, int totalPages) {
		this.pageNo = pageNo;
		this.size = size;
		this.totalPages = totalPages;
		this.pageSize = 5;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public int getSize() {
		return size;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getTotalBlocks() {
		return (int) Math.ceil(((double) totalPages)/pageSize);
	}
	public int getCurrentBlock() {
		return (int) Math.ceil(((double) pageNo)/pageSize);
	}
	public int getBeginPage() {
		return (getCurrentBlock() - 1)*pageSize + 1; 
	}
	public int getEndPage() {
		return (getCurrentBlock() == getTotalBlocks()) ? totalPages : getCurrentBlock()*pageSize;
	}
	public boolean isFirstBlock() {
		return getCurrentBlock() <= 1;
	}
	public boolean isLastBlock() {
		return getCurrentBlock() >= getTotalBlocks();
	}
	public int getPreviousBlockPage() {
		return (getCurrentBlock() - 1)*pageSize;
	}
	public int getNextBlockPage() {
		return getCurrentBlock()*pageNo + 1;
	}
}
