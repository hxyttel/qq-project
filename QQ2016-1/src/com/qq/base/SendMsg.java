package com.qq.base;

import java.io.Serializable;

import javax.swing.text.StyledDocument;

import com.qq.beens.Account;

@SuppressWarnings("serial")
public class SendMsg implements Serializable{
	//设置发送消息的类型
	public int Cmd;
	//发送人的信息
	public Account myIfo;
	//接收人的信息
	public Account frendIfo;
	//发送的内容
	public StyledDocument doc;
	//
	public  byte b[];
	
	public String fielNaem;
}
