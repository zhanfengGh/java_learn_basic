package com.feng.learn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) {
        Package aPackage = A.class.getPackage();
        String packagePath = aPackage.getName().replaceAll("\\.", File.separator);
        String sPath = ClassLoader.getSystemResource("").getPath();
        System.out.println(sPath);
        File path = new File(sPath + packagePath);
        List<String> files = findClassName(path, false);
        for (String file : files) {

        }


    }
    private static List<String> findClassName(File file, boolean subPackage) {
        File[] files = file.listFiles();
        List<String> list = new ArrayList<>(files.length);
        for (File f : files) {
            if (f.isDirectory()) {
                if (subPackage) {
                    list.addAll(findClassName(f, subPackage));
                }
            } else {
                list.add(f.getAbsolutePath());
            }
        }
        return list;
    }
}
