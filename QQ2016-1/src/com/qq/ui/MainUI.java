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
	//����ͼƬ��ͷ��
	JLabel bgphoto,labMyifo,labTop,labBottom;
	//������С���͹ر�
	JLabel lblMin,lblClose;
	//����ѡ����
	JTabbedPane tabList;
	//�����嵥�б�
	JList friendsList,mateList,fiamlyList,blackList;
	//���ò���,�޸�,��С�����رհ�ť
	JButton btFond,btChange,btMin,btClose,btphoto;
	//����״̬��������
	JComboBox cbStatment;
	//�����嵥
	JPopupMenu popupmenu;
	//�����嵥�����Item
	JMenuItem meChat,memoveFrend,memoveMate,memoveFamily,memoveBlack,meLookfreng,meDelfrend;
	//��������
	TrayIcon trayicon;
	//��������������б�
	PopupMenu poputray;
	MenuItem  meOpen,meExit,meOnlien,meBuy,menotLook;
	//Ϊÿһ���б����������
	Vector<Account> VallInfo,VFriend,Vfamily,Vmate,Vblack;
	
	HashMap<Integer, ChatUI> chatWin = new HashMap<Integer,ChatUI>();
	//������DAOģ�ͣ����ó��󷽷�
	AccountDao account = new AccountDaoimpl();
	//ʵ�ִ��ڵ��϶�
	static Point origin = new Point(); 
	
	@SuppressWarnings({ "static-access", "unused" })
	public MainUI(Account a){
		this.Myifo=a;
		//setResizable(false);
		//����ͼ��
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		//���ñ���ͼƬ
		bgphoto = new  JLabel(new ImageIcon("images/142.jpg"));
		add(bgphoto);
		bgphoto.setLayout(new BorderLayout());
		
		//�ڱ��������������һ�����
		JPanel Top = new JPanel(new BorderLayout() );
		//Font font = new Font("����",Font.BOLD,12);
		labTop = new JLabel(new ImageIcon("images/top1.png"));
		labTop.setLayout(null);
		btMin= new JButton();
		
		btMin.setForeground(Color.white);
		//���ð�ť�ı�����ɫΪ��ɫ
		btMin.setBackground(Color.WHITE);
		//���ñ�����ɫΪ��
		btMin.setOpaque(false);
		//���ð�ťû�б߿�
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
		String all = Myifo.getInfoname()+"��"+Myifo.getPrename()+"��";
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
		
		//���б�����Ϊ͸��
		friendsList.setOpaque(false);
		mateList.setOpaque(false);
		fiamlyList.setOpaque(false);
		blackList.setOpaque(false);
		
		//����ѡ����Ϊ͸��
		UIManager.put("TabbedPane.contentOpaque", false);
		/**
		 * tabPanel����ѡ����
		 * 1.��ν��ѡ������ǿ��԰Ѽ���������һ�������л�
		 * */
		tabList = new JTabbedPane();
		tabList.setOpaque(false);
		tabList.add("����",friendsList);
		tabList.add("ͬѧ",mateList);
		tabList.add("����",fiamlyList);
		tabList.add("������",blackList);
		
		VallInfo = new Vector<Account>();
		VFriend = new Vector<Account>();
		Vfamily = new Vector<Account>();
		Vmate  = new Vector<Account>();
		Vblack = new Vector<Account>();
		
		reflush();
		
		
		
		bgphoto.add(tabList,BorderLayout.CENTER);
		
		/*JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btChange = new JButton("����");
		btFond = new 	JButton("����");
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
		//���ð�ť�ı�����ɫΪ��ɫ
		btChange.setBackground(Color.WHITE);
		//���ñ�����ɫΪ��
		btChange.setOpaque(false);
		//���ð�ťû�б߿�
		btChange.setBorderPainted(false);
		btChange.setBounds(170,0,60,30);
		labBottom.add(btChange);
		btFond  = new JButton();
		btFond.setForeground(Color.white);
		//���ð�ť�ı�����ɫΪ��ɫ
		btFond.setBackground(Color.WHITE);
		//���ñ�����ɫΪ��
		btFond.setOpaque(false);
		//���ð�ťû�б߿�
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
		//����û�б߿�
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
				//�������Ƴ�
				Tray.remove(trayicon);
			}
			try {
				Tray.add(trayicon);
				MainUI.this.setVisible(false);
			} catch (AWTException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==btChange){
			//����һ���ļ�ѡ���
			JFileChooser jfc = new JFileChooser();
			//�����ļ�ѡ���ķ��
			jfc.setDialogType(JFileChooser.OPEN_DIALOG);
			//�����ļ�ѡ����ѡ��ģʽ
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//�����ļ�ѡ���Ĵ�����
			jfc.setDialogTitle("����Ƥ��");
			/*showOpenDialog(this)��ʾ��ǰ���ı�ѡ���
			 * 1.������������ĳһ���ļ����˷����ͻ᷵��һ��APPROVE_OPTION�ĳ���
			 * 2.��������ˣ��ʹ���ѡ����
			 * */
			if(jfc.showOpenDialog(this)==jfc.APPROVE_OPTION){
				//getSelectedFile();�õ��ļ�
				File file = jfc.getSelectedFile();
				//�����ļ���ȥ��ȡ���ļ���·��
				String path  = file.getPath();
				//������ѡ�е��ļ�ȥ���ñ���ͼƬ
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
		meChat = new JMenuItem("����");
		memoveFrend = new JMenuItem("�������ѷ���");
		memoveMate = new JMenuItem("����ͬѧ����");
		memoveFamily = new JMenuItem("�������˷���");
		memoveBlack = new JMenuItem("����������");
		meLookfreng = new JMenuItem("���Һ�������");
		meDelfrend  = new JMenuItem("ɾ������");
		
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
		meOpen = new MenuItem("��");
		meOnlien = new MenuItem("����");
		menotLook = new MenuItem("����");
		meBuy = new MenuItem("æµ");
		meExit = new MenuItem("�˳�");
		
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
		// TODO �Զ����ɵķ������
		
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
						  String str ="��"+friendifo.getInfoname()+"�����������Ϊ���ѣ��Ƿ�ͬ��";
						  SendMsg msg1 = new SendMsg();
						  if(JOptionPane.showConfirmDialog(null, str,"��Ӻ���",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
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
						  String str1 ="��"+friendifo.getInfoname()+"���ܾ���ĺ�������";
						  JOptionPane.showMessageDialog(null,str1);
					  case cmd.CMD_DELFRIEND :
						  reflush();
						  break;
					 //������Ϣ
					  case cmd.CMD_SEND :
						  new Sound(msg.Cmd);
						ChatUI chat =OpenChat();
						try {
							chat.appendView(msg.myIfo.getInfoname(), msg.doc);
						} catch (BadLocationException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
						break;
					  //���ն���
					  case cmd.CMD_SHAKE:
						  //��һ�����촰��
						  
						  chat=OpenChat();
						  new Sound(msg.Cmd);
						  //���ö����ķ���
						  chat.shake();
						  break;
					  case cmd.CMD_ONLINE :
						  //�к������߾ʹ���һ������
						  new Sound(msg.Cmd);
						  reflush();
						  new StementUI(friendifo);
						  break;
					  case cmd.CMD_OFFLINE :
						  new Sound(msg.Cmd);
						  reflush();
						  friendifo.setStement("����");
						  new StementUI(friendifo);
						  break;
					  case cmd.CMD_FILE :
						   str = friendifo.getInfoname()+"������һ����"+msg.fielNaem+"�ļ������Ƿ���գ�";
						   //�ж��ļ��Ƿ����
						   if(JOptionPane.showConfirmDialog(null, "�Ƿ�����ļ�","�����ļ�",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							//���ı�ѡ���
							   JFileChooser jfile = new JFileChooser(" ");
							   jfile.setDialogType(JFileChooser.SAVE_DIALOG);
							   jfile.setDialogTitle("�����ļ�");
						   
						   //���ļ�ѡ���ͱ���·��
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
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

	//��ȡ��ǰ�����Ĵ�С
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
