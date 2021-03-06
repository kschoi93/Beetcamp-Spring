package com.bitcamp.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bitcamp.myapp.dao.BoardDAO;
import com.bitcamp.myapp.vo.BoardVO;

@Service
public class BoardServiceImp implements BoardService{
	@Inject
	BoardDAO dao;

	@Override
	public List<BoardVO> allList() {
		return dao.allList();
	}

	@Override
	public BoardVO boardSelect(int no) {
		return dao.boardSelect(no);
	}

	@Override
	public int boardInsert(BoardVO vo) {
		return dao.boardInsert(vo);
	}

	@Override
	public List<BoardVO> searchList(BoardVO vo) {
		
		return dao.searchList(vo);
	}

	@Override
	public BoardVO boardEditSelect(int no) {
		// TODO Auto-generated method stub
		return dao.boardEditSelect(no);
	}

	@Override
	public int boardEdit(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.boardEdit(vo);
	}

	@Override
	public int boardMultiDelete(int[] noList) {
		// TODO Auto-generated method stub
		return dao.boardMultiDelete(noList);
	}

	@Override
	public int boardDel(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.boardDel(vo);
	}

	


	
}
