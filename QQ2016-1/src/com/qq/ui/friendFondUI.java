package com.qq.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qq.base.SendCmd;
import com.qq.base.SendMsg;
import com.qq.base.cmd;
import com.qq.beens.Account;
import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

@SuppressWarnings("serial")
public class friendFondUI extends JFrame implements MouseListener,ActionListener{
	Account myifo;
	JButton btfind,btadd,btClose;
	JLabel bgphoto;
	JTextField findthing,fingName;
	JList frendlist;
	Vector  vfrendlist;
	AccountDao account = new AccountDaoimpl();

	@SuppressWarnings("unchecked")
	public  friendFondUI(Account a){
		this.myifo = a ;
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		bgphoto = new JLabel(new ImageIcon("images/180.jpg"));
		add(bgphoto);
		bgphoto.setLayout(new BorderLayout());
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.setOpaque(false);
		findthing = new JTextField(5);
		fingName = new JTextField(10);
		btfind = new JButton("搜索");
		btfind.setOpaque(false);
		btfind.setForeground(Color.blue);
		//设置按钮的背景颜色为白色
		btfind.setBackground(Color.WHITE);
		//设置背景颜色为空
		btfind.setOpaque(false);
		top.add(findthing);
		top.add(fingName);
		top.add(btfind);
		bgphoto.add(top,BorderLayout.NORTH);
		
		String sql = "select photo,QQid,infoname,stement,age,sex,constellation,boold,mzhome,interst,prename from account";
		vfrendlist = account.findFrend(sql);
		frendlist = new JList();
		frendlist.setModel(new datamadel(vfrendlist)); 
		
		/**
		 * 设置渲染器
		 * */
		frendlist.setCellRenderer(new MyModel(vfrendlist));
		
		frendlist.setOpaque(false);
		frendlist.setBackground(new Color(0, 0, 0, 0));
		bgphoto.add(frendlist,BorderLayout.CENTER);
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btadd = new JButton("添加好友");
		btClose = new JButton("关闭窗口");
		bottom.add(btadd);
		bottom.add(btClose);
		bottom.setOpaque(false);
		bgphoto.add(bottom, BorderLayout.SOUTH);
		
		frendlist.addMouseListener(this);
		btfind.addActionListener(this);
		btClose.addActionListener(this);
		btadd.addActionListener(this);
		setSize(300,450);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==frendlist){
			int index = frendlist.getSelectedIndex();
			
			if(index>=0){
				if(e.getClickCount()==3){
					new frendinforma();
				}
				if(e.getClickCount()==2){
					Vector vlist = (Vector)vfrendlist.get(index);
					int qqid =  Integer.parseInt(vlist.get(1).toString());
					if(qqid==myifo.getQQid()){
						JOptionPane.showMessageDialog(this,"不能添加自己为好友");
						return;
					}
					if(account.isFrend(myifo.getQQid(), qqid)){
						JOptionPane.showMessageDialog(this,"你们已经是好友了");
						return;
					}
					Account frendiFo = account.getSelectedFriend(qqid);
					String str ="是否添加【" +frendiFo.getInfoname()+"】为你的好友？";
					if(JOptionPane.showConfirmDialog(this, str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						SendMsg msg = new SendMsg();
						msg.Cmd = cmd.CMD_ADDFRI;
						msg.myIfo = myifo;
						msg.frendIfo= frendiFo;
						SendCmd.send(msg);
				}
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btfind){
			String sql = "select photo,QQid,infoname,stement,age,sex,constellation,boold,mzhome,interst,prename from account";
			sql += " where 1=1 ";
			String qqid = findthing.getText().trim();
			String name = fingName.getText().trim();
			if(!qqid.equals("")){
				sql += " and QQid=" + qqid ;
			}
			if(!name.equals("")){
				sql += " and infoname like '%"+name + "%'";
			}
			
			vfrendlist = account.findFrend(sql);
			
			frendlist.setModel(new  datamadel(vfrendlist));
		}
		if(e.getSource()==btClose){
			dispose();
		}
		if(e.getSource()==btadd){
			int index = frendlist.getSelectedIndex();
			if(index>=0){
				Vector list = (Vector)vfrendlist.get(index);
				int qqid = Integer.parseInt(list.get(1).toString());
				Account frendIfo = account.getSelectedFriend(qqid);
				String str = "是否添加【"+frendIfo.getGroupID()+"】为你的好友？";
				if(JOptionPane.showConfirmDialog(this, str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					SendMsg msg = new SendMsg();
					msg.Cmd = cmd.CMD_ADDFRI;
					msg.myIfo= myifo;
					msg.frendIfo=frendIfo;
					SendCmd.send(msg);
					
				}
			}
		}
	}
	class datamadel extends AbstractListModel{
		Vector data;
		public datamadel(){
		}
		public datamadel(Vector data){
			this.data=data;
		}
		public Object getElementAt(int index) {
			Vector info =(Vector)vfrendlist.get(index);
			
			
			return info.get(0)+"("+info.get(1)+")"+info.get(2)+"";
		}

		//获取当前向量的大小
		public int getSize() {
			return data.size();
		}
		/*public Class<?> getColumnClass(int c) {
        	if(c==0){
				return ImageIcon.class;
			}else{
				return super.getColumnClass(c);
			}
*/       /*public Class<?> getClass(int c){
    	   if(c==0){
    		   return ImageIcon.class;
    	   }else {
    		   return super.getClass();
    	   }
    	  
       }*/
	}
	class MyModel extends DefaultListCellRenderer{
		Vector<Account> datas;
		public MyModel(Vector<Account> datas){
			this.datas =datas;
		}
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			Component c = super.getListCellRendererComponent(list, value,
					index, isSelected, cellHasFocus);	
			if (index >= 0 && index < datas.size()) {
				Account user =  datas.get(index);
				String headImg = user.getPhoto();
				setIcon(new ImageIcon(headImg));
				}
			setOpaque(false);
			
			return this;
			}
		}
			
	
		
	}


