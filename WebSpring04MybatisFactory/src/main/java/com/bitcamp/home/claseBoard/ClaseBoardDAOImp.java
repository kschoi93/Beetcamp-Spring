package com.bitcamp.home.claseBoard;

import java.util.List;

public interface ClaseBoardDAOImp {
	public int claseInsert(ClaseBoardDTO dto);
	public List<ClaseBoardDTO> claseAllRecord();
	public ClaseBoardDTO claseSelect(int no);
	public int hitCount(int no);
	public ClaseBoardDTO oriInfo(int no);//원글의 ref, step, lvl를 선택하는 메소드
	public int lvlCount(ClaseBoardDTO dto);//lvl증가
	public int claseDataInsert(ClaseBoardDTO dto);
	public int getTotalRecord();//총 레코드수
	public int claseUpdate(ClaseBoardDTO dto);//답변형글 수정
	
	public ClaseBoardDTO getStep(int no);//step, userid가져오기
	
	public int claseDelete(int no);//원글삭제
	public int claseDeleteUpdate(int no,String userid);//답글삭제
	
	
	
//	public ClaseBoardDTO getLagLead(int no);//이전글 다음글 확인
//	public String getLagLeadName(int no);
	
	public PrevNextVO lagLeadSelect(int no);//이전글 다음글
	
}
