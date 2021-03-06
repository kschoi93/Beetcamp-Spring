package com.bitcamp.myapp.service;

import java.util.List;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> allList();
	public BoardVO boardSelect(int no);
	public int boardInsert(BoardVO vo);
	public List<BoardVO> searchList(BoardVO vo);
	public BoardVO boardEditSelect(int no);
	public int boardEdit(BoardVO vo);
	public int boardMultiDelete(int[] noList);
	public int boardDel(BoardVO vo);
}
