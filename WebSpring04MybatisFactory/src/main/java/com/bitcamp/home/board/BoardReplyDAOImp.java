package com.bitcamp.home.board;

import java.util.List;

public interface BoardReplyDAOImp {
	//댓글추가
	public int replyInsert(BoardReplyVO vo) ;
	//댓글 전체목록 불러오기
	public List<BoardReplyVO> replyList(BoardReplyVO vo);
	
	//댓글 수정
	public int replyEdit(BoardReplyVO vo);
	
	//댓글 삭제
	public int replyDelete(int no);
}
