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
			// 按下(mousePressed
			// 不是点击，而是鼠标被按下没有抬起)
			public void mousePressed(MouseEvent e) { 
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX(); 
				origin.y = e.getY();
			}
		});
		myFrame.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged
			// 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) { 														
				// 当鼠标拖动时获取窗口当前位置
				Point p = myFrame.getLocation(); 
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				myFrame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
						- origin.y);
			}
		});
	}
}
	
