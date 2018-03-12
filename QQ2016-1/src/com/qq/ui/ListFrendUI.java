package com.qq.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.qq.base.SendCmd;
import com.qq.base.SendMsg;
import com.qq.base.cmd;
import com.qq.beens.Account;
import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

@SuppressWarnings("serial")
public class ListFrendUI extends JFrame implements ActionListener,MouseListener,MouseMotionListener{
	Account myifo;
	JLabel lablQQcode,labNickName,labAge;
	JButton btfind,btMin,btClose,btnClose,btnAdd;
	JLabel bgphoto;
	//表的组件
	JTable dataTable;
	JTextField findthing,fingName,finfage;
	JList frendlist;
	JComboBox cbSex,cbStement;
	/*定义了两个向量来保存表里面的头部和表里面的内容
	 * 保存头部的内容是返回了一个字符串类型
	 * 
	 * */
	Vector<String> vHead;
	@SuppressWarnings("rawtypes")
	Vector  vfrendlist;
	String sSex[] = {"不选择","男","女"};
	String sStement[]={"不选择","在线","离线","忙碌","隐身"};
	//实现了持久层
	AccountDao account = new AccountDaoimpl();
	static Point origin = new Point(); 
	public  ListFrendUI(Account a){
		this.myifo = a ;
		Toolkit t = Toolkit.getDefaultToolkit();
		Image image = t.getImage("images/QQ3.png");
		this.setIconImage(image);
		bgphoto = new JLabel(new ImageIcon("images/183.jpg"));
		add(bgphoto);
		bgphoto.setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.setOpaque(false);
		lablQQcode = new JLabel("QQ号码");
		labNickName = new JLabel("昵称");
		labAge = new JLabel("年龄");
		finfage = new JTextField(5);
		cbSex = new JComboBox(sSex);
		cbStement= new JComboBox(sStement);
		findthing = new JTextField(5);
		fingName = new JTextField(10);
		btfind = new JButton("搜索");
		btfind.setOpaque(false);
		btfind.setForeground(Color.blue);
		//设置按钮的背景颜色为白色
		btfind.setBackground(Color.WHITE);
		//设置背景颜色为空
		btfind.setOpaque(false);
		top.add(lablQQcode);
		top.add(findthing);
		top.add(labNickName);
		top.add(fingName);
		top.add(labAge);
		top.add(finfage);
		top.add(cbSex);
		top.add(cbStement);
		top.add(btfind);
		bgphoto.add(top,BorderLayout.NORTH);
		
		String[] head = {"头像", "QQ号码", "昵称", "状态", "年龄","性别","星座","血型","名族","爱好","个性签名"};
		vHead = new Vector<String>();
		for(int i=0;i<head.length;i++){
			vHead.addElement(head[i]);
		}
		String sql = "select photo,QQid,infoname,stement,age,sex,constellation,boold,mzhome,interst,prename from account";
		vfrendlist = account.findFrend(sql);
		//vfrendlist.setOpaque(false);
		
		dataTable = new JTable(new datamadel(vHead,vfrendlist));
		dataTable.addMouseListener(this);
		dataTable.setRowHeight(60);
		
		/**
		 * 把表格增加到一个滚动面板上
		 * */
		bgphoto.add(new JScrollPane(dataTable),BorderLayout.CENTER);
		dataTable.setOpaque(false);
		
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAdd = new JButton("添加好友");
		btnClose = new JButton("关闭窗口");
		bottom.add(btnAdd);
		bottom.add(btnClose);
		bottom.setOpaque(false);
		bgphoto.add(bottom, BorderLayout.SOUTH);
		
		
		dataTable.addMouseListener(this);
		btfind.addActionListener(this);
		btnClose.addActionListener(this);
		btnAdd.addActionListener(this);
		bgphoto.addMouseListener(this);
		bgphoto.addMouseMotionListener(this);
		setSize(900,450);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setLocationRelativeTo(null);
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==dataTable){
			int index = dataTable.getSelectedRow();
			if(index>=0){
				if(e.getClickCount()==2){
					Vector row = (Vector)vfrendlist.get(index);
					int qqid = Integer.parseInt(row.get(1).toString());
					if(qqid==myifo.getQQid()){
						JOptionPane.showMessageDialog(this,"不能添加自己为好友");
						return;
					}
					if(account.isFrend(myifo.getQQid(), qqid)){
						JOptionPane.showMessageDialog(this,"你们已经是好友了");
						return;
					}
					Account frendIfo = account.getSelectedFriend(qqid);
					String str ="是否添加【"+frendIfo.getInfoname()+"】为你的好友？";
					if(JOptionPane.showConfirmDialog(this, str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						SendMsg msg = new SendMsg();
						msg.Cmd = cmd.CMD_ADDFRI;
						msg.myIfo=myifo;
						msg.frendIfo=frendIfo;
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
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		origin.x = e.getX(); 
		origin.y = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p =this.getLocation(); 
		// 设置窗口的位置
		// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
		this.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
				- origin.y);
	}
		
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	

	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnClose){
			dispose();
		}
		if(e.getSource()==btfind){
		
				
			String sql = "select photo,QQid,infoname,stement,age,sex,constellation,boold,mzhome,interst,prename from account";
				sql += " where 1=1 ";
				String qqcode = findthing.getText().trim();
				String nickname = fingName.getText().trim();
				String age = finfage.getText().trim();
				
				/**
				 * 如果查询面板上的qq号码填了内容
				 * */
				if(!qqcode.equals("")){
					sql += " and QQid=" + qqcode ;
				}
				if(!nickname.equals("")){
					sql += " and infoname like '%"+nickname + "%'";
				}
				if(!age.equals("")){
					sql += " and age="+age;
				}
				String sex = sSex[cbSex.getSelectedIndex()];
				String status = sStement[cbStement.getSelectedIndex()];
				
				if(!sex.equals("不选择")){
					sql += " and sex='"+sex + "'";
				}
				if(!status.equals("不选择")){
					sql += " and stement='"+status + "'";
				}
				sql += " order by age";
				/**
				 * 调用持久层的查询好友方法
				 * 把SQL作为一个参数传递
				 * 因为在这个界面要拼接SQL
				 * */
				vfrendlist = account.findFrend(sql);
				/**
				 * 重新设置JTable里面的数据模型
				 * */
				dataTable.setModel(new datamadel(vHead,vfrendlist));
		}if(e.getSource()==btnAdd){
			int index = dataTable.getSelectedRow();
			if(index>=0){
				Vector row = (Vector)vfrendlist.get(index);
				int qqid = Integer.parseInt(row.get(1).toString());
				if(qqid==myifo.getQQid()){
					JOptionPane.showMessageDialog(this,"不能添加自己为好友");
					return;
				}
				if(account.isFrend(myifo.getQQid(), qqid)){
					JOptionPane.showMessageDialog(this,"你们已经是好友了");
					return;
				}
				Account frendifo = account.getSelectedFriend(qqid);
				String str ="是否添加【"+frendifo.getInfoname()+"】为你的好友？";
				if(JOptionPane.showConfirmDialog(this, str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){}
					SendMsg msg = new  SendMsg();
					msg.Cmd = cmd.CMD_ADDFRI;
					msg.myIfo=myifo;
					msg.frendIfo=frendifo;
					SendCmd.send(msg);
			}
		}
	}
	 class datamadel extends AbstractTableModel {
	    	Vector<String> vHead;
	    	@SuppressWarnings("rawtypes")
			Vector data;
	        @SuppressWarnings("rawtypes")
			public datamadel(Vector<String> vHead,Vector data) {
	        	this.vHead = vHead;
	        	this.data = data;
	        }
	        /**
	         * 得到表格列长度
	         * */
	        public int getColumnCount() {
	            return vHead.size();
	        }
	        /**
	         * 得到表格行的长度
	         * */
	        public int getRowCount() {
	            return data.size();
	        }
	        /**
	         * 根据序号获取到列名
	         * */
	        public String getColumnName(int col) {
	            return vHead.get(col);
	        }
	        /**
	         * 根据行列获取到单元格的信息
	         * */
	        @SuppressWarnings("rawtypes")
			public Object getValueAt(int row, int col) {
	        	/**
	        	 * 获取到第几行的数据
	        	 * 返回值是一个向量
	        	 * */
	        	Vector rowData = (Vector)vfrendlist.get(row);
				/**
				 * 如果是第一列返回头像的对象
				 * */
	        	if(col==0){
					return new ImageIcon(rowData.get(col).toString());
				}else{
					return rowData.get(col);
				}
	        }
	        /**
	         * 返回指定列的class对象
	         * */
	        @Override
			public Class<?> getColumnClass(int c) {
	        	if(c==0){
					return ImageIcon.class;
				}else{
					return super.getColumnClass(c);
				}
	        }
	        /**
	         * 设置表格是否能修改
	         * */
	        public boolean isCellEditable(int row, int col) {
	            return false;
	        }
	        /**
	         * 根据行列设置单元格的值
	         * */
	        @SuppressWarnings({ "rawtypes", "unchecked" })
			public void setValueAt(Object value, int row, int col) {
	        	Vector rowData = (Vector)vfrendlist.get(row);
	        	rowData.set(col, value);
	        	/**
	        	 * 更新表格
	        	 * 调用的是父类的方法
	        	 * */
	            fireTableCellUpdated(row, col);  
	        }
	    }
	
}
	