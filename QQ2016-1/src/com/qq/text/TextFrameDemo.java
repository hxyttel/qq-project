package com.qq.text;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class TextFrameDemo {
	static Point origin = new Point(); 
	static JFrame myFrame = new JFrame();

	public static void main(String[] args) {
		myFrame.setUndecorated(true);
		myFrame.setSize(600, 400);
		myFrame.setVisible(true);

		myFrame.addMouseListener(new MouseAdapter() {
			// ����(mousePressed
			// ���ǵ����������걻����û��̧��)
			public void mousePressed(MouseEvent e) { 
				// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				origin.x = e.getX(); 
				origin.y = e.getY();
			}
		});
		myFrame.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged
			// ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) { 														
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point p = myFrame.getLocation(); 
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				myFrame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
						- origin.y);
			}
		});
	}
}
	
