package com.feng.learn.basic.concurrence.test;

public interface AccountServiceI {
	
	/**
	 * 转账服务
	 * @param from 转出账户
	 * @param to   转入账户
	 * @param balance 转账金额
	 * @return
	 */
	boolean transferMoney(Account from, Account to, int balance);

}
