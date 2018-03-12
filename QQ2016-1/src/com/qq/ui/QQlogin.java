package com.qq.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.qq.beens.Account;
import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

@SuppressWarnings("serial")
public class QQlogin extends JFrame implements ActionListener,MouseListener,MouseMotionListener,ItemListener{
	JLabel bg,logqq;
	JLabel labHphoto,labRe,labforpassword,labnorth;
	JComboBox cbRe;
	JPasswordField pfforpassword;
	JCheckBox ckforget,ckAutologin;
	JButton btleogin,btMin,btClose,btMax;
	static Point origin = new Point(); 
	
	HashMap<Integer, Account> user;
	
	AccountDao ad = new AccountDaoimpl();
	Account ac ;
	


	public QQlogin(){
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		bg = new JLabel(new ImageIcon("images/log.jpg"));
		bg.setLayout(null);
		add(bg);
		
		labnorth = new JLabel(new ImageIcon("images/qqname.gif"));
		labHphoto = new JLabel(new ImageIcon("images/133.jpg"));
		labRe = new JLabel("		");
		labforpassword = new JLabel("		");
		
		cbRe = new JComboBox();
		cbRe.setEditable(true);
		cbRe.setToolTipText("QQ账号:");
		
		pfforpassword = new JPasswordField(20);
		pfforpassword.setToolTipText("QQ密码：");
		
		ckforget = new JCheckBox("记住密码");
		ckforget.setOpaque(false);
		ckAutologin = new JCheckBox("自动登录");
		ckAutologin.setOpaque(false);
		
		btleogin = new JButton("登录");
		btleogin.setBackground(Color.BLUE);
		//btleogin.setOpaque(false);
		btMin = new JButton();
		btMin.setBackground(Color.WHITE);
		btMin.setOpaque(false);
		btMin.setBorderPainted(false);
		btMax = new JButton();
		btMax.setBackground(Color.WHITE);
		btMax.setOpaque(false);
		//设置JButton没有边框
		btMax.setBorderPainted(false);
		btClose = new JButton();
		btClose.setBackground(Color.red);
		btClose.setOpaque(false);
		btClose.setBorderPainted(false);
		
		labnorth.setBounds(0, 0, 430, 183);
		bg.add(labnorth);
		
		btMax.setBounds(340,3,30,20);
		btMin.setBounds(370,3,30,20);
		btClose.setBounds(400,3,30,20);
		labnorth.add(btMax);
		labnorth.add(btMin);
		labnorth.add(btClose);
		
		cbRe.setBounds(132,183,193,33);
		bg.add(cbRe);
		labRe.setBounds(338,186,70,25);
		bg.add(labRe);
		labforpassword.setBounds(338,210,70,25);
		bg.add(labforpassword);
		pfforpassword.setBounds(132,215,194,32);
		bg.add(pfforpassword);
		
		labHphoto.setBounds(30,186,80,90);
		bg.add(labHphoto);
		
		ckforget.setBounds(150,260,100,15);
		bg.add(ckforget);
		
		ckAutologin.setBounds(235,260,100,15);
		bg.add(ckAutologin);
		btleogin.setBounds(145,285,170,30);
		bg.add(btleogin);		
		
		ckforget.addItemListener(this);
		btMin.addActionListener(this);
		btMax.addActionListener(this);
		btClose.addActionListener(this);
		labRe.addMouseListener(this);
		btleogin.addActionListener(this);
		labforpassword.addMouseListener(this);
		//设置窗口能拖动的兼听事件
		addMouseListener(this);
		addMouseMotionListener(this);
		ReadFile();
		
		
		setSize(430,321);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		//设置窗口关闭，它不会马上把程序关闭，它会等一会
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	public static void main(String[] args) {
		new QQlogin();	
	}
	@SuppressWarnings({ "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btMax){	
		}
		if(e.getSource()==btMin){
			//setStata(JFrame.HIDE_ON_Close)实现最小化
			this.setState(JFrame.HIDE_ON_CLOSE);
		}
		if(e.getSource()==btClose){
			System.exit(0);
		}
		if(e.getSource()==btleogin){
			if(cbRe.getSelectedItem()!=null&&!cbRe.getSelectedItem().equals("")){
				if(pfforpassword.getText().equals("")){
					JOptionPane.showMessageDialog(this,"密码不能为空,请输入密码");
					return;
				}
				
				 ac = new Account();
				try{
				ac.setQQid(Integer.parseInt(cbRe.getSelectedItem().toString()));
				}catch(NumberFormatException en){
					JOptionPane.showMessageDialog(this,"QQ号码不正确");
					return;
				}
				
				ac.setPassword(pfforpassword.getText());
			//实现持久层
				ac=ad.login(ac);
				//如果数据库里面存在你的账号你就可以实现QQ登录
				if(ac!=null){
					dispose();
					sava(ac);
					new LOGINUI(ac);
					//new MainUI(ac);
				}else{
					JOptionPane.showMessageDialog(this,"你输入的QQ号或密码不正确,请重新输入");
					pfforpassword.setText("");
					return;
				}	
			}else{
				JOptionPane.showMessageDialog(this,"请输入QQ号");
				return;
			}
			
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==labRe){
			//实现qq注册
			new QQRegin();
		}
		if(e.getSource()==labforpassword){
			//实现找回密码，传递的是当前QQ的账号的参数
			new QQfindPassword(Integer.parseInt(cbRe.getSelectedItem().toString()) );
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		origin.x = e.getX(); 
		origin.y = e.getY();
	}
	public void mouseDragged(MouseEvent e) { 														
		Point p =this.getLocation(); 
		// 设置窗口的位置
		// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
		this.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
				- origin.y);
	}
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@SuppressWarnings("unchecked")
	public void sava(Account account){
		
		File file = new File("account.dat");
		try{
		if(!file.exists()){
			file.createNewFile();
			user = new HashMap<Integer, Account>();
		}else{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			user = (HashMap<Integer, Account>) ois.readObject();
			
			fis.close();
			ois.close();
			}
		user.put(account.getQQid(), account);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("account.dat"));
		os.writeObject(user);
		os.flush();
		os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*把硬盘上的内存读取在登录界面上
	 *1.使用对象的输入流把硬盘上的对象都读取到HashMap的对象里面
	 *2.遍历这个键，把所有的qq号码都保存在下拉框里面
	 * */	
	@SuppressWarnings({ "resource", "unchecked" })
	public void ReadFile(){
		File file = new File("account.dat");
	try{
			if(!file.exists()){
				return;
			}
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			user = (HashMap<Integer, Account>) ois.readObject();
			/**
			 * user.keySet();
			 * 1.把HashMap里面所有的key装载进入一个Set集合
			 * 2.返回此Set集合
			 * */
			Set<Integer> set = user.keySet();
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){
				cbRe.addItem(it.next());
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		//记住密码
		if(e.getSource()==ckforget){
		//创建了一个对象
		Account a = new Account();
		a.setQQid(Integer.parseInt(cbRe.getSelectedItem().toString()));
		//实现了持久层
		a=ad.selectpasswordd(a) ;
		pfforpassword.setText(a.getPassword());
		}
		
	}
		
	
}
