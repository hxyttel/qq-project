package com.qq.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

import com.qq.base.SendCmd;
import com.qq.base.SendMsg;
import com.qq.base.Sound;
import com.qq.base.cmd;
import com.qq.beens.Account;
import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

@SuppressWarnings("serial")
public class MainUI extends JFrame implements ActionListener,MouseListener,MouseMotionListener{
	
	static Account Myifo;
	Account friendifo;
	//背景图片和头像
	JLabel bgphoto,labMyifo,labTop,labBottom;
	//设置最小化和关闭
	JLabel lblMin,lblClose;
	//设置选项卡面板
	JTabbedPane tabList;
	//设置清单列表
	JList friendsList,mateList,fiamlyList,blackList;
	//设置查找,修改,最小化，关闭按钮
	JButton btFond,btChange,btMin,btClose,btphoto;
	//设置状态的下拉框
	JComboBox cbStatment;
	//设置清单
	JPopupMenu popupmenu;
	//设置清单里面的Item
	JMenuItem meChat,memoveFrend,memoveMate,memoveFamily,memoveBlack,meLookfreng,meDelfrend;
	//设置托盘
	TrayIcon trayicon;
	//设置托盘里面的列表
	PopupMenu poputray;
	MenuItem  meOpen,meExit,meOnlien,meBuy,menotLook;
	//为每一个列表里面的向量
	Vector<Account> VallInfo,VFriend,Vfamily,Vmate,Vblack;
	
	HashMap<Integer, ChatUI> chatWin = new HashMap<Integer,ChatUI>();
	//调用了DAO模型，引用抽象方法
	AccountDao account = new AccountDaoimpl();
	//实现窗口的拖动
	static Point origin = new Point(); 
	
