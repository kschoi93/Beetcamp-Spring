package com.bitcamp.myapp.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bitcamp.myapp.dao.MemberDAO;
import com.bitcamp.myapp.vo.MemberVO;

//service 등록
@Service
public class MemberServiceImp implements MemberService{
	//DAO가 객체로 만들어져서 들어간다
	@Inject
	MemberDAO memberDAO;
	
	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return memberDAO.loginCheck(vo);
	}
}
