package com.qq.beens;

//���������ѱ�
public class frend {
	//ID 
	private  int     ID ;
	
	//���˺���
	private String  uesrnum;
	
	//���Ѻ���
	private String  friendnum;
	
	//����ID
	private String  groupID;
	
	//������
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