	@SuppressWarnings({ "static-access", "unused" })
	public MainUI(Account a){
		this.Myifo=a;
		//setResizable(false);
		//设置图标
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		//设置背景图片
		bgphoto = new  JLabel(new ImageIcon("images/142.jpg"));
		add(bgphoto);
		bgphoto.setLayout(new BorderLayout());
		
		//在北的面板里在设置一个面板
		JPanel Top = new JPanel(new BorderLayout() );
		//Font font = new Font("楷体",Font.BOLD,12);
		labTop = new JLabel(new ImageIcon("images/top1.png"));
		labTop.setLayout(null);
		btMin= new JButton();
		
		btMin.setForeground(Color.white);
		//设置按钮的背景颜色为白色
		btMin.setBackground(Color.WHITE);
		//设置背景颜色为空
		btMin.setOpaque(false);
		//设置按钮没有边框
		btMin.setBorderPainted(false);
		btClose = new JButton();
		btClose.setForeground(Color.RED);
		btClose.setBackground(Color.WHITE);
		btClose.setOpaque(false);
		btClose.setBorderPainted(false);
		btClose.setEnabled(true);
		btMin.setBounds(230,5,30, 20);
		btClose.setBounds(265,5,30,20);
		
		/*btphoto = new JButton(new ImageIcon(Myifo.getPhoto()));
		btphoto.setMargin(new Insets(0,0,0,0));
		btphoto.setBounds(10, 45,80,80);*/
		String all = Myifo.getInfoname()+"【"+Myifo.getPrename()+"】";
		ImageIcon ima = new ImageIcon(Myifo.getPhoto());
		labMyifo = new JLabel(all,ima,JLabel.LEFT);
		//labMyifo.setFont(font);
		labMyifo.setForeground(Color.cyan);
		labMyifo.setOpaque(false);
		labMyifo.setBounds(10,50,250,80);
		labTop.add(btMin);
		labTop.add(btClose);
		//labTop.add(btphoto);
		labTop.add(labMyifo);
		
		bgphoto.add(labTop,BorderLayout.NORTH);
			
		friendsList = new JList();
		mateList = new JList();
		fiamlyList = new JList();
		blackList = new JList();
		
		//把列表设置为透明
		friendsList.setOpaque(false);
		mateList.setOpaque(false);
		fiamlyList.setOpaque(false);
		blackList.setOpaque(false);
		
		//设置选项卡面板为透明
		UIManager.put("TabbedPane.contentOpaque", false);
		/**
		 * tabPanel就是选项卡面板
		 * 1.所谓的选项卡面板就是可以把几个面板叠在一起，自由切换
		 * */
		tabList = new JTabbedPane();
		tabList.setOpaque(false);
		tabList.add("好友",friendsList);
		tabList.add("同学",mateList);
		tabList.add("家人",fiamlyList);
		tabList.add("黑名单",blackList);
		
		VallInfo = new Vector<Account>();
		VFriend = new Vector<Account>();
		Vfamily = new Vector<Account>();
		Vmate  = new Vector<Account>();
		Vblack = new Vector<Account>();
		
		reflush();
		
		
		
		bgphoto.add(tabList,BorderLayout.CENTER);
		
		/*JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btChange = new JButton("换肤");
		btFond = new 	JButton("查找");
		cbStatment = new JComboBox(cmd.STATUS);
		cbStatment.removeItemAt(1);
		
		bottom.add(btChange);
		bottom.add(btFond);
		bottom.add(cbStatment);
		bottom.setOpaque(false);
		bgphoto.add(bottom,BorderLayout.SOUTH);*/
		
		labBottom = new JLabel(new ImageIcon("images/bootom.png"));
		labBottom.setLayout(null);
		btChange = new JButton();
		btChange.setForeground(Color.white);
		//设置按钮的背景颜色为白色
		btChange.setBackground(Color.WHITE);
		//设置背景颜色为空
		btChange.setOpaque(false);
		//设置按钮没有边框
		btChange.setBorderPainted(false);
		btChange.setBounds(170,0,60,30);
		labBottom.add(btChange);
		btFond  = new JButton();
		btFond.setForeground(Color.white);
		//设置按钮的背景颜色为白色
		btFond.setBackground(Color.WHITE);
		//设置背景颜色为空
		btFond.setOpaque(false);
		//设置按钮没有边框
		btFond.setBorderPainted(false);
		btFond.setBounds(240,0,60,30);
		labBottom.add(btFond);
		bgphoto.add(labBottom, BorderLayout.SOUTH);
		
		creatMenu();
		
		CreateTray();
		setTray();
		
		addThread at = new addThread();
		at.start();
		
		
		btMin.addActionListener(this);
		btClose.addActionListener(this);
		friendsList.addMouseListener(this);
		mateList.addMouseListener(this);
		fiamlyList.addMouseListener(this);
		blackList.addMouseListener(this);
		memoveFrend.addActionListener(this);
		memoveFamily.addActionListener(this);
		memoveMate.addActionListener(this);
		memoveBlack.addActionListener(this);
		meOpen.addActionListener(this);
		meOnlien.addActionListener(this);
		menotLook.addActionListener(this);
		meBuy.addActionListener(this);
		meExit.addActionListener(this);
		btFond.addActionListener(this);
		meChat.addActionListener(this);
		btChange.addActionListener(this);
		meLookfreng.addActionListener(this);
		labMyifo.addMouseListener(this);
		meDelfrend.addActionListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		
		setSize(300, 700);   
		setResizable(false);
		//设置没有边框
		setUndecorated(true);
		setVisible(true);
		setLocation(1050, 10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btMin){
			SystemTray Tray = SystemTray.getSystemTray();
			if(trayicon!=null){
				//将托盘移出
				Tray.remove(trayicon);
			}
			try {
				Tray.add(trayicon);
				MainUI.this.setVisible(false);
			} catch (AWTException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==btChange){
			//创建一个文件选择框
			JFileChooser jfc = new JFileChooser();
			//设置文件选择框的风格
			jfc.setDialogType(JFileChooser.OPEN_DIALOG);
			//设置文件选择框的选中模式
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//设置文件选择框的窗口名
			jfc.setDialogTitle("更换皮肤");
			/*showOpenDialog(this)显示当前的文本选择框
			 * 1.如果点击了其中某一个文件，此方法就会返回一个APPROVE_OPTION的常量
			 * 2.如果返回了，就代表被选中了
			 * */
			if(jfc.showOpenDialog(this)==jfc.APPROVE_OPTION){
				//getSelectedFile();得到文件
				File file = jfc.getSelectedFile();
				//根据文件名去获取该文件的路径
				String path  = file.getPath();
				//根据你选中的文件去设置背景图片
				bgphoto.setIcon(new ImageIcon(path));
			}
		}
		if(e.getSource()==btClose){
			account.changeStement(Myifo.getQQid(),cmd.STATUS[1]);
			SendCmd.sendAll(VallInfo, Myifo, cmd.CMD_OFFLINE);
			System.exit(0);
		}
		
		if(e.getSource()==btFond){
			new ListFrendUI(Myifo);
			
		}
		if(e.getSource()==meDelfrend){
			account.delFreand(Myifo.getQQid(),friendifo.getQQid());
			SendMsg msg = new SendMsg();
			msg.Cmd = cmd.CMD_DELFRIEND;
			msg.myIfo= Myifo;
			msg.frendIfo= friendifo;
			SendCmd.send(msg);
			reflush();
		}
		if(e.getSource()==memoveFrend){
			account.moveGroup(Myifo.getQQid(), friendifo.getQQid(),cmd.GROUPNAME[0]);
			reflush();
		}
		if(e.getSource()==meLookfreng){
			new LookFrend(friendifo);
		}
		if(e.getSource()==meChat){
			OpenChat();
		}
		else if(e.getSource()==memoveFamily){
			account.moveGroup(Myifo.getQQid(), friendifo.getQQid(), cmd.GROUPNAME[2]);
			reflush();
		}
		else if(e.getSource()==memoveMate){
			account.moveGroup(Myifo.getQQid(),friendifo.getQQid(),cmd.GROUPNAME[1]);
			reflush();
		}
		else if(e.getSource()==memoveBlack){
			account.moveGroup(Myifo.getQQid(),friendifo.getQQid(),cmd.GROUPNAME[3]);
			reflush();
		}
		else if(e.getSource()==meOpen){
			SystemTray tray = SystemTray.getSystemTray();
			tray.remove(trayicon);
			MainUI.this.setVisible(true);
			MainUI.this.setState(JFrame.NORMAL);
		}
		else if(e.getSource()==meOnlien){
			String Stement = cmd.STATUS[0];
			ChangStement(Stement);
			account.changeStement(Myifo.getQQid(),Stement);
		}
		else if(e.getSource()==menotLook){
			String Stement = cmd.STATUS[3];
			ChangStement(Stement);
			account.changeStement(Myifo.getQQid(),Stement);
		}
		else if(e.getSource()==meBuy){
			String Stement = cmd.STATUS[2];
			ChangStement(Stement);
			account.changeStement(Myifo.getQQid(),Stement);
		}
		else if(e.getSource()==meExit){
			account.changeStement(Myifo.getQQid(),cmd.STATUS[1]);
			SendCmd.sendAll(VallInfo, Myifo,cmd.CMD_OFFLINE);
			System.exit(0);
		}
		
			
	}
	public void reflush(){
		VallInfo = account.getMyfriend(Myifo.getQQid());
		VFriend.clear();
		Vfamily.clear();
		Vmate.clear();
		Vblack.clear();
		for(Account acc : VallInfo){
			String groupName = acc.getGroupID();
			if(groupName.equals(cmd.GROUPNAME[0])){
				VFriend.add(acc);
			}else if(groupName.equals(cmd.GROUPNAME[1])){
				Vmate.add(acc);
			}else if(groupName.equals(cmd.GROUPNAME[2])){
				Vfamily.add(acc);
			}else if(groupName.equals(cmd.GROUPNAME[3])){
				Vblack.add(acc);
			}
		}
		friendsList.setModel(new Modeldata(VFriend));
		mateList.setModel(new Modeldata(Vmate));
		fiamlyList.setModel(new Modeldata(Vfamily));
		blackList.setModel(new Modeldata(Vblack));
		
		friendsList.setCellRenderer(new HeadModel(VFriend));
		mateList.setCellRenderer(new HeadModel(Vmate));
		fiamlyList.setCellRenderer(new HeadModel(Vfamily));
		blackList.setCellRenderer(new HeadModel(Vblack));
		
		
	}
	public void ChangStement(String Stement){
		String friendphoto = Myifo.getPhoto();
		String myphoto = Myifo.getPhoto();
		if(Stement.equals(cmd.STATUS[0])){
			friendphoto=myphoto;
		}
		else if(Stement.equals(cmd.STATUS[1])){
			int pos = myphoto.indexOf('.');
			String pre = myphoto.substring(0, pos);
			String fox = myphoto.substring(pos,myphoto.length());
			friendphoto = pre+"_h"+fox;
		}
		else if(Stement.equals(cmd.STATUS[2])){
			int pos = myphoto.indexOf('.');
			String pre = myphoto.substring(0,pos);
			String fox = myphoto.substring(pos,myphoto.length());
			friendphoto= pre+"_l"+fox;
		}
		else if(Stement.equals(cmd.STATUS[3])){
			int pos = myphoto.indexOf('.');
			String pre = myphoto.substring(0,pos);
			String fox = myphoto.substring(pos, myphoto.length());
			friendphoto= pre+ "_h" + fox;
		}
		labMyifo.setIcon(new ImageIcon(friendphoto));
	}
	public void creatMenu(){
		popupmenu = new JPopupMenu();
		meChat = new JMenuItem("聊天");
		memoveFrend = new JMenuItem("移至好友分组");
		memoveMate = new JMenuItem("移至同学分组");
		memoveFamily = new JMenuItem("移至家人分组");
		memoveBlack = new JMenuItem("移至黑名单");
		meLookfreng = new JMenuItem("查找好友资料");
		meDelfrend  = new JMenuItem("删除好友");
		
		popupmenu.add(meChat);
		popupmenu.add(memoveFrend);
		popupmenu.add(memoveMate);
		popupmenu.add(memoveFamily);
		popupmenu.add(memoveBlack);
		popupmenu.add(meLookfreng);
		popupmenu.add(meDelfrend);
		
	}
	public void CreateTray(){
		poputray = new PopupMenu();
		meOpen = new MenuItem("打开");
		meOnlien = new MenuItem("在线");
		menotLook = new MenuItem("隐身");
		meBuy = new MenuItem("忙碌");
		meExit = new MenuItem("退出");
		
		poputray.add(meOpen);
		poputray.add(meOnlien);
		poputray.add(menotLook);
		poputray.add(meBuy);
		poputray.add(meExit);
	}
	public void setTray(){
		Image image = new ImageIcon("images/QQ3.png").getImage();
		trayicon = new TrayIcon(image,"QQ",poputray);
		trayicon.setImageAutoSize(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==friendsList){
			if(friendsList.getSelectedIndex()>=0){
					friendifo = VFriend.get(friendsList.getSelectedIndex());
			}
		if(e.getButton()==3){
			if(friendsList.getSelectedIndex()>=0){
				popupmenu.show(friendsList,e.getX(), e.getY());
			}
		}
		}else if(e.getSource()==fiamlyList){
			if(fiamlyList.getSelectedIndex()>=0){
				friendifo = Vfamily.get(fiamlyList.getSelectedIndex());
			}
			if(e.getButton()==3){
				if(fiamlyList.getSelectedIndex()>=0){
					popupmenu.show(fiamlyList, e.getX(), e.getY());
				}
			}
			
		}
		else if(e.getSource()==mateList){
			if(mateList.getSelectedIndex()>=0){
				friendifo= Vmate.get(mateList.getSelectedIndex());
			}
			if(e.getButton()==3){
				if(mateList.getSelectedIndex()>=0){
					popupmenu.show(mateList, e.getX(), e.getY());
				}
			}
		}
		else if (e.getSource()==blackList){
			if(blackList.getSelectedIndex()>=0){
				friendifo = Vblack.get(blackList.getSelectedIndex());
			}
			if(e.getButton()==3){
				if(blackList.getSelectedIndex()>=0){
					popupmenu.show(blackList, e.getX(),e.getY());
				}
			}
		}
}
	@SuppressWarnings("deprecation")
	public ChatUI OpenChat(){
		ChatUI chat =chatWin.get(friendifo.getQQid());
		if(chat==null){
			chat = new ChatUI(Myifo,friendifo,Vfamily);
			chatWin.put(friendifo.getQQid(),chat);
		}
		chat.show();
		return chat;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		origin.x=e.getX();
		origin.y=e.getY();
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==labMyifo){
			new UpdateUI(Myifo,this);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = this.getLocation();
		this.setLocation(p.x+e.getX()-origin.x, p.y+e.getY()-origin.y);
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	class addThread extends Thread{
		public addThread(){
			
		}
		@SuppressWarnings("static-access")
		public void run(){
			try {
				DatagramSocket socket = new DatagramSocket(Myifo.getPort());
				while(true){
					byte[] b = new byte[1024*520];
					DatagramPacket packet = new DatagramPacket(b,0,b.length);
					socket.receive(packet);
					ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
					ObjectInputStream ois = new ObjectInputStream(bis);
					SendMsg msg = (SendMsg) ois.readObject();
					Myifo= msg.frendIfo;
					friendifo=msg.myIfo;
					switch(msg.Cmd){
					  case cmd.CMD_ADDFRI:
						  String str ="【"+friendifo.getInfoname()+"】请求添加你为好友，是否同意";
						  SendMsg msg1 = new SendMsg();
						  if(JOptionPane.showConfirmDialog(null, str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							  msg1.Cmd =cmd.CMD_ARGEE;
							 account.addFrend(Myifo.getQQid(),friendifo.getQQid());
							 reflush();
						  }else{
							  msg1.Cmd=cmd.CMD_REFUSE;
						  }
						  msg1.myIfo=Myifo;
						  msg1.frendIfo=friendifo;
						  SendCmd.send(msg1);
						  break;
					  case  cmd.CMD_ARGEE:
						  reflush();
						  break;
					  case  cmd.CMD_REFUSE:
						  String str1 ="【"+friendifo.getInfoname()+"】拒绝你的好友请求";
						  JOptionPane.showMessageDialog(null,str1);
					  case cmd.CMD_DELFRIEND :
						  reflush();
						  break;
					 //发送消息
					  case cmd.CMD_SEND :
						  new Sound(msg.Cmd);
						ChatUI chat =OpenChat();
						try {
							chat.appendView(msg.myIfo.getInfoname(), msg.doc);
						} catch (BadLocationException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						break;
					  //接收抖动
					  case cmd.CMD_SHAKE:
						  //打开一个聊天窗口
						  
						  chat=OpenChat();
						  new Sound(msg.Cmd);
						  //调用抖动的方法
						  chat.shake();
						  break;
					  case cmd.CMD_ONLINE :
						  //有好友上线就传递一个声音
						  new Sound(msg.Cmd);
						  reflush();
						  new StementUI(friendifo);
						  break;
					  case cmd.CMD_OFFLINE :
						  new Sound(msg.Cmd);
						  reflush();
						  friendifo.setStement("离线");
						  new StementUI(friendifo);
						  break;
					  case cmd.CMD_FILE :
						   str = friendifo.getInfoname()+"发送了一个【"+msg.fielNaem+"文件】，是否接收？";
						   //判断文件是否接收
						   if(JOptionPane.showConfirmDialog(null, "是否接收文件","接收文件",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							//打开文本选择框
							   JFileChooser jfile = new JFileChooser(" ");
							   jfile.setDialogType(JFileChooser.SAVE_DIALOG);
							   jfile.setDialogTitle("保存文件");
						   
						   //打开文件选择框和保存路径
							   if(jfile.showOpenDialog(null)==jfile.APPROVE_OPTION){
								   String ext = msg.fielNaem.substring(msg.fielNaem.indexOf('.'),msg.fielNaem.length());
								   String fileName = jfile.getSelectedFile().getAbsolutePath()+ext;
								   FileOutputStream fos = new FileOutputStream(fileName);
								   fos.write(msg.b);
								   fos.flush();
								   fos.close();
							   }
						   
						   }
					}
				}
			} catch (SocketException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	
}
@SuppressWarnings("serial")
class Modeldata extends AbstractListModel{
	Vector<Account> data;
	public Modeldata(){
	}
	public Modeldata(Vector<Account> data){
		this.data=data;
	}
	public Object getElementAt(int index) {
		Account info = data.get(index);
		return info.getInfoname()+"("+info.getQQid()+")"+info.getPrename()+"";
	}

	//获取当前向量的大小
	public int getSize() {
		return data.size();
	}
	
}

class HeadModel extends DefaultListCellRenderer{
	Vector<Account> datas;
	public HeadModel(Vector<Account> datas){
		this.datas= datas;
	}
	@SuppressWarnings("unused")
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		Component c = super.getListCellRendererComponent(list, value,
				index, isSelected, cellHasFocus);
		if(index >=0 && index<datas.size()){
			Account user = datas.get(index);
			String 	stement = user.getStement();
			String  headphoto = user.getPhoto();
			String  friendphoto = "";
			if(stement.equals(cmd.STATUS[0])){
				friendphoto = headphoto;
			}
			else if(stement.equals(cmd.STATUS[1])){
				int pos = headphoto.indexOf('.');
				String pre = headphoto.substring(0, pos);
				String fox = headphoto.substring(pos, headphoto.length());
				friendphoto = pre+"_h"+fox;
			}
			else if(stement.equals(cmd.STATUS[2])){
				int pos = headphoto.indexOf('.');
				String pre = headphoto.substring(0,pos);
				String fox  = headphoto.substring(pos,headphoto.length());
				friendphoto = pre + "_l"+fox;
			}
			else if(stement.equals(cmd.STATUS[3])){
				int pos = headphoto.indexOf('.');
				String pre = headphoto.substring(0,pos);
				String fox = headphoto.substring(pos,headphoto.length());
				friendphoto = pre+"_h"+fox;
			}
			setIcon(new ImageIcon(friendphoto));
		}
		setOpaque(false);
		return this;
	}
				
}
