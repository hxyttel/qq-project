package com.qq.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.qq.beens.Account;

@SuppressWarnings("serial")
public class LookFrend extends JFrame implements MouseListener{
	JLabel bgphoto;
	JButton qqphoto;
	JLabel qqid,qqname,qqiname,qqage,qqsex,qqcon,qqboold,qqmzu,qqhobit,qqpre;
	JLabel textiname,textage,textsex,textcon,textboold,textmzu,texthobit;
	Font fong,Font;
	
	@SuppressWarnings("static-access")
	public LookFrend(Account myifo){
		bgphoto = new JLabel(new ImageIcon("images/402.png"));
		add(bgphoto);
		bgphoto.setLayout(null);
		qqphoto = new JButton(new ImageIcon(myifo.getPhoto()));
		qqphoto.setMargin(new Insets(0,0,0,0));
		qqphoto.setBounds(18,65,72,72);
		bgphoto.add(qqphoto);
		
		qqname = new JLabel(myifo.getInfoname()+"("+myifo.getQQid()+")");
		qqname.setBounds(108, 105, 200,20);
		fong = new Font("楷体",Font.BOLD,18);
		qqname.setFont(fong);
		qqname.setForeground(Color.ORANGE);
		bgphoto.add(qqname);
		
		qqpre = new JLabel("【"+myifo.getPrename()+"】");
		qqpre.setBounds(111,125,250,30);
		fong = new Font("宋体",Font.BOLD,14);
		qqpre.setFont(fong);
		qqpre.setForeground(Color.BLACK);
		bgphoto.add(qqpre);
		
		
		
		qqiname = new JLabel("昵称:");
		qqiname.setBounds(15, 160, 50,20);
		fong = new Font("宋体",Font.BOLD,18);
		qqiname.setFont(fong);
		qqiname.setForeground(Color.black);
		bgphoto.add(qqiname);
		
		textiname = new JLabel(myifo.getInfoname());
		textiname.setBounds(72, 160,60, 20);
		fong = new Font("宋体",Font.BOLD,14);
		textiname.setFont(fong);
		textiname.setForeground(Color.gray);
		bgphoto.add(textiname);
		
		qqage = new JLabel("年龄:   "+myifo.getAge());
		fong = new Font("宋体",Font.BOLD,18);
		qqage.setFont(fong);
		qqage.setForeground(Color.pink);
		qqage.setBounds(15, 205, 150, 20);
		bgphoto.add(qqage);
		
		qqsex = new JLabel("性别:");
		qqsex.setBounds(15,250,50,20);
		fong = new Font("宋体",Font.BOLD,18);
		qqsex.setFont(fong);
		qqsex.setForeground(Color.BLACK);
		bgphoto.add(qqsex);
		
		textsex = new JLabel(myifo.getSex());
		textsex.setBounds(75,250,60,20);
		fong = new Font("宋体",Font.BOLD,14);
		textsex.setFont(fong);
		textsex.setForeground(Color.gray);
		bgphoto.add(textsex);
		
		qqcon = new JLabel("星座:");
		qqcon.setBounds(15,295,50,20);
		fong = new Font("宋体",Font.BOLD,18);
		qqcon.setFont(fong);
		qqcon.setForeground(Color.BLACK);
		bgphoto.add(qqcon);
		
		textcon = new JLabel(myifo.getConstellation());
		textcon.setBounds(75,295,60,20);
		fong = new Font("宋体",Font.BOLD,14);
		textcon.setFont(fong);
		textcon.setForeground(Color.BLACK);
		bgphoto.add(textcon);
		
		qqboold = new JLabel("血型:");
		fong = new Font("宋体",Font.BOLD,18);
		qqboold.setFont(fong);
		qqboold.setForeground(Color.BLACK);
		qqboold.setBounds(15,340,50,20);
		bgphoto.add(qqboold);
		
		textboold= new JLabel(myifo.getBoold()+"型");
		textboold.setBounds(75,340,60,20);
		fong = new Font("宋体",Font.BOLD,14);
		textboold.setFont(fong);
		textboold.setForeground(Color.gray);
		bgphoto.add(textboold);
		
		qqmzu = new JLabel("名族:");
		qqmzu.setBounds(15,385,50,20);
		fong = new Font("宋体",Font.BOLD,18);
		qqmzu.setFont(fong);
		qqmzu.setForeground(Color.BLACK);
		bgphoto.add(qqmzu);
		
		
		textmzu = new JLabel(myifo.getMzhome());
		textmzu.setBounds(75,385,60,20);
		fong = new Font("宋体",Font.BOLD,14);
		textmzu.setFont(fong);
		textmzu.setForeground(Color.gray);
		bgphoto.add(textmzu);
		
		qqhobit = new JLabel("爱好:");
		fong = new Font("宋体",Font.BOLD,18);
		qqhobit.setFont(fong);
		qqhobit.setForeground(Color.BLACK);
		qqhobit.setBounds(15,435,50,20);
		bgphoto.add(qqhobit);
		
		texthobit = new JLabel(myifo.getInterst());
		fong = new Font("宋体",Font.BOLD,14);
		texthobit.setFont(fong);
		texthobit.setForeground(Color.gray);
		texthobit.setBounds(75,435,150,20);
		bgphoto.add(texthobit);
		addMouseListener(this);
		
		setSize(300,500);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public LookFrend(){
		
	}
	
	public static void main(String[] args) {
		new LookFrend();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		dispose();
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	

}
