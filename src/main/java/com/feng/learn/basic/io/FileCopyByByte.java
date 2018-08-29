package com.feng.learn.basic.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyByByte {

    public static void main2(final String... args) {
        final String inFileName = args[0];
        final String outFileName = args[1];
        try (FileInputStream fis = new FileInputStream(inFileName);
             FileOutputStream fos = new FileOutputStream(outFileName)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(final String... args) {
        final String inFileName = args[0];
        final String outFileName = args[1];
        try (FileInputStream fis = new FileInputStream(inFileName);
             FileOutputStream fos = new FileOutputStream(outFileName)) {

            final byte[] buf = new byte[128];
            for (int length = 0; (length = fis.read(buf)) != -1; ) {
                fos.write(buf, 0, length);
            }

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
