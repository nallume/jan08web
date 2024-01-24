package com.poseidon.dto;

public class CommentDTO {
	private String ccomment, mname, mid, cdate, cip;
	private int bno, cno, clike, mno;
	
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getBno() {
		return bno;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getClike() {
		return clike;
	}

	public void setClike(int clike) {
		this.clike = clike;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getCcomment() {
		return ccomment;
	}

	public void setCcomment(String comment) {
		this.ccomment = comment;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}
}
