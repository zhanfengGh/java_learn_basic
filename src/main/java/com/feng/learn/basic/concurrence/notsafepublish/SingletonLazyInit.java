package com.feng.learn.basic.concurrence.notsafepublish;

import com.feng.learn.basic.thread.annotation.GuardedBy;
import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
public class SingletonLazyInit {
	
	@GuardedBy("this")
	private static Person person;
	
	public static synchronized Person getInstance(){
		if (person==null){
			person=new Person("feng",26);
		}
		System.out.println(person);
		return person;
	}

}
