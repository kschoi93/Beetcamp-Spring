package com.bitcamp.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	public JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	//메소드 자동실행하는 어노테이션 Servlet-Context.xml에 있는 template 객체를
	// jdbcTemplate와 constants.jdbcTemplate에 셋팅한다.
	@Autowired
	public void setJdbcTemplate(JdbcTemplate template) {
		this.jdbcTemplate = template;
		Constants.jdbcTemplate = template;
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {


		return "home";
	}

}
