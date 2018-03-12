package com.qq.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.beens.Account;
import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

@SuppressWarnings("serial")
public class QQRegin extends JFrame implements ActionListener{
	//�ñ�ǩ����ͼƬ
	JLabel bgphoto;
	
	//��ǩ��������
	JLabel labQQid,labphoto,labtrname,labpassword,labtepassword,labinterst,labage,
				labsex,labcslat,labBoold,labhome,labadrss,labport,labprename;
	//�ı���
	JTextField tfQQid,tftrname,tfage,tfadress,tfport,tfinterst;
	//�������벻�ɼ�������
	JPasswordField  pfpassword,pftepassword;
	//��ѡ��
	JRadioButton rbgirs,rgboy;
	//���õ�ѡ����
	ButtonGroup bgsex;
	//����������
	JComboBox  cbphoto,cbcslat,cbBoold,cbhome;
	//�����ı�����
	JTextArea taprename;
	//���ð�ť
	JButton btSeve,btClose;
	String star[]={"��ţ��","ˮƿ��","ʨ����","��Ů��","��Ы��","Ħ����","˫����",
			"������","˫����","������","��з��","�����"};
	
	String bld[] = {"A","B","AB","O"};
	
	String naction[]={"����","����","����","����","׳��","����","��ɽ��","����","����"};
	
