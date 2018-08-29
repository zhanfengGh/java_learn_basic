package com.feng.learn.basic.concurrence.test;


/**
 * 对AccountService的方法进行修改，利用hashCode 来改变from 和 to 的加锁顺序。
 * 防止 A转账给B的同时 B转账给A 造成死锁。
 * 
 * 特殊情况 ：from 和to 的hashCode相同，此时仍然有可能造成死锁。
 * 修改方法： from 和to 的hashCode相同 时。以额外的锁来保证。
 * 
*/	
public class AccountService3 implements AccountServiceI {
	
	public boolean transferMoney(Account from, Account to, int balance) {
		if (from==null||to==null){
			return false;
		}
		if (from==to){
			return false;
		}
		int fromHashCode=System.identityHashCode(from);
		int toHashCode=System.identityHashCode(to);
		if (fromHashCode<toHashCode){
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
		} else if (fromHashCode>toHashCode) {
			synchronized (to) {
				synchronized (from) {
					int fromAccountCurBalance=from.getBalance();
					int toAccountCurBalance=to.getBalance();
					if (fromAccountCurBalance<balance) {
						System.out.println("from: "+from.getId()+" to: "+to.getId()+" failed. reason: notEnoughMoney. curBalance:"+from.getBalance()+" needed:"+balance);
						return false;
					} else {
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
		//hashCode相同时 已内置锁来同步。
		} else {
			synchronized (this) {
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
	}
}
