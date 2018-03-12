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
	//用标签设置图片
	JLabel bgphoto;
	
	//标签设置内容
	JLabel labQQid,labphoto,labtrname,labpassword,labtepassword,labinterst,labage,
				labsex,labcslat,labBoold,labhome,labadrss,labport,labprename;
	//文本框
	JTextField tfQQid,tftrname,tfage,tfadress,tfport,tfinterst;
	//设置密码不可见的内容
	JPasswordField  pfpassword,pftepassword;
	//单选框
	JRadioButton rbgirs,rgboy;
	//设置单选框组
	ButtonGroup bgsex;
	//设置下拉框
	JComboBox  cbphoto,cbcslat,cbBoold,cbhome;
	//设置文本框组
	JTextArea taprename;
	//设置按钮
	JButton btSeve,btClose;
	String star[]={"金牛座","水瓶座","狮子座","处女座","天蝎座","摩羯座","双子座",
			"射手座","双鱼座","白羊座","巨蟹座","天秤座"};
	
	String bld[] = {"A","B","AB","O"};
	
	String naction[]={"汉族","苗族","傣族","彝族","壮族","回族","高山族","藏族","其他"};
	
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
		super("QQ用户注册");
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		//设置窗口图标
		this.setIconImage(image);
		//用标签设置了背景图片
		bgphoto = new JLabel(new ImageIcon("images/4.jpg"));
		add(bgphoto);
		
		bgphoto.setLayout(null);
		
		labQQid = new JLabel("QQ账号:",JLabel.RIGHT);
		labphoto = new JLabel("QQ头像:",JLabel.RIGHT);
		labtrname = new JLabel("昵称:",JLabel.RIGHT);
		labpassword = new JLabel("密码:",JLabel.RIGHT);
		labtepassword = new JLabel("确认密码:",JLabel.RIGHT);
		labage = new JLabel("年龄:",JLabel.RIGHT);
		labsex = new JLabel("性别:",JLabel.RIGHT);
		labcslat = new JLabel("星座:",JLabel.RIGHT);
		labBoold = new  JLabel("血型:",JLabel.RIGHT);
		labadrss = new JLabel("IP地址:",JLabel.RIGHT);
		labport = new JLabel("通信端口:",JLabel.RIGHT);
		labinterst = new JLabel("爱好",JLabel.RIGHT);
		labprename = new JLabel("个性签名:",JLabel.RIGHT);
		
		btSeve = new JButton("提交");
		btClose = new JButton("关闭");
		
		tfQQid = new JTextField(10);
		//设置QQ账号的密码不有自己输入
		tfQQid.setText("系统自动生成");
		tfQQid.setEditable(false);
		tftrname = new JTextField(10);
		cbphoto = new JComboBox(headphote);
		pfpassword = new JPasswordField(10);
		//设置密码时在文本框输入的内容用*好代替
		pfpassword.setEchoChar('*');
		pftepassword = new JPasswordField(10);
		pftepassword.setEchoChar('*');
		tfage = new JTextField(5);
		//true在这里是默认的将女作为默认的情况
		rbgirs = new JRadioButton("女",true);
		rgboy = new JRadioButton("男");
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//getHostAddress()获取主句地址相关的字符串
		tfadress = new JTextField(address.getHostAddress());
		tfport = new JTextField(5);
		tfport.setText("系统自动输入");
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
		//设置窗口没有外表框
		//setUndecorated(true);
		setVisible(true);
		//设置窗口在屏幕中间
		setLocationRelativeTo(null);
		//设置窗口关闭，它不会马上把程序关闭，它会等一会
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
				JOptionPane.showMessageDialog(this,"昵称不能为空");
				return;
			}
			if(pfpassword.getText().equals("")){
				JOptionPane.showMessageDialog(this,"密码不能为空");
				return;
			}
			if(pftepassword.getText().equals("")){
				JOptionPane.showMessageDialog(this,"确认密码不能为空");
				return;
			}
			int password = pfpassword.getText().length();
			if(password<8||password>17){
				JOptionPane.showMessageDialog(this,"密码设置为8—15位");
				
			}
			if(!(pftepassword.getText().equals(pfpassword.getText()))){
				JOptionPane.showMessageDialog(this,"输入的确认密码不正确，请重新输入");
				pftepassword.setText("");
				return;
			}
			if(tfage.getText().equals("")){
				JOptionPane.showMessageDialog(this,"年龄不能为空");
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
			//isSelected()方法的作用是：是否被选中,默认的情况为true
			if(rbgirs.isSelected()){
				a.setSex("女");
			}else{
				a.setSex("男");
			}
			
			AccountDao account = new AccountDaoimpl();
			String qqid = account.Save(a);
			JOptionPane.showMessageDialog(this,"你申请的QQ号为"+qqid);
			tftrname.setText("");
			pfpassword.setText("");
			pftepassword.setText("");
			tfage.setText("");
			tfinterst.setText("");
			taprename.setText("");
		}else if(e.getSource()==btClose){
			//设置窗口关闭，它不会马上把程序关闭，它会等一会
			dispose();
		}
		
	}
}
