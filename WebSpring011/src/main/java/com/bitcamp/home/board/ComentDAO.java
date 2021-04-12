package com.bitcamp.home.board;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.home.DBCPConnection;

public class ComentDAO extends DBCPConnection {
	//댓글 전체 선택
	public List<ComentVO> comentAllSelect(ComentVO vo) {
		List<ComentVO> lst = new ArrayList<ComentVO>();
		try {
			getConn();
			
			sql = "select coment_no, userid, writedate, coment_content from "
					+ " coment where board_no=? order by coment_no desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoard_no());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add(new ComentVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				
			}
			
		}catch(Exception e) {
			System.out.println("댓글 전체 선택 실패......");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return lst;
	}
	
	//댓글 추가
	public int comentInsert(ComentVO vo) {
		int result= 0;
		
		try {
			getConn();
			sql = "insert into coment(coment_no,board_no,userid,writedate,coment_content) "
					+ " values(comentsq.nextval,?,?,sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoard_no());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getComent_content());
			
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			System.out.println("댓글추가 에러 .....");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}
	//댓글 삭제
	public int comentDelete(int no) {
		int result=0;
		try {
			getConn();
			sql="delete from coment where coment_no=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
			
		}finally {
			getClose();
		}
		
		return result;
		
	}
	//댓글 수정
	public int comentEdit(ComentVO vo) {
		int result= 0;
		try {
			getConn();
			sql="update coment set coment_content=? where coment_no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getComent_content());
			pstmt.setInt(2, vo.getComent_no());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("댓글 수정 오류 .....");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}
}
