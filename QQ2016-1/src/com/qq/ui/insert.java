package com.qq.ui;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


	

@SuppressWarnings("serial")
class insert extends JFrame implements Runnable {
	JLabel insertPhoto;
	public insert(){
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		insertPhoto = new JLabel(new ImageIcon("images/q1.png"));
		add(insertPhoto);
		setSize(500,375);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			//切换窗口
			dispose();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		insert i = new insert();
		Thread t = new Thread(i);
		t.start();
		try {
			t.join();
			new QQlogin();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}


}
