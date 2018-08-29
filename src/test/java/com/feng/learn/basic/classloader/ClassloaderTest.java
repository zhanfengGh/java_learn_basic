package com.feng.learn.basic.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;
import java.net.URLClassLoader;

@Slf4j
public class ClassloaderTest {

    @Test
    public void testAppClassLoader() {
        ClassLoader cl = this.getClass().getClassLoader();
        log.info("classpath: {}", System.getProperty("java.class.path"));
        log.info("app ClassLoader: {}\npath: {}", cl);
        for (URL p: ((URLClassLoader) cl).getURLs()){
            log.info("{}",p);
        }
    }

    @Test
    public void testExtClassLoader() {
        ClassLoader cl = this.getClass().getClassLoader();
        ClassLoader extClassLoader = cl.getParent();
        log.info("ext classpath: {}", System.getProperty("java.ext.dirs"));
        log.info("ext ClassLoader: {}\npath: {}", extClassLoader);
        for (URL p: ((URLClassLoader) extClassLoader).getURLs()){
            log.info("{}",p);
        }
    }
}
