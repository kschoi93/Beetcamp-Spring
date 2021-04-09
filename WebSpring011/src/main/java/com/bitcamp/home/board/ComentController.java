package com.bitcamp.home.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComentController {
	@RequestMapping("")
	public String comentAllSelect() {
		return "";
	}
	
	@RequestMapping(value="/comentInsert",method = RequestMethod.GET,produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String comentInsert(ComentVO vo,HttpServletRequest req) {
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		ComentDAO dao = new ComentDAO();
		int result = dao.comentInsert(vo);
		if(result>0) {
			return "댓글 달기 성공 번호";
		}
		return "댓글달기 실패 .....";
	}
	
	@RequestMapping("/comentAllSelect")
	@ResponseBody
	public List<ComentVO> comentAllSelect(ComentVO vo,HttpServletRequest req) {
		ComentDAO dao = new ComentDAO();
		
		return dao.comentAllSelect(vo);
		
	}
}
