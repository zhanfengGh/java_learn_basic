/**
 * 
 */
package com.feng.learn.basic.old2.learn.encode;

import com.feng.learn.basic.old2.learn.Utils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**  
 * @author zhangzhanfeng 
 * @date Mar 3, 2017   
 */
public class EncodeTest {
	
	@Test
	public void testStr() throws UnsupportedEncodingException {
		String str = "02100860350";
		byte[] bs = str.getBytes("utf-8");
		String hex = Utils.toHex(bs);
		byte[] bs2 = new byte[bs.length + 2];
		System.arraycopy(bs, 0, bs2, 0, bs.length);
		String str2 = new String(bs);
		String str3 = new String(bs2);
		boolean b =  str2.equals(str3);
	}

	@Test
	public void test() throws UnsupportedEncodingException {
		String str = "容器测试";
		byte[] s = str.getBytes("utf-8");
		//byte[] s = str.getBytes("gbk");
		String hexStr = Utils.toHex(s);
		System.out.println(hexStr);
	}
	
	@Test
	public void testUrlEncode () throws UnsupportedEncodingException {
		String e = URLEncoder.encode("Servlet 容器测试 success..", "utf-8");
		System.out.println(e);
		e = URLDecoder.decode(e, "gbk");
	}

}
