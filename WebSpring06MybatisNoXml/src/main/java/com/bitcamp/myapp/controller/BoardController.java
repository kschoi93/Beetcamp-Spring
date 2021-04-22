package com.bitcamp.myapp.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;

@Controller
public class BoardController {
	@Inject
	BoardService service;
	
	@RequestMapping("/list")
	public ModelAndView boardList(BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		
		/* SearchVO를 사용할때
		 * if(searchVO.getSearchWord()!=null){ 
		 * 	searchVO.setSearchWord("%"+searchVO.getSearchWord()+"%");
		 * }
		 */
		
		mav.addObject("list",service.allList());
			
		mav.setViewName("board/list");
		return mav;
	}
	
	@RequestMapping("/view")
	public String boardView(int no, Model model) {
		BoardVO vo = service.boardSelect(no);
		
		model.addAttribute("vo",vo);
		
		return "board/view";
	}
	
	@RequestMapping("/write")
	public String boardWrite() {
		return "board/write";
	}
	
	@RequestMapping("/writeOk")
	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		System.out.println(vo.getNo());
		System.out.println(vo.getContent());
		System.out.println(vo.getIp());
		System.out.println(vo.getSubject());
		System.out.println(vo.getUserid());
		if(service.boardInsert(vo)>0) {
			mav.setViewName("redirect:list");
		} else {
			mav.setViewName("redirect:write");
		}
		return mav;
	}
	
	@RequestMapping("/searchList")
	public ModelAndView searchList(BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",service.searchList(vo));
		List<BoardVO> vo2 =service.searchList(vo);
		System.out.println(vo2.size());
		
		mav.setViewName("board/list");
		return mav;
	}
	
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",service.boardEditSelect(no));
		mav.setViewName("board/edit");
		return mav;
		
	}
	
	@RequestMapping(value = "/editOk", method = RequestMethod.POST)
	public String boardEditOk(BoardVO vo, HttpSession session) {
		vo.setUserid((String)session.getAttribute("logId"));
		
		if(service.boardEdit(vo)>0) {
			return "redirect:view?no="+vo.getNo();
		}else {
			return "redirect:boardEdit?no="+vo.getNo();	
		}
		
	}
	
	@RequestMapping("/boardDel")
	public ModelAndView boardDel(BoardVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		vo.setUserid((String)session.getAttribute("logId"));
		
		if(service.boardDel(vo)>0) {
			mav.setViewName("redirect:list");
		} else {
			mav.addObject("no",vo.getNo());
			mav.setViewName("redirect:view");
		}
		
		return mav;
	}
	
	@RequestMapping("/multiDel")
	public ModelAndView boardMultiDel(BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		for(int no :vo.getNoList()) {
			System.out.println("no="+no);
		}
		int result = service.boardMultiDelete(vo.getNoList());
		System.out.println("삭제된 레코드 수 ="+ result);
		mav.setViewName("redirect:list");
		return mav;
	}
}
