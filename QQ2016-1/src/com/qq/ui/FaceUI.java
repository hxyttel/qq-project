package com.qq.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FaceUI extends JFrame implements MouseListener{
	
	//表情数组
	JLabel[] bgIco;
	//保存文件的路径
	String path[];
	ChatUI chat;
	public FaceUI(ChatUI chat ,int x, int y){
		this.chat=chat;
		//设置没有边框
		setUndecorated(true);
		//设置不能改变大小
		setResizable(false);
		//设置窗口在最前面
		setAlwaysOnTop(true);
		//获取当前面板
		Container con = getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		con.setBackground(Color.WHITE);
		//获取文件
		File file = new File("bq");
		//获取到文件里的所有路径
		path = file.list();
		
		//数组的大小就是文件的数量
		bgIco = new JLabel[path.length];
		//遍历文件名
		for(int i =0;i<path.length;i++){
			bgIco[i]= new JLabel(new ImageIcon("bq/"+path[i]));
			//设置白色的边框，边框为2的两个像素的高度
			bgIco[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
			//为每一个JLabel组件设置鼠标事件
			bgIco[i].addMouseListener(this);
			add(bgIco[i]);
			
		}
		setSize(300, 320);
		setVisible(true);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//鼠标双击
				if(e.getClickCount()==2){
					//遍历整个数组，看哪一个控件被选中
					for(int i = 0;i<path.length;i++){
						if(e.getSource()==bgIco[i]){
							chat.textsned.insertIcon(bgIco[i].getIcon());
							dispose();
							break;
						}
					}
				}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//鼠标移动到改控件上就触发这个事件

			for(int i=0;i<path.length;i++){
				if(e.getSource()==bgIco[i]){
				//设置边框颜色
				bgIco[i].setBorder(BorderFactory.createLineBorder(Color.blue,2));
				break;
			}
		
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//鼠标离开改控件触发的事件
		
			for(int i =0;i<path.length;i++){
				if(e.getSource()==bgIco[i]){
				//鼠标离开该控件就将改控件背景颜色设置为白色
				bgIco[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
				break;
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
	
	
}
