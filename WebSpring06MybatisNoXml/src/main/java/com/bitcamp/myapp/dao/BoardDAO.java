package com.bitcamp.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardDAO {
	/*
 		searchVO를 통해 한번에 하는방법
 		json 형식으로 처리해준다.
 		@Select({"<script>",
 			" select no, subject, userid, hit, to_char(writedate,'MM-DD HH:MI') writedate ",
			" from board ",
			" <if test=\"searchWord!=null\">",
			" 		where ${searchKey} like '%${searchWord}%' ",
			" </if> ",
			" order by no desc ",
			" </script>"})
	
	 */
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
	
	@Select({"<script>",
			"select no, subject, content from board where no=#{no} ",
			"</script>"})
	public BoardVO boardEditSelect(int no);
	
	
	@Update("update board set subject=#{subject}, content=#{content} where userid=#{userid} and no=#{no}")
	public int boardEdit(BoardVO vo);
	
	@Delete("delete from board where no=#{no} and userid=#{userid}")
	public int boardDel(BoardVO vo);
	
	@Delete({" <script> ",
			" delete from board where no in ",
			" <foreach item=\"item\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">",
			" #{item} ",
			" </foreach>",
			" </script>"})
	public int boardMultiDelete(int[] noList);
}
