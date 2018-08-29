package com.feng.learn.basic.old2.learn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  
 * @author zhangzhanfeng 
 * @date Jun 23, 2017   
 */
public class ExMain {
	public static void main(String[] args) {
		String data = "zabbix /log/web/device5/access.log 2017-06-23 14:43:39,078 [INFO ] [LogFilter.java:97] - "
				+ "SUCCESS!@#!POST!@#!/xqtravel/api/device/v4/returnDevice!@#!2017-06-23 14:43:38 850!@#!228ms!@#!18605696972!@#!121.361514,31.136031!@#!ANDROID!@#!0.0.0.0!@#!117!@#!27F900EB3FE363C53DBDA2981CB270C7!@#!header:{\"content-type\":\"application/x-www-form-urlencoded\",\"connection\":\"Keep-Alive\",\"host\":\"192.168.0.216:8082\",\"content-length\":\"260\",\"user-agent\":\"okhttp/3.4.2\",\"accept-encoding\":\"gzip\"}!@#!body:mobile=18605696972&source=ANDROID&version=117&sessionKey=27F900EB3FE363C53DBDA2981CB270C7&lat=31.136031&lng=121.361514&deviceCode=021061352&privateSecretKey=l0gns+zVRM3GBIrk81/AxNDlwqgMXVdRhdIrjMT6Xe8=&deviceLat=&deviceLng=&faultConfigIds=&devices=";
		Pattern p = Pattern.compile("^(\\w+) (\\S+) (\\d+-\\d+-\\d+ \\d+:\\d+:\\d+,\\d+) \\[(\\w+)\\s*\\] \\[(.*)\\] - "
				+ "(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)!@#!(.*)$");
		Matcher m = p.matcher(data);
		for (int i = 1; i < m.groupCount(); i++) {
			System.out.println(i + ": " + m.group(i));
		}
	}
}
