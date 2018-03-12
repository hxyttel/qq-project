package com.qq.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.qq.beens.Account;

@SuppressWarnings("serial")
public class LOGINUI extends JFrame implements ActionListener{
	 static Account myifo;
	JLabel bg,logqq;
	JLabel labnorth;
	JButton btMin,btClose,btMax,btsure,labHphoto;
	@SuppressWarnings("static-access")
	public LOGINUI(Account myifo){
		this.myifo=myifo;
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		bg = new JLabel(new ImageIcon("images/login1.jpg"));
		bg.setLayout(null);
		add(bg);
		
		labnorth = new JLabel(new ImageIcon("images/qqname.gif"));
		labHphoto = new JButton(new ImageIcon(myifo.getPhoto()));
		labHphoto.setMargin(new Insets(0,0,0,0));
		
		labnorth.setBounds(0, 0, 430, 183);
		bg.add(labnorth);
		labHphoto.setBounds(170,190,80,80);
		bg.add(labHphoto);
		
		btsure = new JButton("确定");
		btsure.setBounds(150,275,120,30);
		btsure.setBackground(Color.blue);
		bg.add(btsure);
		
		btMin = new JButton();
		btMin.setBackground(Color.WHITE);
		btMin.setOpaque(false);
		btMin.setBorderPainted(false);
		btMax = new JButton();
		btMax.setBackground(Color.WHITE);
		btMax.setOpaque(false);
		//设置JButton没有边框
		btMax.setBorderPainted(false);
		btClose = new JButton();
		btClose.setBackground(Color.red);
		btClose.setOpaque(false);
		btClose.setBorderPainted(false);
		btMax.setBounds(340,3,30,20);
		btMin.setBounds(370,3,30,20);
		btClose.setBounds(400,3,30,20);
		
		labnorth.add(btMax);
		labnorth.add(btMin);
		labnorth.add(btClose);
		
		btsure.addActionListener(this);
		
		setSize(430,321);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		//设置窗口关闭，它不会马上把程序关闭，它会等一会
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btsure){
			dispose();
			new MainUI(myifo);
		}
		
	}
}
