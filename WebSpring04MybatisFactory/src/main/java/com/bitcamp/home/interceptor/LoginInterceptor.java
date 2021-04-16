package com.bitcamp.home.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.ognl.EnumerationIterator;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.net.httpserver.HttpContext;

public class LoginInterceptor extends HandlerInterceptorAdapter  {
	
	//HandlerInterceptorAdapter를 상속받아 Interceptor 클래스를 생성한 후 메소드를 오버라이딩한다.
	//컨트롤러가 호출되기 전에 먼저 실행되는 메소드
	@Override
	public boolean preHandle(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object handler) throws Exception {
		//로그인 여부를 확인하고 로그인 상태인 경우 호출된 컨트롤러가 실행되고,
		// 로그인 안된 경우 로그인 페이지(컨트롤러)이동을 구현한다.

		
		//1. 로그인 정보 구하기
		String userid= (String)req.getSession().getAttribute("logId");
		
		if(userid==null || userid.equals("")) {//로그인 안된경우
			
			if(req.getHeader("AJAX")==null) {
				//로그인으로 이동 
				res.sendRedirect(req.getContextPath()+"/loginForm");
				return false;//접속한 컨트롤러의 실행을 중지하고 새로 접속하는 곳을 지정한다.
			} else if(req.getHeader("AJAX").equals("true")){		//2. ajax로 사용시 확인	
				res.sendError(400);
				return false;
			}
			
		}
		
		return true;//true가 리턴되면 접속한 컨트롤러를 계속 실행한다.
		
	}
	//컨트롤러가 실행 후 view 페이지로 이동하기전에 호출되는 메소드
	@Override
	public void postHandle(
			HttpServletRequest req, 
			HttpServletResponse res,
			Object handler, 
			@Nullable ModelAndView modelAndView) throws Exception {
		
	}
	
	//컨트롤러가 실행 후 호출되는 메소드
	@Override
	public void afterCompletion(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object handler, 
			@Nullable Exception ex) throws Exception{
		
	}
	
}
