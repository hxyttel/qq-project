package com.qq.ui;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.qq.base.cmd;
import com.qq.beens.Account;

@SuppressWarnings("serial")
public class StementUI extends JFrame{
	JLabel myLabel;
	public StementUI(Account myifo){
		setUndecorated(true);
		getContentPane().setBackground(Color.WHITE);
		String str = "【"+ myifo.getInfoname()+"】";
			if(myifo.getStement().equals(cmd.STATUS[0])){
				str+="上线了";
			}else{
				str+="下线了";
			}
			String headimg = ChangStement(myifo);
			myLabel = new JLabel(str,new ImageIcon(headimg),JLabel.LEFT);
			add(myLabel);
			setAlwaysOnTop(true);
			setSize(200,50);
			Toolkit t = Toolkit.getDefaultToolkit();
			int width = t.getScreenSize().width-200;
			int heigth = t.getScreenSize().height;
			setVisible(true);
			for(int i=0;i<100;i++){
				setLocation(width,heigth-i);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			for(int i=100;i>0;i--){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			dispose();
			
	}
	//根据状态的不同设置头像
	public String ChangStement(Account myifo){
		String stement = myifo.getStement();
		String filename = myifo.getPhoto();
		String headimag = myifo.getPhoto();
		if(stement.equals(cmd.STATUS[0])){
			filename = headimag;
		}
		else if(stement.equals(cmd.STATUS[1])){
			int pos = headimag.indexOf('.');
			String pre = headimag.substring(0,pos);
			String fos = headimag.substring(pos,headimag.length());
			filename = pre+"_h"+fos;
		}
		return filename;
		
	}
}
