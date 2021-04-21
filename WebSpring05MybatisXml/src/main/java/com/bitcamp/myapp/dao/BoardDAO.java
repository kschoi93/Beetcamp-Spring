package com.bitcamp.myapp.dao;

import java.util.List;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardDAO {
	public List<BoardVO> boardAllRecord();
	public int boardInsert(BoardVO vo);
	public BoardVO boardSelect(int no);
	public int boardEditOk(BoardVO vo);
	public int boardDelete(int no, String userid);
}
