package com.qq.text;

import com.qq.beens.Account;
import com.qq.dao.AccountDao;
import com.qq.daoimp.AccountDaoimpl;

public class TextLogin {
	public static void main(String[] args) {
		AccountDao ad = new AccountDaoimpl();
		Account ac = new Account();
		ac.setQQid(1);
		ac.setPassword("123456");
		ad.login(ac);
	}
}
