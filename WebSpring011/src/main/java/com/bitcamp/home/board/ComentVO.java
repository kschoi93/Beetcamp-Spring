package com.bitcamp.home.board;

public class ComentVO {
	private int coment_no;
	private String coment_content;
	private String writedate;
	private String userid;
	private int board_no;
	public ComentVO() {}
	public ComentVO(int coment_no, String userid, String writedate, String coment_content) {
		this.coment_content=coment_content;
		this.coment_no=coment_no;
		this.userid=userid;
		this.writedate=writedate;
	}
	
	public int getComent_no() {
		return coment_no;
	}
	public void setComent_no(int coment_no) {
		this.coment_no = coment_no;
	}
	public String getComent_content() {
		return coment_content;
	}
	public void setComent_content(String coment_content) {
		this.coment_content = coment_content;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	
	
}
