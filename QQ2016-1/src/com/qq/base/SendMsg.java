package com.qq.base;

import java.io.Serializable;

import javax.swing.text.StyledDocument;

import com.qq.beens.Account;

@SuppressWarnings("serial")
public class SendMsg implements Serializable{
	//���÷�����Ϣ������
	public int Cmd;
	//�����˵���Ϣ
	public Account myIfo;
	//�����˵���Ϣ
	public Account frendIfo;
	//���͵�����
	public StyledDocument doc;
	//
	public  byte b[];
	
	public String fielNaem;
}
