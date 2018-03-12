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
	//������
	JTable dataTable;
	JTextField findthing,fingName,finfage;
	JList frendlist;
	JComboBox cbSex,cbStement;
	/*��������������������������ͷ���ͱ����������
	 * ����ͷ���������Ƿ�����һ���ַ�������
	 * 
	 * */
	Vector<String> vHead;
	@SuppressWarnings("rawtypes")
	Vector  vfrendlist;
	String sSex[] = {"��ѡ��","��","Ů"};
	String sStement[]={"��ѡ��","����","����","æµ","����"};
	//ʵ���˳־ò�
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
		lablQQcode = new JLabel("QQ����");
		labNickName = new JLabel("�ǳ�");
		labAge = new JLabel("����");
		finfage = new JTextField(5);
		cbSex = new JComboBox(sSex);
		cbStement= new JComboBox(sStement);
		findthing = new JTextField(5);
		fingName = new JTextField(10);
		btfind = new JButton("����");
		btfind.setOpaque(false);
		btfind.setForeground(Color.blue);
		//���ð�ť�ı�����ɫΪ��ɫ
		btfind.setBackground(Color.WHITE);
		//���ñ�����ɫΪ��
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
		
		String[] head = {"ͷ��", "QQ����", "�ǳ�", "״̬", "����","�Ա�","����","Ѫ��","����","����","����ǩ��"};
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
		 * �ѱ�����ӵ�һ�����������
		 * */
		bgphoto.add(new JScrollPane(dataTable),BorderLayout.CENTER);
		dataTable.setOpaque(false);
		
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAdd = new JButton("��Ӻ���");
		btnClose = new JButton("�رմ���");
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
						JOptionPane.showMessageDialog(this,"��������Լ�Ϊ����");
						return;
					}
					if(account.isFrend(myifo.getQQid(), qqid)){
						JOptionPane.showMessageDialog(this,"�����Ѿ��Ǻ�����");
						return;
					}
					Account frendIfo = account.getSelectedFriend(qqid);
					String str ="�Ƿ���ӡ�"+frendIfo.getInfoname()+"��Ϊ��ĺ��ѣ�";
					if(JOptionPane.showConfirmDialog(this, str,"��Ӻ���",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
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
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		origin.x = e.getX(); 
		origin.y = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p =this.getLocation(); 
		// ���ô��ڵ�λ��
		// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
		this.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
				- origin.y);
	}
		
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
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
				 * �����ѯ����ϵ�qq������������
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
				
				if(!sex.equals("��ѡ��")){
					sql += " and sex='"+sex + "'";
				}
				if(!status.equals("��ѡ��")){
					sql += " and stement='"+status + "'";
				}
				sql += " order by age";
				/**
				 * ���ó־ò�Ĳ�ѯ���ѷ���
				 * ��SQL��Ϊһ����������
				 * ��Ϊ���������Ҫƴ��SQL
				 * */
				vfrendlist = account.findFrend(sql);
				/**
				 * ��������JTable���������ģ��
				 * */
				dataTable.setModel(new datamadel(vHead,vfrendlist));
		}if(e.getSource()==btnAdd){
			int index = dataTable.getSelectedRow();
			if(index>=0){
				Vector row = (Vector)vfrendlist.get(index);
				int qqid = Integer.parseInt(row.get(1).toString());
				if(qqid==myifo.getQQid()){
					JOptionPane.showMessageDialog(this,"��������Լ�Ϊ����");
					return;
				}
				if(account.isFrend(myifo.getQQid(), qqid)){
					JOptionPane.showMessageDialog(this,"�����Ѿ��Ǻ�����");
					return;
				}
				Account frendifo = account.getSelectedFriend(qqid);
				String str ="�Ƿ���ӡ�"+frendifo.getInfoname()+"��Ϊ��ĺ��ѣ�";
				if(JOptionPane.showConfirmDialog(this, str,"��Ӻ���",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){}
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
	         * �õ�����г���
	         * */
	        public int getColumnCount() {
	            return vHead.size();
	        }
	        /**
	         * �õ�����еĳ���
	         * */
	        public int getRowCount() {
	            return data.size();
	        }
	        /**
	         * ������Ż�ȡ������
	         * */
	        public String getColumnName(int col) {
	            return vHead.get(col);
	        }
	        /**
	         * �������л�ȡ����Ԫ�����Ϣ
	         * */
	        @SuppressWarnings("rawtypes")
			public Object getValueAt(int row, int col) {
	        	/**
	        	 * ��ȡ���ڼ��е�����
	        	 * ����ֵ��һ������
	        	 * */
	        	Vector rowData = (Vector)vfrendlist.get(row);
				/**
				 * ����ǵ�һ�з���ͷ��Ķ���
				 * */
	        	if(col==0){
					return new ImageIcon(rowData.get(col).toString());
				}else{
					return rowData.get(col);
				}
	        }
	        /**
	         * ����ָ���е�class����
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
	         * ���ñ���Ƿ����޸�
	         * */
	        public boolean isCellEditable(int row, int col) {
	            return false;
	        }
	        /**
	         * �����������õ�Ԫ���ֵ
	         * */
	        @SuppressWarnings({ "rawtypes", "unchecked" })
			public void setValueAt(Object value, int row, int col) {
	        	Vector rowData = (Vector)vfrendlist.get(row);
	        	rowData.set(col, value);
	        	/**
	        	 * ���±��
	        	 * ���õ��Ǹ���ķ���
	        	 * */
	            fireTableCellUpdated(row, col);  
	        }
	    }
	
}
	