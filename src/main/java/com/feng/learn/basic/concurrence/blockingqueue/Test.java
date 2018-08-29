package com.feng.learn.basic.concurrence.blockingqueue;

import java.util.concurrent.*;

class Person{
	private String name;
	private int age;
	
	public Person(){
		
	}
	
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}

public class Test {
	public static void main(String[] args){
		final java.util.concurrent.BlockingQueue<String> bQueue=new LinkedBlockingQueue<String>();
		
		Runnable blockingQueue=new Runnable(){

			public void run() {
				for(;;){
					try {
						String msg=bQueue.take();
						System.out.println(msg);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
					
				}
			}
			
		};
		
		Callable<Person> task=new Callable<Person>(){

			public Person call() throws Exception {
				Thread.sleep(2000);
				return new Person("feng",18);
			}
			
		};
		
		ExecutorService executor=Executors.newCachedThreadPool();
		Future<Person> future=executor.submit(task);
		Future<Person> future2=executor.submit(task);
		try {
			Person result=future.get();
			System.out.println(result);
			System.out.println("..............");
			System.out.println(future2.get());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
