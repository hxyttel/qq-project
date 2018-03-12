package com.qq.beens;

//建立了朋友表
public class frend {
	//ID 
	private  int     ID ;
	
	//本人号码
	private String  uesrnum;
	
	//朋友号码
	private String  friendnum;
	
	//分组ID
	private String  groupID;
	
	//黑名单
	private  int 	blacklist;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUesrnum() {
		return uesrnum;
	}
	public void setUesrnum(String uesrnum) {
		this.uesrnum = uesrnum;
	}
	public String getFriendnum() {
		return friendnum;
	}
	public void setFriendnum(String friendnum) {
		this.friendnum = friendnum;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public int getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}
	
}
