package com.bitcamp.myapp.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	//데이터를 보내는 3가지 방법 
	
	// 첫번째
	@RequestMapping(value="/aLink", method = RequestMethod.GET)
	public String test(HttpServletRequest req, Model model) { 
		//클라이언트에서 서버로 데이터 가져오기
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		//서버에 출력
		System.out.println(name + " , " + age);
		
		
		model.addAttribute("username",name);
		model.addAttribute("age", age);
		model.addAttribute("msg", "서버에서 클라이언트에게 데이터 보내기");
		return "mappingTest/aLinkView";
	}   
	
	
	
	// 두번째
	//@RequestParam : 클라이언트 측 데이터를 서버 request한다.
	@RequestMapping("/aLink2")
	public String test2(@RequestParam("name") String username, @RequestParam("age") int age, Model model) {
		System.out.println("aLink2-->"+username+":"+age);
		
		model.addAttribute("username",username);
		model.addAttribute("age",age);
		model.addAttribute("msg","RequestParam 방법으로 보낸 메시지다");
		
		return "mappingTest/aLinkView";
	}
	
	
	//세번째
	@RequestMapping("/aLink3")
	public String test3(TestVO vo, Model model) {
		System.out.println("TestVO--->"+ vo.getUsername() +", "+ vo.getAge());
		
		vo.setMsg("vo를 이용한 request테스트중.....");
		
		model.addAttribute("vo",vo);
		
		return "mappingTest/aLinkView2";
	}
	
	// 가장 많이 사용하는 방식이다.
	@RequestMapping("/aLink4")
	public ModelAndView test4(TestVO vo) {
		System.out.println("TestVO-------4>"+ vo.getUsername()+", "+vo.getAge());
		vo.setMsg("ModelAndView테스트 중......");
		
		//데이터와 뷰파일명을 한번에 가지는 클래스
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("mappingTest/aLinkView2");
		
		return mav;
	}
	
	//폼으로 이동하기
	@RequestMapping("/formData")
	public String formTest() {
		return "mappingTest/form";
	}
	
	@RequestMapping(value="/formDataOk", method=RequestMethod.POST)
	public ModelAndView formTestOk(TestVO vo) {
		System.out.println("formData----->"+ vo.getUsername() + ", " + vo.getAge());
		
		vo.setMsg("폼데이터 전송");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",vo);
		mav.setViewName("mappingTest/aLinkView2");
		
		return mav;
	}
}
