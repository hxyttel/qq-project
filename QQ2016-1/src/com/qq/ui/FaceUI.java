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
	
	//��������
	JLabel[] bgIco;
	//�����ļ���·��
	String path[];
	ChatUI chat;
	public FaceUI(ChatUI chat ,int x, int y){
		this.chat=chat;
		//����û�б߿�
		setUndecorated(true);
		//���ò��ܸı��С
		setResizable(false);
		//���ô�������ǰ��
		setAlwaysOnTop(true);
		//��ȡ��ǰ���
		Container con = getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		con.setBackground(Color.WHITE);
		//��ȡ�ļ�
		File file = new File("bq");
		//��ȡ���ļ��������·��
		path = file.list();
		
		//����Ĵ�С�����ļ�������
		bgIco = new JLabel[path.length];
		//�����ļ���
		for(int i =0;i<path.length;i++){
			bgIco[i]= new JLabel(new ImageIcon("bq/"+path[i]));
			//���ð�ɫ�ı߿򣬱߿�Ϊ2���������صĸ߶�
			bgIco[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
			//Ϊÿһ��JLabel�����������¼�
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
		//���˫��
				if(e.getClickCount()==2){
					//�����������飬����һ���ؼ���ѡ��
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
		//����ƶ����Ŀؼ��Ͼʹ�������¼�

			for(int i=0;i<path.length;i++){
				if(e.getSource()==bgIco[i]){
				//���ñ߿���ɫ
				bgIco[i].setBorder(BorderFactory.createLineBorder(Color.blue,2));
				break;
			}
		
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//����뿪�Ŀؼ��������¼�
		
			for(int i =0;i<path.length;i++){
				if(e.getSource()==bgIco[i]){
				//����뿪�ÿؼ��ͽ��Ŀؼ�������ɫ����Ϊ��ɫ
				bgIco[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
				break;
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
	
	
}
