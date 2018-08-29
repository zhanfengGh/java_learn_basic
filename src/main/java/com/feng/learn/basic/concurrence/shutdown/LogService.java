package com.feng.learn.basic.concurrence.shutdown;

import java.io.PrintWriter;
import java.util.concurrent.*;

public class LogService implements LogServiceI {
	
	private static final int CAPACITY=10;
	private BlockingQueue<String> queue;
	private PrintWriter writer;
	private ExecutorService executor=Executors.newCachedThreadPool();
	private Future<?> logFuture;
	
	public LogService(){
		this(CAPACITY);
	}
	
	public LogService(int capacity){
		this.queue=new ArrayBlockingQueue<String>(capacity);
		this.writer=new PrintWriter(System.out);
		this.logFuture=executor.submit(logTask);
	}

	public void log(String message) throws InterruptedException {
		queue.put(message);
	}
	
	public void stop(){
		this.logFuture.cancel(true);
		executor.shutdown();
	}
	
	private Runnable logTask=new Runnable(){

		public void run() {
			for(;;){
				try {
					String msg=queue.take();
					writer.println(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	};
		
		
	
	public static void main(String[] args){
		LogService service=new LogService();
		for (int i=0;i<1000;i++){
			try {
				service.log("Hello :  "+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		service.stop();
	}
	

}
