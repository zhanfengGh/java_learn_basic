/**
 * 
 */
package com.feng.learn.basic.old2.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**  
 * @author zhangzhanfeng 
 * @date Dec 15, 2016   
 */
public class Client {
	public static void main(String args[]) throws IOException {
		ByteBuffer bbuf = ByteBuffer.allocate(1024);

		bbuf.clear();
		bbuf.put("HelloWorld".getBytes("utf-8"));

		SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));

		bbuf.flip();
		sc.write(bbuf);

		bbuf.clear();
		sc.read(bbuf);
		System.out.println(new String(bbuf.array(), "utf-8"));
	}
}
