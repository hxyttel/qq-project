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
		//��Ӱ�ť�������ֵ�������ɫ
		btMin.setForeground(Color.white);
		//���ð�ť�ı�����ɫΪ��ɫ
		btMin.setBackground(Color.WHITE);
		//���ñ�����ɫΪ��
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
		
		newpassword = new JLabel("������:");
		font = new Font("����",Font.BOLD,18);
		newpassword.setFont(font);
		newpassword.setBounds(270,140,100,30);
		bgphoto.add(newpassword);
		
		textnewpassword = new JTextField(10);
		//qqid+""����������Ϊqqid�����Σ���setText�������ַ���
		textnewpassword.setBounds(270,170,210,30);
		bgphoto.add(textnewpassword);
		
		oldpassword = new JLabel("ȷ������:");
		font = new Font("����",Font.BOLD,18);
		oldpassword.setFont(font);
		oldpassword.setBounds(270,200,100,30);
		bgphoto.add(oldpassword);
		
		textoldpassword = new JTextField();
		textoldpassword.setBounds(270,230,210,30);
		bgphoto.add(textoldpassword);
		
		btseve = new JButton("�ύ");
		btseve.setBackground(Color.blue);
		btseve.setBounds(290,265,80,30);
		bgphoto.add(btseve);
		btnClose = new JButton("�˳�");
		
		btnClose.setBackground(Color.blue);
		btnClose.setBounds(380,265,80,30);
		bgphoto.add(btnClose);
		btseve.addActionListener(this);
		btnClose.addActionListener(this);
		
		
		
		setSize(700,400);
		//���ô���û������
		setUndecorated(true);
		setVisible(true);
		//���ô�������Ļ�м�
		setLocationRelativeTo(null);
		//���ô��ڹرգ����������ϰѳ���رգ������һ��
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
				JOptionPane.showMessageDialog(this,"�������������ȷ�����벻��ͬ,����������");
				textoldpassword.setText("");
			}
			if(onepassword.equals("")){
				JOptionPane.showMessageDialog(this,"����������");
			}
			if(onepassword.equals(twopassword)&&!onepassword.equals("")){
			//ʵ�ֳ־ò�
			AccountDao accountdao = new AccountDaoimpl();
			accountdao.updatepassword(qqid,onepassword);
			JOptionPane.showMessageDialog(this,"��ϲ���޸ĳɹ�");
			dispose();
			}
		}
		
	}
}
