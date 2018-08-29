/**
 * 
 */
package com.feng.learn.basic.old2.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**  
 * @author zhangzhanfeng 
 * @date Dec 15, 2016   
 */
public class Server {
	public static void main(String[] args) throws IOException {
		ByteBuffer bbuf = ByteBuffer.allocate(1024);

		Selector selector = Selector.open();

		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(8888));

		ssc.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> itr = keys.iterator();
			while (itr.hasNext()) {
				SelectionKey key = itr.next();
				if (key.isAcceptable()) {
					ServerSocketChannel sscTmp = (ServerSocketChannel) key.channel();
					SocketChannel sc = sscTmp.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					SocketChannel sc = (SocketChannel) key.channel();
					bbuf.clear();
					sc.read(bbuf);
					//System.out.println(bbuf);
					bbuf.flip();
					sc.write(bbuf);
				}
				itr.remove();
			}
		}
	}
}
