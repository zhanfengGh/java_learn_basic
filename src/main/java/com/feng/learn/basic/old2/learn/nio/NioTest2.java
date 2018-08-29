/**
 * 
 */
package com.feng.learn.basic.old2.learn.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**  
 * @author zhangzhanfeng 
 * @date May 1, 2017   
 */
public class NioTest2 {

	@Test
	public void test() throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();

		ssc.configureBlocking(false);
		ssc.bind(new InetSocketAddress("127.0.0.1", 8888));

		ssc.register(selector, SelectionKey.OP_ACCEPT);

		for (;;) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> it = keys.iterator();
			while (it.hasNext()) {
				SelectionKey k = it.next();
				it.remove();
				if (k.isAcceptable()) {
					ServerSocketChannel s = (ServerSocketChannel) k.channel();
					SocketChannel c = s.accept();
					c.configureBlocking(false);
					c.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, ByteBuffer.allocate(128));
					
				} else if (k.isReadable()) {
					SocketChannel c = (SocketChannel) k.channel();
					ByteBuffer buf = (ByteBuffer) k.attachment();
					c.read(buf);
				}
			}
		}
	}
	
	
	@Test
	public void testAsynchronizedNio() throws IOException {
		final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
		server.bind(new InetSocketAddress("127.0.0.1", 8888));
		server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

			@Override
			public void completed(AsynchronousSocketChannel result, Object attachment) {
				server.accept(null, this); // 继续接受新连接
				ByteBuffer buf = ByteBuffer.allocate(128);
				result.read(buf, buf, new EchoCompletionHandler());
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				
			}
			
		});
	}
	
	private static class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

		@Override
		public void completed(Integer result, ByteBuffer attachment) {
		}

		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			
		}
		
	}

}
