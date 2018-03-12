package com.qq.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class QQfindPassword extends JFrame implements ActionListener,MouseListener{
	JLabel bgphoto;
	JLabel qqname,qqsure,labsure;
	JTextField nametext,suretext,zsurephoto;
	JButton btsure,btClose,btMin;
	Font font ;
	int qqid;
	int a = 0;
	public QQfindPassword(){
		
	}
	public QQfindPassword(int qqid){
		this.qqid = qqid;
		bgphoto = new JLabel(new ImageIcon("images/12345.png"));
		add(bgphoto);
		bgphoto.setLayout(null);
		btMin = new JButton();
		//添加按钮里面文字的字体颜色
		btMin.setForeground(Color.white);
		//设置按钮的背景颜色为白色
		btMin.setBackground(Color.WHITE);
		//设置背景颜色为空
		btMin.setOpaque(false);
		btMin.setBounds(635,5,15,15);
		btMin.setBorderPainted(false);
		bgphoto.add(btMin);
		btClose = new JButton();
		btClose.setForeground(Color.RED);
		btClose.setBackground(Color.WHITE);
		btClose.setOpaque(false);
		btClose.setBorderPainted(false);
		btClose.setEnabled(true);
		btClose.setBounds(666,3,15,15);
		bgphoto.add(btClose);
		
		qqname = new JLabel("账号:");
		font = new Font("楷体",Font.BOLD,18);
		qqname.setFont(font);
		qqname.setBounds(270,140,100,30);
		bgphoto.add(qqname);
		
		
	
		nametext = new JTextField(10);
		//qqid+""的作用是因为qqid是整形，而setText必须是字符串
		//传递的是qq账号
		nametext.setText(qqid+"");
		nametext.setBounds(270,170,210,30);
		bgphoto.add(nametext);
		
		
		qqsure = new JLabel("验证码:");
		font = new Font("楷体",Font.BOLD,18);
		qqsure.setFont(font);
		qqsure.setBounds(270,200,100,30);
		bgphoto.add(qqsure);
		
		suretext = new JTextField();
		suretext.setBounds(270,230,100,30);
		bgphoto.add(suretext);
		
		Random rd = new  Random();
		a = rd.nextInt(9999)+10000;
		zsurephoto = new JTextField();
		font = new Font("微软雅黑",Font.BOLD,24);
		zsurephoto.setForeground(Color.green);
		zsurephoto.setFont(font);
		zsurephoto.setText(a+"");
		zsurephoto.setBounds(375,230,100,30);
		bgphoto.add(zsurephoto);
		
		labsure = new JLabel("看不清,换一张");
		font = new Font("宋体",Font.PLAIN,14);
		labsure.setFont(font);
		labsure.setBounds(480,230,100,30);
		bgphoto.add(labsure);
		
		btsure = new JButton("确定");
		btsure.setBackground(Color.blue);
		btsure.setBounds(270,270,80,30);
		bgphoto.add(btsure);
		
		labsure.addMouseListener(this);
		btClose.addActionListener(this);
		btMin.addActionListener(this);
		btsure.addActionListener(this);
		
		setSize(700,400);
		//设置窗口没有外表框
		setUndecorated(true);
		setVisible(true);
		//设置窗口在屏幕中间
		setLocationRelativeTo(null);
		//设置窗口关闭，它不会马上把程序关闭，它会等一会
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btClose){
			dispose();
		}
		if(e.getSource()==btMin){
			this.setState(JFrame.HIDE_ON_CLOSE);
		}
		if(e.getSource()==btsure){
			if(suretext.getText().trim().equals(zsurephoto.getText().trim())){
				new passwordUI(qqid);
				dispose();
			}else{
				JOptionPane.showMessageDialog(this,"你输入的验证码不正确，请重新输入");
				suretext.setText("");
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==labsure){
			Random rd = new  Random();
			a = rd.nextInt(9999)+10000;
			zsurephoto.setText(a+"");
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
}
