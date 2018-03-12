package com.qq.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.beens.Account;
import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

@SuppressWarnings("serial")
public class UpdateUI extends JFrame implements ActionListener{
	JLabel bgphoto;
	JLabel title,qqphoto,qqname,qqage,qqsex,qqcon,qqboold,qqpre,qqint;
	JTextField textname,textage,textint;
	JComboBox  cbphoto,cbcon,cbboold;
	JTextArea tepre;
	JRadioButton rbgirs,rgboy;
	//���õ�ѡ����
	ButtonGroup bgsex;
	JButton btseve,btcose;
	Font font;
	Account myifo;
	MainUI mainui;
	
	String star[]={"��ţ��","ˮƿ��","ʨ����","��Ů��","��Ы��","Ħ����","˫����",
			"������","˫����","������","��з��","�����"};
	
	String bld[] = {"A","B","AB","O"};
	
	String naction[]={"����","����","����","����","׳��","����","��ɽ��","����","����"};
	
	String[] Headphlic={"images/1.jpg","images/2.jpg","images/5.jpg",
			"images/6.jpg","images/1.png","images/8.jpg","heads/1.png",
			"heads/2.png","heads/0.png","images/108.jpg","heads/9.png","images/7.png","heads/3.png",
			"heads/6.png","heads/7.png"};
	ImageIcon[] headphote={new ImageIcon(Headphlic[0]),
							new ImageIcon(Headphlic[1]),
							new ImageIcon(Headphlic[2]),
							new ImageIcon(Headphlic[3]),
							new ImageIcon(Headphlic[4]),
							new ImageIcon(Headphlic[5]),
							new ImageIcon(Headphlic[6]),
							new ImageIcon(Headphlic[7]),
							new ImageIcon(Headphlic[8]),
							new ImageIcon(Headphlic[9]),
							new ImageIcon(Headphlic[10]),
							new ImageIcon(Headphlic[11]),
							new ImageIcon(Headphlic[12]),
							new ImageIcon(Headphlic[13]),
							new ImageIcon(Headphlic[14])};
	
