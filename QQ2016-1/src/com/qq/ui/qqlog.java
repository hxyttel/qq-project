package com.qq.ui;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings({ "unused", "serial" })
public class qqlog extends JFrame{
	JLabel qqlog;
	public qqlog(){
		qqlog = new JLabel( new ImageIcon("images/qq.gif"));
		this.add(qqlog);
		
		setSize(285,626);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		//���ô��ڹرգ����������ϰѳ���رգ������һ��
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new qqlog();
	}
	
}
