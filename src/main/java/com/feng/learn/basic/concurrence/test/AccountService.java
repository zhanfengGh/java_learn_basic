package com.feng.learn.basic.concurrence.test;

/**
 *  会造成死锁。。。
 *  A转账给B的同时 B转账给A 造成死锁。
 */
public class AccountService implements AccountServiceI {

	public boolean transferMoney(Account from, Account to, int balance) {
		if (from==null||to==null){
			return false;
		}
		if (from==to){
			return false;
		}
		synchronized (from) {
			int fromAccountCurBalance=from.getBalance();
			int toAccountCurBalance=to.getBalance();
			if (fromAccountCurBalance<balance) {
				System.out.println("from: "+from.getId()+" to: "+to.getId()+" failed. reason: notEnoughMoney. curBalance:"+from.getBalance()+" needed:"+balance);
				return false;
			} else {
				synchronized (to) {
					from.setBalance(fromAccountCurBalance-balance);
					to.setBalance(toAccountCurBalance+balance);
					System.out.println("from: "+from.getId()+" to: "+to.getId()+" succeeded. \n"
							+ "beforeTransfer [fromAccount.balance:"+fromAccountCurBalance+",toAccount.balance:"+toAccountCurBalance+"]\n"
									+ "transferred: "+balance+"\n"
											+ "afterTransfer [fromAccount.balance:"+from.getBalance()+",toAccount.balance:"+to.getBalance()+"]");
					return true;
				}
			}
		}
	}
	
}
