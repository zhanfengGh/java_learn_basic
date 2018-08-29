/**
 * 
 */
package com.feng.learn.basic.old2.learn.nio;

import com.feng.learn.basic.old2.learn.Utils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

/**  
 * @author zhangzhanfeng 
 * @date Dec 15, 2016   
 */
public class NioTest {

	private static final String FILE = "/Volumes/RamDisk/test.txt";
	private static final String FILE2 = "/Volumes/RamDisk/test2.txt";

	private static FileChannel fc;

	private static FileChannel fc2;

	@BeforeClass
	public static void setBeforeClass() throws FileNotFoundException {
		fc = new RandomAccessFile(FILE, "rw").getChannel();
		fc2 = new RandomAccessFile(FILE2, "rw").getChannel();
	}

	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		fc.close();
		fc2.close();
	}

	@Test
	public void test() throws CharacterCodingException {
		CharBuffer cbuf = CharBuffer.allocate(128);
		cbuf.put("Hello World. 你好。。");
		System.out.println(cbuf);
		cbuf.flip();
		System.out.println(cbuf);
		//Charset iso8859 = Charset.forName("iso-8859-1");
		Charset iso8859 = Charset.forName("utf-8");
		CharsetEncoder encoder = iso8859.newEncoder();
		CharsetDecoder decoder = iso8859.newDecoder();
		ByteBuffer bbuf = encoder.encode(cbuf);
		String str = bbuf.toString();
		System.out.println(str);
		try {
			fc.write(bbuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCharacterEncoding() throws UnsupportedEncodingException {
		String str = "Hello World. 你好。。";
		byte[] bs = str.getBytes("utf-8");
		System.out.println(Utils.toHex(bs));
	}

	public CharsetDecoder getDecoder(String charset) {
		Charset cs = Charset.forName(charset);
		return cs.newDecoder();
	}

	public CharsetEncoder getEncoder(String charset) {
		Charset cs = Charset.forName(charset);
		return cs.newEncoder();
	}

	@Test
	@Ignore
	public void testFileCopy() throws IOException {
		ByteBuffer bbuf = ByteBuffer.allocate(1024);
		CharsetDecoder decoder = getDecoder("utf-8");
		while (true) {
			bbuf.clear();
			int count = fc.read(bbuf);
			if (count == -1) {
				break;
			}
			bbuf.flip();
			CharBuffer cbuf = decoder.decode(bbuf);
			System.out.println(cbuf);

			fc2.write(bbuf);
		}
	}

	/**
	 *  bbuf.put() 多放throw异常
	 * 	bbuf.get() 多取throw异常
	 */
	@Test
	public void testByteBuffer() {
		byte[] a = new byte[1];
		ByteBuffer bbuf = ByteBuffer.wrap(a);
		System.out.println(bbuf);
		bbuf.put((byte) 'a');
		bbuf.put((byte) 'b');
		System.out.println(bbuf);
		bbuf.flip();
		char c = (char) bbuf.get();
		System.out.println(c);
		System.out.println(bbuf);

		bbuf.get();
		//bbuf.get(); //throw 异常
	}

	@Test
	public void testByteBuffer2() {
		ByteBuffer bbuf = ByteBuffer.allocate(32);
		bbuf.putChar('a');
		System.out.println(bbuf);

		bbuf.putDouble(9.0);
		System.out.println(bbuf);

		bbuf.putFloat(5.5f);
		System.out.println(bbuf);

		bbuf.putInt(7);
		System.out.println(bbuf);

		bbuf.flip();

		System.out.println(bbuf.getChar());
		bbuf.mark();
		System.out.println(bbuf.getInt());
		bbuf.reset();
		System.out.println(bbuf.getDouble());

		System.out.println(bbuf);

	}

	@Test
	@Ignore
	public void testServerSocketChannel() throws IOException {

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
					while (true) {
						bbuf.clear();
						int count = sc.read(bbuf);
						if (count == -1) {
							break;
						}
						System.out.println(bbuf);
						bbuf.flip();
						sc.write(bbuf);
					}

				}
				itr.remove();
			}
		}
	}

	@Test
	@Ignore
	public void testSocketChannel() throws IOException {

		ByteBuffer bbuf = ByteBuffer.allocate(1024);

		bbuf.clear();
		bbuf.put("HelloWorld".getBytes("utf-8"));

		SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));

		bbuf.flip();
		sc.write(bbuf);

	}

}