	public UpdateUI(){
		
	}
	public UpdateUI(Account myifo,MainUI mainui){
		this.myifo =myifo;
		this.mainui=mainui;
		bgphoto = new JLabel(new ImageIcon("images/1112.jpg"));
		add(bgphoto);
		bgphoto.setLayout(null);
		title = new JLabel("�޸��û�����");
		font = new Font("����",Font.BOLD,32);
		title.setForeground(Color.RED);
		title.setFont(font);
		title.setBounds(40,30,250,30);
		bgphoto.add(title);
		qqname = new JLabel("�ǳ�:");
		font = new Font("����",Font.BOLD,18);
		qqname.setFont(font);
		qqname.setForeground(Color.black);
		qqname.setBounds(55, 85, 50, 20);
		bgphoto.add(qqname);
		
		textname = new JTextField(myifo.getInfoname());
		textname.setBounds(115,85, 100, 20);
		bgphoto.add(textname);
		
		qqphoto = new JLabel("ͷ��:");
		font = new Font("����",Font.BOLD,18);
		qqphoto.setFont(font);
		qqphoto.setForeground(Color.black);
		qqphoto.setBounds(55,145,145,20);
		bgphoto.add(qqphoto);
		cbphoto = new JComboBox(headphote);
		cbphoto.setBounds(115, 125, 100, 70);
		//����ѡ�е�ͷ��
		for(int i=0;i<Headphlic.length;i++){
			if(Headphlic[i].equals(myifo.getPhoto())){
				cbphoto.setSelectedIndex(i);
				break;
			}
		}
		bgphoto.add(cbphoto);
		
		qqage = new JLabel("����:");
		font = new Font("����",Font.BOLD,18);
		qqage.setFont(font);
		qqage.setForeground(Color.black);
		qqage.setBounds(55,220 ,50,20);
		bgphoto.add(qqage);
		
		textage = new JTextField(myifo.getAge()+"");
		textage.setBounds(115,220,100,20);
		bgphoto.add(textage);
		
		qqsex = new JLabel("�Ա�:");
		font = new Font("����",Font.BOLD,18);
		qqsex.setFont(font);
		qqsex.setForeground(Color.black);
		qqsex.setBounds(60,265,50,20);
		bgphoto.add(qqsex);
		
		if(myifo.getSex().equals("Ů")){
			rbgirs = new JRadioButton("Ů",true);
			rgboy = new JRadioButton("��");
		}else{
			rbgirs = new JRadioButton("Ů");
			rgboy = new JRadioButton("��",true);
		}
		
		bgsex = new ButtonGroup();
		bgsex.add(rbgirs);
		bgsex.add(rgboy);
		rbgirs.setBounds(130,265,40,20);
		rgboy.setBounds(170,265,40,20);
		bgphoto.add(rbgirs);
		bgphoto.add(rgboy);
		
		qqcon = new JLabel("����:");
		font = new Font("����",Font.BOLD,18);
		qqcon.setFont(font);
		qqcon.setForeground(Color.black);
		qqcon.setBounds(55, 310,50,20);
		bgphoto.add(qqcon);
		
		cbcon = new JComboBox(star);
		for(int i = 0;i<star.length;i++){
			if(star[i].equals(myifo.getConstellation())){
				cbcon.setSelectedIndex(i);
				break;
			}
		}
		cbcon.setBounds(115,310,100,20);
		bgphoto.add(cbcon);
		
		qqboold = new JLabel("Ѫ��:");
		font = new Font("����",Font.BOLD,18);
		qqboold.setFont(font);
		qqboold.setForeground(Color.black);
		qqboold.setBounds(55, 355,50,20);
		bgphoto.add(qqboold);
		
		cbboold = new JComboBox(bld);
		for(int i =0;i<bld.length;i++){
			if(bld[i].equals(myifo.getBoold())){
				cbboold.setSelectedIndex(i);
				break;
			}
		}
		cbboold.setBounds(115,355,100,20);
		bgphoto.add(cbboold);
		
		qqint = new JLabel("����:");
		font = new Font("����",Font.BOLD,18);
		qqint.setFont(font);
		qqint.setForeground(Color.black);
		qqint.setBounds(55, 400,50,20);
		bgphoto.add(qqint);
		
		System.out.println(myifo.getInterst());
		textint = new JTextField(myifo.getInterst());
		textint.setBounds(115,400,150,20);
		bgphoto.add(textint);
		
		
		qqpre = new JLabel("����ǩ��:",JLabel.LEFT);
		font = new Font("����",Font.BOLD,18);
		qqpre.setFont(font);
		qqpre.setForeground(Color.black);
		qqpre.setBounds(55,485,100,20);
		bgphoto.add(qqpre);
		
		tepre = new JTextArea(myifo.getPrename());
		tepre.setBounds(155, 465, 130,60);
		bgphoto.add(tepre);
		
		btseve = new JButton("�ύ");
		btseve.setBounds(85,530,60,30);
		bgphoto.add(btseve);
		
		btcose = new JButton("�ر�");
		btcose.setBounds(175,530,60,30);
		bgphoto.add(btcose);
		btcose.addActionListener(this);
		btseve.addActionListener(this);
		
		setSize(300,580);
		//���ô���û������
		setUndecorated(true);
		setVisible(true);
		//���ô�������Ļ�м�
		setLocationRelativeTo(null);
		//���ô��ڹرգ����������ϰѳ���رգ������һ��
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new UpdateUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btseve){
		
			String name = textname.getText().trim();
			if(name.equals("")){
				JOptionPane.showMessageDialog(this,"�������ǳ�");
				return;
			}
			int age = 0;
			String sage = textage.getText().trim();
			
			if(sage.equals("")){
				JOptionPane.showMessageDialog(this,"����������");
				textage.setText("0");
				return;
			}
			try{
				age = Integer.parseInt(sage);
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this, "������0~150֮�������");
				return;
			}
			if(age<0 || age>150){
				JOptionPane.showMessageDialog(this, "������0~150֮�������");
				return;
			}
			myifo.setInfoname(name);
			myifo.setPhoto(Headphlic[cbphoto.getSelectedIndex()]);
			myifo.setAge(age);
			if(rbgirs.isSelected()){
				myifo.setSex("Ů");
			}else{
				myifo.setSex("��");
			}
			myifo.setBoold(bld[cbboold.getSelectedIndex()]);
			myifo.setConstellation(star[cbcon.getSelectedIndex()]);
			myifo.setInterst(textint.getText().trim());
			myifo.setPrename(tepre.getText().trim());
			AccountDao accountdao =new AccountDaoimpl();
			myifo = accountdao.update(myifo);
			JOptionPane.showMessageDialog(this,"��ϲ���޸ĳɹ�");
			ImageIcon icon = new ImageIcon(myifo.getPhoto());
			String str = myifo.getInfoname()+"��"+myifo.getPrename()+"��";
			//ˢ����������Ϣ
			mainui.labMyifo.setIcon(icon);
			mainui.labMyifo.setText(str);
			dispose();
			
			}
		
		if(e.getSource()==btcose){
			dispose();
		}
		
	}
	
}
