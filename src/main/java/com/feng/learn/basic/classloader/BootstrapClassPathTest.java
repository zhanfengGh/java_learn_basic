package com.feng.learn.basic.classloader;

import java.net.URL;

public class BootstrapClassPathTest {
	//根加载器
    public static void main( String[] args )
    {
        @SuppressWarnings("restriction")
		URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url:urls){
        	System.out.println(url.toExternalForm());
        }
    }
}
