package com.bitcamp.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardDAO {
	@Select("select no, subject, userid, hit, to_char(writedate,'MM-DD HH:MI') writedate "
			+" from board order by no desc")
	public List<BoardVO> allList();
	
	@Select("select no, subject, content, userid, hit, writedate from board where no=#{no}")
	public BoardVO boardSelect(int no);
	
	@Insert("insert into board(no, subject, content, userid, ip, writedate, hit) "
			+ " values(boardsq.nextval, #{subject}, #{content}, #{userid}, #{ip}, sysdate, 0)")
	public int boardInsert(BoardVO vo);
	
	@Select("select no, subject, userid, hit, to_char(writedate,'MM-DD HH:MI') writedate "
			+" from board where ${searchKey} like '%${searchTxt}%' order by no desc")
	public List<BoardVO> searchList(BoardVO vo);
}
