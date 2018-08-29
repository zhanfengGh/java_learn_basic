package com.feng.learn.basic.concurrence.test;

import java.util.Random;

public class Account {
	/** 唯一性id */
	private int id;
	
	private int balance;
	
	public Account(int id, int balance) {
		this.id=id;
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public static Account[] getAccounts(int accountNum, int maxBalance){
		Account[] accounts= new Account[accountNum];
		Random random=new Random();
		for (int i=0;i<accountNum;i++){
			accounts[i]=new Account(i, random.nextInt(maxBalance));
		}
		return accounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
