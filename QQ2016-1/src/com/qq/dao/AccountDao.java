package com.qq.dao;

import java.util.Vector;

import com.qq.beens.Account;
@SuppressWarnings("rawtypes")
public	interface AccountDao {
	public String Save(Account a);
	public Account login(Account a);
	public Vector<Account> getMyfriend(int QQid);
	public void moveGroup(int qqid,int frendid,String groupname);
	public void changeStement(int qqid,String Stement);
	
	public Vector<Vector> findFrend(final String sql);
	public boolean isFrend(int myqqid,int friendqqid);
	public Account getSelectedFriend(int myqqid);
	public void addFrend(int myqqid,int frendqqid);
	public Account update(Account acc);
	public void delFreand(int myqqid,int frendqqid );
	public void updatepassword(int qqid,String password);
	public Account selectpasswordd(Account acc);
	
}
