package com.qq.beens;

//建立消息表的been
public class information {
	//ID
	private int id;
	
	//发送号码
	private String sendnum;
	
	//接收号码
	private String resevicenum;
	
	//分组id
	private String groupid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSendnum() {
		return sendnum;
	}
	public void setSendnum(String sendnum) {
		this.sendnum = sendnum;
	}
	public String getResevicenum() {
		return resevicenum;
	}
	public void setResevicenum(String resevicenum) {
		this.resevicenum = resevicenum;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
}
