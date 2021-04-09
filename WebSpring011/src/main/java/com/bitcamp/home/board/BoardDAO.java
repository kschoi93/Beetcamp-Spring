package com.bitcamp.home.board;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.home.DBCPConnection;
import com.bitcamp.home.member.MemberDAOimpl;
import com.bitcamp.home.member.MemberVO;

public class BoardDAO extends DBCPConnection implements BoardDAOimpl {

	@Override
	public int boardInsert(BoardVO vo) {
		int result=0;
		try {
			getConn();
			sql="insert into board(no, subject, content, userid, ip) "
					+ " values(boardsq.nextval, ?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getUserid());
			pstmt.setString(4, vo.getIp());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("글쓰기 에러.....");
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return result;
	}

	@Override
	public List<BoardVO> boardAllRecord() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConn();
			sql = "select no, subject, userid, hit, writedate from board order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setUserid(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setWritedate(rs.getString(5));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			System.out.println("레코드 선택 에러 ...... ");
			e.printStackTrace();
		}finally{
			getClose();
		}
		
		return list;
	}

	@Override
	public void boardSelect(BoardVO vo) {
		try {
			getConn();
			sql="select no, subject, content, userid, hit, writedate from board where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getNo());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setUserid(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setWritedate(rs.getString(6));
			}
			
		}catch(Exception e) {
			System.out.println("레코드 (1개) 선택 에러......");
			e.printStackTrace();
		}finally {
			getClose();
		}
		
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update board set subject=?, content=? where userid=? and no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getUserid());
			pstmt.setInt(4, vo.getNo());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("글 수정 에러......");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}

	@Override
	public int boardDelete(BoardVO vo) {
		int result=0;
		try {
			getConn();
			sql="delete from board where no=? and userid=?";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,vo.getNo());
			pstmt.setString(2, vo.getUserid());
			
			result= pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("글 삭제 에러......");
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}

	@Override
	public void hitcount(int no) {
		try {
			getConn();
			
			sql="update board set hit = hit + 1 where no=?";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("조회수 증가 에러......");
			e.printStackTrace();
		}finally {
			getClose();
		}
		
	}


}
