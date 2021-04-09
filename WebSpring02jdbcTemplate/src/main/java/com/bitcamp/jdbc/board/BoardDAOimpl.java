package com.bitcamp.jdbc.board;

import java.util.List;

public interface BoardDAOimpl {
	//글전체 선택
	public List<BoardVO> boardAllRecord();
	//글선택(1레코드)
	public BoardVO boardOneRecord(int no);
	//글 추가
	public int boardInsertRecord(BoardVO vo);
	//글수정
	public int boardUpdateRecord(BoardVO vo);//번호,id
	//글삭제
	public int boardDeleteRecord(int no, String userid);//번호,id
	//조회수 증가
	public void boardHit(int no);
}
