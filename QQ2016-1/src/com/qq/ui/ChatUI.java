package com.qq.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.qq.base.SendCmd;
import com.qq.base.SendMsg;
import com.qq.base.cmd;
import com.qq.beens.Account;

@SuppressWarnings("serial")
public class ChatUI extends JFrame implements ActionListener,ItemListener,MouseListener,MouseMotionListener{
	Account Myifo,frendifo;
	Vector<Account>  vfamily;
	JLabel bgphoto,sendTitlephoto,sentBophoto;
	JLabel title,titlename,titlepname;
	JTextPane receivepanel,textsned;
	JPanel sendpanel;
	JButton frendphoto,btsend,btclose;
	JButton btnShake,btnFile,btnColor,btnFace;
	String sFont[] = {"����","����","����","����"};
	String sSize[]={"8","10","12","14","16","18","24","28","32","36","72"};
	Font font;
	Point orgin = new Point();
	
	/**
	 * ����ʹ�С
	 * */
	JComboBox cbFont,cbSize;
	@SuppressWarnings("unused")
	public ChatUI(Account Myifo,Account frendifo,Vector<Account> vfamily){
		this.Myifo=Myifo;
		this.frendifo=frendifo;
		this.vfamily=vfamily;
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		bgphoto = new JLabel(new ImageIcon("images/lt1.png"));
		add(bgphoto);
		bgphoto.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		/*top.setLayout(null);
		frendphoto = new JButton(new ImageIcon(frendifo.getPhoto()));
		*//**
		 * ���ð�ť�߿�ͱ�ǩ֮��Ŀհ�
		 * *//*
		frendphoto.setMargin(new Insets(0,0,0,0));
		String str = "��"+frendifo.getInfoname()+"��";
		String str1 = frendifo.getPrename();
		titlename = new JLabel(str);
		titlepname = new JLabel(str1);
		
		frendphoto.setBounds(15,5,73,73);
		titlename.setBounds(90,5,10,15);
		titlepname.setBounds(92,23,100,15);
		top.add(frendphoto);
		top.add(titlepname);
		top.add(titlename);*/
		String str = "("+frendifo.getInfoname()+")";
		str+="��"+ frendifo.getPrename()+"��";
		title = new JLabel(str,new ImageIcon(frendifo.getPhoto()),JLabel.LEFT);
		bgphoto.add(title,BorderLayout.NORTH);
		
		receivepanel = new JTextPane();
		receivepanel.setOpaque(false);
		/*JScrollPane scorllpane = new JScrollPane(receivepanel);
		scorllpane.setBackground(Color.white);
		*/
		textsned = new JTextPane();
		sendpanel = new JPanel();
		sendpanel.setOpaque(false);
		textsned.setOpaque(false);
		JPanel centenr = new JPanel(new GridLayout(2,1,1,1));
		//centenr.add(new JScrollPane(receivepanel));
		centenr.add(receivepanel);
		centenr.add(sendpanel);
		centenr.setOpaque(false);
		
		sendpanel.setLayout(new BorderLayout());
		
		JPanel sendtitle = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		cbFont = new JComboBox(sFont);
		cbSize = new JComboBox(sSize);
		btnShake = new JButton(new ImageIcon("images/zd.png"));
		/**
		 * ���ð�ť�߿�ͱ�ǩ֮��Ŀհ�
		 * */
		btnShake.setMargin(new Insets(0,0,0,0));
		btnFile = new JButton("�ļ�");
		btnColor = new JButton("��ɫ");
		btnFace = new JButton(new ImageIcon("images/sendFace.png"));
		btnFace.setMargin(new Insets(0,0,0,0));
		sendtitle.add(cbFont);
		sendtitle.add(cbSize);
		sendtitle.add(btnShake);
		sendtitle.add(btnFace);
		sendtitle.add(btnColor);
		sendtitle.add(btnFile);
		
		
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		btsend = new JButton("����");
		btclose = new JButton("�ر�");
		bottom.add(btsend);
		bottom.add(btclose);
		
		
		sendpanel.add(sendtitle,BorderLayout.NORTH);
		sendpanel.add(textsned, BorderLayout.CENTER);
		sendpanel.add(bottom,BorderLayout.SOUTH);
		bgphoto.add(centenr,BorderLayout.CENTER);
		
		
		btclose.addActionListener(this);
		btsend.addActionListener(this);
		btnShake.addActionListener(this);
		btnFile.addActionListener(this);
		btnFace.addActionListener(this);
		btnColor.addActionListener(this);
		cbFont.addItemListener(this);
		cbSize.addItemListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setSize(388,505);
		
		setResizable(false);
		//����û�б߿�
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	@SuppressWarnings({ "static-access", "deprecation", "resource" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btclose){
			dispose();
		}
		else if(e.getSource()==btsend){
			if(textsned.getText().equals("")){
				JOptionPane.showMessageDialog(this,"��������Ҫ���͵�����");
				return;
			}
			try {
				appendView(Myifo.getInfoname(),textsned.getStyledDocument());
				if(frendifo.getGroupID()!=null&&frendifo.getGroupID().equals(cmd.GROUPNAME[2])){
					//SendCmd.sendAll(familyGroup, myInfo, Cmd.CMD_SEND,txtSend.getStyledDocument());
				}else{
					SendMsg msg = new SendMsg();
					msg.Cmd=cmd.CMD_SEND;
					msg.myIfo=Myifo;
					msg.frendIfo=frendifo;
					msg.doc=textsned.getStyledDocument();
					SendCmd.send(msg);
				}
				textsned.setText("");
			} catch (BadLocationException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==btnShake){
			SendMsg msg = new SendMsg();
			msg.Cmd = cmd.CMD_SHAKE;
			msg.myIfo=Myifo;
			msg.frendIfo=frendifo;
			SendCmd.send(msg);
			shake();
		}
		else if(e.getSource()==btnFace){
			int x;
			int y;
			x=this.getLocation().x+220;
			y=this.getLocation().y-28;
			new FaceUI(this, x, y);

		}
		else if(e.getSource()==btnColor){
			JColorChooser colorlg = new JColorChooser();
			Color color= colorlg.showDialog(this, "��ѡ��������ɫ", Color.BLACK);
			textsned.setForeground(color);
 
		}
		else if(e.getSource()==btnFile){
			FileDialog filelg = new FileDialog(this,"��ѡ��Ҫ���͵��ļ�(64K����)",FileDialog.LOAD);
			filelg.show();
			String filename = filelg.getDirectory() + filelg.getFile();
			if(filename!=null&&!filename.equals("")&&!filename.equals("nullnull")){
				try {
					FileInputStream fi = new FileInputStream(filename);
					int size  = fi.available();
					byte[] b = new byte[size];
					fi.read(b);
					SendMsg msg = new SendMsg();
					msg.Cmd=cmd.CMD_FILE;
					msg.myIfo = Myifo;
					msg.frendIfo = frendifo;
					msg.b=b;
					msg.fielNaem=filelg.getFile();
					SendCmd.send(msg);
					
				} catch (FileNotFoundException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
			}
		}
	}
	public void shake(){
		int x = this.getLocation().x;
		int y = this.getLocation().y;
		for(int i =0 ;i<20;i++){
			if(i%2==0){
				this.setLocation(x+2,y+2);
			}else {
				this.setLocation(x-2, y-2);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==cbFont){
			setFont();
		}
		if(e.getSource()==cbSize){
			setFont();
		}
		
	}
	public void setFont(){
	
		String Sf = sFont[cbFont.getSelectedIndex()];
		String Ss = sSize[cbSize.getSelectedIndex()];
		font = new Font(Sf,Font.PLAIN,Integer.parseInt(Ss));
		textsned.setFont(font);
		
	}
	public void appendView(String name,StyledDocument xx) throws BadLocationException{
		/*1.�ѷ��Ϳ�������ύ�����տ�
		 *2.��ȡ��ԭ�����տ������
		 *3.StyledDoocument�Ķ�����Ƕ�Ӧ�ı�������������
		 * */
		StyledDocument vdoc = receivepanel.getStyledDocument();
		//�ڽ��տ��������ʱ�ڵķ���
		Date date = new Date();
		//�������ڸ�ʽ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = sdf.format(date);
		
		//����һ���յ����Է��
		SimpleAttributeSet as = new SimpleAttributeSet();
		
		//�ڽ������������һ������
		String s = name + "  "+time+"\n";
		//vdoc.getLength()�õ��ı������������ݴ�С
		vdoc.insertString(vdoc.getLength(), s, as);
		
		//����һ������������ѭ������
		int end = 0;
		//�ѷ��Ϳ��ȡ����xx����һ��һ����ȡ�����տ���
		while(end<xx.getLength()){
			//�õ����Ϳ����Ӧ�����Է��
			Element e = xx.getCharacterElement(end);
			//������ʾ���Ӧ�ķ��
			SimpleAttributeSet sia = new SimpleAttributeSet();
			StyleConstants.setForeground(sia,StyleConstants.getForeground(e.getAttributes()));
			StyleConstants.setFontSize(sia,StyleConstants.getFontSize(e.getAttributes()));
			StyleConstants.setFontFamily(sia, StyleConstants.getFontFamily(e.getAttributes()));
			
			//��ȡ�����Ϳ������
			s = e.getDocument().getText(end, e.getEndOffset() - end);
			
			//�ѷ��Ϳ����������ʾ�ڽ��տ���
			if("icon".equals(e.getName())){
				vdoc.insertString(vdoc.getLength(), s, e.getAttributes());
			}
			else {
				vdoc.insertString(vdoc.getLength(), s, sia);
			}
			//getEndOffset()�������ǻ�ȡ��ǰԪ�صĳ���
			end=e.getEndOffset();
		}
			//����һ������
		vdoc.insertString(vdoc.getLength(),"\n",as );
		
		//������ʾ��ͼ���ַ���λ�����ĵ���β���Ա���ͼ����
		receivepanel.setCaretPosition(vdoc.getLength());
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = this.getLocation();
		this.setLocation(p.x+e.getX()-orgin.x, p.y+e.getY()-orgin.y);
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		orgin.x=e.getX();
		orgin.y = e.getY();
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
}
