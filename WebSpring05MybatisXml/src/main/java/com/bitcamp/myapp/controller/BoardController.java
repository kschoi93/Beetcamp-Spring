package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.dao.BoardDAO;
import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;
import com.bitcamp.myapp.vo.MemberVO;

@Controller
public class BoardController {
	@Inject
	BoardService boardService;
	
	@RequestMapping("/boardList")
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",boardService.boardAllRecord());
		mav.setViewName("board/boardList");
		return mav;
	}
	
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/boardWriteOk",method = RequestMethod.POST)
	public ModelAndView boardWriteOk(HttpSession session, BoardVO vo, HttpServletRequest req) {
		
		vo.setIp(req.getRemoteAddr());
//		MemberVO vo2 = (MemberVO)session.getAttribute("logVo");
//		vo.setUserid(vo2.getUserid());
		vo.setUserid(((MemberVO)session.getAttribute("logVo")).getUserid());
		
		
		
		ModelAndView mav = new ModelAndView();
		vo.setIp(req.getRemoteAddr());
		
		System.out.println("userid="+vo.getUserid());
		System.out.println("ip="+vo.getIp());
		System.out.println("subject="+vo.getSubject());
		System.out.println("content="+vo.getContent());
		
		int result= boardService.boardInsert(vo);
		if(result>0) {
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("redirect:boardWrite");
		}
		return mav;
	}
	
	@RequestMapping("/boardView")
	public ModelAndView boardView(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",boardService.boardSelect(no));
		mav.setViewName("board/boardView");
		
		return mav;
	}
	
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",boardService.boardSelect(no));
		mav.setViewName("board/boardEdit");
		
		return mav;
	}
	
	@RequestMapping("/boardEditOk")
	public ModelAndView boardEditOk(BoardVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		vo.setUserid(((MemberVO)session.getAttribute("logVo")).getUserid());
		int result = 0;
		try {
			result = boardService.boardEditOk(vo);
		}catch(Exception e) {
			mav.addObject("no",vo.getNo());
			mav.setViewName("redirect:boardEdit");
		}
		if(result>0) {
			mav.addObject("no",vo.getNo());
			mav.setViewName("redirect:boardView");
		}
		
		return mav;
	}
}
