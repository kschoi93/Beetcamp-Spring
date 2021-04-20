package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.MemberService;
import com.bitcamp.myapp.vo.MemberVO;
@Controller
public class MemberController {
	@Inject
	MemberService memberService;

	/* 순서?
	 * 1. member Controller // 웹 브라우저의 요청을 전담하여 처리, service를 호출
	 * 2. MemberService // 비지니스 로직을 수행, 데이터베이스에 접근하는 DAO를 이용해서 결과값을 받아온다
	 * 3. MemberServiceImp 
	 * 4. MemberDAO // 데이터베이스에 접속하여 비즈니스 로직 실행에 필요한 쿼리를 호출
	 * 5. DB // 쿼리를 실행하고 결과값을 반환
	 *    + DTO(VO) : 각 계층이 데이터를 주고 받을 때 사용하는 객체
	 * 
	 * 6. DB
	 * 7. MemberDAO
	 * 8. MemberServiceImp
	 * 9. MemberService
	 * 10. member Controller
	 */
	
	//로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@RequestMapping(value = "/loginOk", method = RequestMethod.POST)
	public ModelAndView loginOk(MemberVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		//logVO가 null 로그인 실패, null이 아닐경우 vo에 userid, username을 저장해서 return
		MemberVO logVO = memberService.loginCheck(vo);
		if(logVO!=null) {//로그인 성공
			session.setAttribute("logVo", logVO);
			mav.setViewName("redirect:/");
		}else {//로그인 실패
			mav.setViewName("redirect:loginForm");
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "home";
	}
}
