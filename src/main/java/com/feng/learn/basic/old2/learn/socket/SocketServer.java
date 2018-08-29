/**
 * 
 */
package com.feng.learn.basic.old2.learn.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**  
 * @author zhangzhanfeng 
 * @date Dec 27, 2016   
 */
public class SocketServer {
	public static void main(String[] args) throws IOException {
		SocketAddress sa = new InetSocketAddress("127.0.0.1", 8888);
		ServerSocket ss = new ServerSocket();
		ss.bind(sa);
		while (true) {
			Socket s = ss.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				if (str.equals("zhangzhanfeng")) {
					ss.close();
				}
			}
			s.close();
		}

	}
}
