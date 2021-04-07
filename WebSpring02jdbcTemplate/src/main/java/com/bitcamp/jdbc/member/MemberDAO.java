package com.bitcamp.jdbc.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bitcamp.jdbc.Constants;

public class MemberDAO {
	public JdbcTemplate template;
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public MemberDAO(){
		template = Constants.jdbcTemplate;
		
	}
	
	// template 는 vo에 알아서 담아준다!
	// 만약, sql문으로 select를 했을 때, 값이 null이 나오면 오류가 발생하기 때문에
	// 애초에 데이터가 있을 때 실행되도록 한다.
	   // 1. 데이터가 있는지 먼저 확인
	   // 2. 데이터를 호출 or null
	
	// queryForObject 의 사용법
	// String sql, RowMapper를 사용하는데
	// sql select 명령문을 실행한 결과의 필드명 (MemberVO의 필드명)이 같으면 
	// RowMapper<MemberVO> ... <MemberVO> == <T>에 즉, (VO)에 자동으로 값을 넣어준다.
	// 뒤에 Object....args 는 .... " ? " 물음표에 들어갈 갯수만큼 적어주면 된다.
	public MemberVO loginCheck(MemberVO vo) {
		//찾는 아이디가 있는지 없는지 확인한다.
										// useridCount 이게 RowMapper에 들어갈 변수명
		String sql = "select count(userid) useridCount from register where userid=? and userpwd=?";
		// 					select의 필드명과 vo의 변수명이 같으면  set 해준다
		MemberVO vo2 = template.queryForObject(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class), vo.getUserid(), vo.getUserpwd());
		
		if(vo2.getUseridCount()>0) { // 아이디가 존재한다.
			//아이디와 이름을 선택해서 vo를 return한다
			sql = "select userid, username from register where userid=? and userpwd=?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class),vo.getUserid(),vo.getUserpwd());
			
		} else{//아이디가 존재하지 않는다.
			return null;
		}
	}
	
}
