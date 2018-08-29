/**
 * 
 */
package com.feng.learn.basic.old2.learn.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

/**  
 * @author zhangzhanfeng 
 * @date Dec 27, 2016   
 */
public class SocketClient {

	public static void main(String args[]) throws UnknownHostException, IOException {
		Socket client = new Socket("127.0.0.1", 8888);
		Writer w = new OutputStreamWriter(client.getOutputStream());
		w.write("Hello World.你好，世界。。\n");
		w.write("zhangzhanfeng");
		w.close();
		client.close();
	}
}
