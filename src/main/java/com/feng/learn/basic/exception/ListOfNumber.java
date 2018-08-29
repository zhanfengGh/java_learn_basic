package com.feng.learn.basic.exception;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class ListOfNumber {

    private final static int SIZE = 10;
    private final List<Integer> list;

    {
        list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add(new Integer(i));
        }
    }

    public static void main(final String... args) {
        final ListOfNumber listOfNumber = new ListOfNumber();

        try {
            listOfNumber.a();
        } catch (final IOException e) {
            // e.printStackTrace();
            final StackTraceElement[] elements = e.getStackTrace();
            for (int i = 0, n = elements.length; i < n; i++) {
                System.err.println(elements[i].getFileName()
                        + ":" + elements[i].getLineNumber()
                        + ">> "
                        + elements[i].getMethodName() + "()");
            }
        }
    }

    public void writeList() {
        PrintWriter out = null;
        try {
            // The FileWriter constructor throws IOException, which must be caught.
            out = new PrintWriter(new FileWriter("/Volumes/RamDisk/OutFile.txt"));
            for (int i = 0; i < SIZE; i++) {
                // The get(int) method throws IndexOutOfBoundsException, which must be caught.
                out.println("Value at: " + i + " = " + list.get(i));
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void writeList2() {
        try (PrintWriter out = new PrintWriter(new FileWriter("/Volumes/RamDisk/OutFile.txt"))) {
            // The FileWriter constructor throws IOException, which must be caught.
            for (int i = 0; i < SIZE; i++) {
                // The get(int) method throws IndexOutOfBoundsException, which must be caught.
                out.println("Value at: " + i + " = " + list.get(i));
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void tryWithoutResource() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(""));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void a() throws IOException {
        b();
    }

    private void b() throws IOException {
        c();
    }

    private void c() throws IOException {
        d();
    }

    private void d() throws IOException {
        throw new IOException();
    }

    private void e() throws IOException {
        final Handler handler = new FileHandler("OutFile.log");
        Logger.getLogger("").addHandler(handler);

    }
}