	String[] Headphlic={"images/1.jpg","images/2.jpg","images/5.jpg",
			"images/6.jpg","images/1.png","images/8.jpg","images/1.png",
			"heads/2.png","heads/0.png","images/108.jpg","images/9.png,","heads/3.png"
			,"heads/6.png","heads/7.png"};
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
							};
	
	public QQRegin(){
		super("QQ�û�ע��");
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		//���ô���ͼ��
		this.setIconImage(image);
		//�ñ�ǩ�����˱���ͼƬ
		bgphoto = new JLabel(new ImageIcon("images/4.jpg"));
		add(bgphoto);
		
		bgphoto.setLayout(null);
		
		labQQid = new JLabel("QQ�˺�:",JLabel.RIGHT);
		labphoto = new JLabel("QQͷ��:",JLabel.RIGHT);
		labtrname = new JLabel("�ǳ�:",JLabel.RIGHT);
		labpassword = new JLabel("����:",JLabel.RIGHT);
		labtepassword = new JLabel("ȷ������:",JLabel.RIGHT);
		labage = new JLabel("����:",JLabel.RIGHT);
		labsex = new JLabel("�Ա�:",JLabel.RIGHT);
		labcslat = new JLabel("����:",JLabel.RIGHT);
		labBoold = new  JLabel("Ѫ��:",JLabel.RIGHT);
		labadrss = new JLabel("IP��ַ:",JLabel.RIGHT);
		labport = new JLabel("ͨ�Ŷ˿�:",JLabel.RIGHT);
		labinterst = new JLabel("����",JLabel.RIGHT);
		labprename = new JLabel("����ǩ��:",JLabel.RIGHT);
		
		btSeve = new JButton("�ύ");
		btClose = new JButton("�ر�");
		
		tfQQid = new JTextField(10);
		//����QQ�˺ŵ����벻���Լ�����
		tfQQid.setText("ϵͳ�Զ�����");
		tfQQid.setEditable(false);
		tftrname = new JTextField(10);
		cbphoto = new JComboBox(headphote);
		pfpassword = new JPasswordField(10);
		//��������ʱ���ı��������������*�ô���
		pfpassword.setEchoChar('*');
		pftepassword = new JPasswordField(10);
		pftepassword.setEchoChar('*');
		tfage = new JTextField(5);
		//true��������Ĭ�ϵĽ�Ů��ΪĬ�ϵ����
		rbgirs = new JRadioButton("Ů",true);
		rgboy = new JRadioButton("��");
		bgsex = new ButtonGroup();
		bgsex.add(rbgirs);
		bgsex.add(rgboy);
		
		cbcslat = new JComboBox(star);
		cbBoold = new JComboBox(bld);
		cbhome = new JComboBox(naction);
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//getHostAddress()��ȡ�����ַ��ص��ַ���
		tfadress = new JTextField(address.getHostAddress());
		tfport = new JTextField(5);
		tfport.setText("ϵͳ�Զ�����");
		tfport.setEditable(false);
		tfinterst = new JTextField(20);
		taprename = new JTextArea(3,80);
		
		labQQid.setBounds(0,140,80,20);
		tfQQid.setBounds(90,140,120,20);
		bgphoto.add(labQQid);
		bgphoto.add(tfQQid);
		labphoto.setBounds(220,150,80,60);
		cbphoto.setBounds(320,150,80,60);
		bgphoto.add(labphoto);
		bgphoto.add(cbphoto);
		labtrname.setBounds(0,190,80,20);
		tftrname.setBounds(90,190,120,20);
		bgphoto.add(labtrname);
		bgphoto.add(tftrname);
		labpassword.setBounds(0,230,80,20);
		pfpassword.setBounds(90,230,120,20);
		bgphoto.add(labpassword);
		bgphoto.add(pfpassword);
		labtepassword.setBounds(220,230,80,20);
		pftepassword.setBounds(320,230,120,20);
		bgphoto.add(labtepassword);
		bgphoto.add(pftepassword);
		labage.setBounds(0,270,80,20);
		tfage.setBounds(90,270,120,20);
		bgphoto.add(labage);
		bgphoto.add(tfage);
		labsex.setBounds(220,270,80,20);
		rbgirs.setBounds(330,270,40,20);
		rgboy.setBounds(390,270,40,20);
		bgphoto.add(labsex);
		bgphoto.add(rbgirs);
		bgphoto.add(rgboy);
		labinterst.setBounds(0,310,80,20);
		tfinterst.setBounds(90,310,360,20);
		bgphoto.add(labinterst);
		bgphoto.add(tfinterst);
		labcslat.setBounds(0,350,80,20);
		cbcslat.setBounds(90,350,100,20);
		bgphoto.add(labcslat);
		bgphoto.add(cbcslat);
		labBoold.setBounds(220,350,80,20);
		cbBoold.setBounds(320,350,100,20);
		bgphoto.add(labBoold);
		bgphoto.add(cbBoold);
		labadrss.setBounds(0,390,80,20);
		tfadress.setBounds(90,390,100,20);
		bgphoto.add(labadrss);
		bgphoto.add(tfadress);
		labport.setBounds(220,390,80,20);
		tfport.setBounds(320,390,100,20);
		bgphoto.add(labport);
		bgphoto.add(tfport);
		labprename.setBounds(0,430,80,20);
		taprename.setBounds(90,420,350,80);
		bgphoto.add(labprename);
		bgphoto.add(taprename);
		
		btSeve.setBounds(160,510,70,40);
		bgphoto.add(btSeve);
		btClose.setBounds(270,510,70,40);
		bgphoto.add(btClose);
		
		btSeve.addActionListener(this);
		btClose.addActionListener(this);
		
		
		setSize(500,600);
		//���ô���û������
		//setUndecorated(true);
		setVisible(true);
		//���ô�������Ļ�м�
		setLocationRelativeTo(null);
		//���ô��ڹرգ����������ϰѳ���رգ������һ��
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new QQRegin();
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btSeve){
			if(tftrname.getText().equals("")){
				JOptionPane.showMessageDialog(this,"�ǳƲ���Ϊ��");
				return;
			}
			if(pfpassword.getText().equals("")){
				JOptionPane.showMessageDialog(this,"���벻��Ϊ��");
				return;
			}
			if(pftepassword.getText().equals("")){
				JOptionPane.showMessageDialog(this,"ȷ�����벻��Ϊ��");
				return;
			}
			int password = pfpassword.getText().length();
			if(password<8||password>17){
				JOptionPane.showMessageDialog(this,"��������Ϊ8��15λ");
				
			}
			if(!(pftepassword.getText().equals(pfpassword.getText()))){
				JOptionPane.showMessageDialog(this,"�����ȷ�����벻��ȷ������������");
				pftepassword.setText("");
				return;
			}
			if(tfage.getText().equals("")){
				JOptionPane.showMessageDialog(this,"���䲻��Ϊ��");
			}
			
			Account a = new Account();
			
			a.setInfoname(tftrname.getText());
			a.setPassword(pfpassword.getText());
			a.setAge(Integer.parseInt(tfage.getText()));
			a.setIpadress(tfadress.getText());
			a.setInterst(tfinterst.getText());
			a.setPrename(taprename.getText());
			a.setPhoto(Headphlic[cbphoto.getSelectedIndex()]);
			a.setConstellation(star[cbcslat.getSelectedIndex()]);
			a.setBoold(bld[cbBoold.getSelectedIndex()]);
			a.setMzhome(naction[cbhome.getSelectedIndex()]);
			//isSelected()�����������ǣ��Ƿ�ѡ��,Ĭ�ϵ����Ϊtrue
			if(rbgirs.isSelected()){
				a.setSex("Ů");
			}else{
				a.setSex("��");
			}
			
			AccountDao account = new AccountDaoimpl();
			String qqid = account.Save(a);
			JOptionPane.showMessageDialog(this,"�������QQ��Ϊ"+qqid);
			tftrname.setText("");
			pfpassword.setText("");
			pftepassword.setText("");
			tfage.setText("");
			tfinterst.setText("");
			taprename.setText("");
		}else if(e.getSource()==btClose){
			//���ô��ڹرգ����������ϰѳ���رգ������һ��
			dispose();
		}
		
	}
}
