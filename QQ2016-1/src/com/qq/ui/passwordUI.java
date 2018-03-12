package com.qq.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

@SuppressWarnings("serial")
public class passwordUI extends JFrame implements ActionListener{
	JLabel bgphoto;
	JLabel newpassword,oldpassword;
	JTextField textnewpassword,textoldpassword;
	JButton btseve,btnClose,btClose,btMin;
	Font font;
	int qqid ;
	public passwordUI(int qqid){
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
		btMin.addActionListener(this);
		btClose.addActionListener(this);
		
		newpassword = new JLabel("新密码:");
		font = new Font("楷体",Font.BOLD,18);
		newpassword.setFont(font);
		newpassword.setBounds(270,140,100,30);
		bgphoto.add(newpassword);
		
		textnewpassword = new JTextField(10);
		//qqid+""的作用是因为qqid是整形，而setText必须是字符串
		textnewpassword.setBounds(270,170,210,30);
		bgphoto.add(textnewpassword);
		
		oldpassword = new JLabel("确认密码:");
		font = new Font("楷体",Font.BOLD,18);
		oldpassword.setFont(font);
		oldpassword.setBounds(270,200,100,30);
		bgphoto.add(oldpassword);
		
		textoldpassword = new JTextField();
		textoldpassword.setBounds(270,230,210,30);
		bgphoto.add(textoldpassword);
		
		btseve = new JButton("提交");
		btseve.setBackground(Color.blue);
		btseve.setBounds(290,265,80,30);
		bgphoto.add(btseve);
		btnClose = new JButton("退出");
		
		btnClose.setBackground(Color.blue);
		btnClose.setBounds(380,265,80,30);
		bgphoto.add(btnClose);
		btseve.addActionListener(this);
		btnClose.addActionListener(this);
		
		
		
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
		if(e.getSource()==btnClose){
			dispose();
			
		}
		if(e.getSource()==btseve){
			String onepassword = textnewpassword.getText().trim();
			String twopassword = textoldpassword.getText().trim();
			if(!onepassword.equals(twopassword)){
				JOptionPane.showMessageDialog(this,"你输入的密码与确认密码不相同,请重新输入");
				textoldpassword.setText("");
			}
			if(onepassword.equals("")){
				JOptionPane.showMessageDialog(this,"请输入密码");
			}
			if(onepassword.equals(twopassword)&&!onepassword.equals("")){
			//实现持久层
			AccountDao accountdao = new AccountDaoimpl();
			accountdao.updatepassword(qqid,onepassword);
			JOptionPane.showMessageDialog(this,"恭喜你修改成功");
			dispose();
			}
		}
		
	}
}
