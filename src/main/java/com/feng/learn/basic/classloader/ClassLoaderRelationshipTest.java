package com.feng.learn.basic.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderRelationshipTest {
	// 加载器的层次关系
	public static void main(String[] args) throws IOException {
		// 获得系统类加载器
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println(systemClassLoader);
		Enumeration<URL> urls = systemClassLoader.getResources("");
		while (urls.hasMoreElements()) {
			System.out.println(urls.nextElement());
		}
		ClassLoader extensionClassLoader = systemClassLoader.getParent();
		System.out.println(extensionClassLoader);
		System.out.println(extensionClassLoader.getParent());
	}
}
