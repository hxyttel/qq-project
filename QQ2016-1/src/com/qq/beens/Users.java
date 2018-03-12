package com.qq.beens;

import java.util.Date;

//建立了用户表的been
public class Users {
	//qq号码
	private String number;
	
	//用户名
	private String name;
	
	//性别
	private String sex;
	
	//年龄
	private int age;
	
	//生日
	private Date brithday;
	
	//星座
	private String constellation;
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	
	
}
