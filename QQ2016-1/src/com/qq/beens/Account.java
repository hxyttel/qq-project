package com.qq.beens;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	//QQ�˺�
	private int QQid;
	
	//�ǳ�
	private String infoname ;
	//����
    private String password ;
	//ͷ��
	private String photo ;
	//����
	private int age ;
	//�Ա�
	private String sex  ;
	//״̬
	private String stement ;
	//����
	private String constellation ;
	//Ѫ��
	private String  boold ;
	//����
	private String  mzhome ;
	//ip��ַ
	private String ipadress ;
	//ͨ�Ŷ˿�
	 private int port ;
	//����
	private String interst ;
	//����ǩ��
	private String prename ;
	//���������ϵ
	private String groupID;
		
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public int getQQid() {
		return QQid;
	}
	public void setQQid(int qQid) {
		QQid = qQid;
	}
	public String getInfoname() {
		return infoname;
	}
	public void setInfoname(String infoname) {
		this.infoname = infoname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStement() {
		return stement;
	}
	public void setStement(String stement) {
		this.stement = stement;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getBoold() {
		return boold;
	}
	public void setBoold(String boold) {
		this.boold = boold;
	}
	public String getMzhome() {
		return mzhome;
	}
	public void setMzhome(String mzhome) {
		this.mzhome = mzhome;
	}
	public String getIpadress() {
		return ipadress;
	}
	public void setIpadress(String ipadress) {
		this.ipadress = ipadress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getInterst() {
		return interst;
	}
	public void setInterst(String interst) {
		this.interst = interst;
	}
	public String getPrename() {
		return prename;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
		
	}

