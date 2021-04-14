package com.bitcamp.home.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardReplyController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/boardReplyInsert")
	@ResponseBody
	public String boardReplyInsert(BoardReplyVO vo, HttpSession session, HttpServletRequest req) {
		vo.setUserid((String)session.getAttribute("logId"));
		vo.setIp(req.getRemoteAddr());
		System.out.println(vo.getUserid()+", "+vo.getIp()+", "+vo.getContent()+", "+vo.getNo());
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		
		
		int result = dao.replyInsert(vo);
		if(result>0) {
			return "success";
		}else {
			return "error";
		}
	}
	
	@RequestMapping("/boardReplyList")
	@ResponseBody
	public List<BoardReplyVO> boardReplyList(BoardReplyVO vo,HttpServletRequest req){
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		List<BoardReplyVO> lst = dao.replyList(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",lst);
		return lst;
		
	}
	
	@RequestMapping("/replyEdit")
	@ResponseBody
	public String replyEdit(BoardReplyVO vo) {
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		int result = dao.replyEdit(vo);
		if(result>0) {
			return "수정성공";
		} else {
			return "수정실패";
		}
	}
	
	@RequestMapping("/replyDelete")
	@ResponseBody
	public String replyDelete(BoardReplyVO vo) {
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		int result = dao.replyDelete(vo.getNum());
		if(result>0) {
			return "삭제 성공입니다.";
		} else {
			return "삭제 실패 ㅠㅠㅠㅠㅠ";
		}
	}
}
