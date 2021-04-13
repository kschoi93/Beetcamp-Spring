package com.bitcamp.home.board;

import java.util.List;

public interface BoardDAOImp {
	//글목록 불러오기
	public List<BoardVO> allList();
	
	//글 등록하기
	public int boardInsert(BoardVO vo);
	
	public BoardVO boardSelect(int no);
	
	public int boardUpdate(int no, String subject, String content, String userid);
	
	public int boardDelete(int no, String userid);
}
