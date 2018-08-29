package com.feng.learn.basic.io;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {

    private static final int BUF_DEFAULT_SIZE = 8192;
    private static final int BUF_MAX_SIZE = Integer.MAX_VALUE;

    private InputStream in;

    private byte[] buf;
    private int pos;
    private int count;
    private int markpos = -1;
    private int marklimit;


    public BufferedInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public synchronized long skip(long n) throws IOException {
        getInIfOpen();
        if (n <= 0) {
            return 0;
        }

        int avail = count - pos;
        if (avail <= 0) {
            if (markpos < 0) {
                return getInIfOpen().skip(n);
            }

            fill();
            avail = count - pos;
            if (avail <= 0) {
                return 0;
            }
        }
        int skipped = avail;
        pos += skipped;
        return skipped;
    }

    @Override
    public synchronized int available() throws IOException {
        int n = count - pos;
        int avail = getInIfOpen().available();
        return n > (Integer.MAX_VALUE - avail) ? Integer.MAX_VALUE : n + avail;
    }

    private InputStream getInIfOpen() throws IOException {
        if (in == null) {
            throw new IOException("Stream closed.");
        }
        return in;

    }

    @Override
    public void close() throws IOException {
        in.close();
        buf = null;
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.markpos = this.pos;
        this.marklimit = readlimit;
    }

    @Override
    public synchronized void reset() throws IOException {
        if (markpos == -1) {
            throw new IOException("reset to invalid mark.");
        }
        pos = markpos;
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public synchronized int read() throws IOException {
        if (pos >= count) {
            fill();
            if (pos >= count) {
                return -1;
            }
        }
        return getBufIfOpen()[pos++] & 0xFF;
    }

    private byte[] getBufIfOpen() throws IOException {
        if (buf == null) {
            throw new IOException("Stream closed.");
        }
        return buf;
    }

    private void fill() throws IOException {
        if (markpos < 0) {
            // 完全丢弃现有buf中的所有数据，重新填充buf
            count = pos = 0;
        } else if (pos >= markpos + marklimit) {
            // 完全丢弃现有buf中的所有数据，重新填充buf
            count = pos = 0;
            markpos = -1;

        } else {
            int lengthToKeep = pos - markpos;

            if (lengthToKeep > buf.length * 0.75) {
                if (buf.length == BUF_MAX_SIZE && lengthToKeep > BUF_MAX_SIZE) {
                    throw new OutOfMemoryError("Required array size too large");
                }
                // 扩容到原来的2倍
                int newLength = buf.length * 2;
                newLength = newLength > BUF_MAX_SIZE ? BUF_MAX_SIZE : newLength;

                byte[] buf_2 = new byte[newLength];
                System.arraycopy(buf, markpos, buf_2, 0, lengthToKeep);
                buf = buf_2;
            } else if (lengthToKeep < buf.length * 0.25) {
                byte[] buf_2 = new byte[buf.length / 2];
                System.arraycopy(buf, markpos, buf_2, 0, lengthToKeep);
                buf = buf_2;
            } else if (markpos > 0) {
                System.arraycopy(buf, markpos, buf, 0, lengthToKeep);
            }
            markpos = 0;
            count = pos = lengthToKeep;
        }
        int n = in.read(buf, count, buf.length - count);
        if (n > 0) {
            count += n;
        }
    }

    private void fill2() throws IOException {
        // 方法隐含条件：pos >= count
        if (markpos < 0) {
            // no mark: throw array the buf
            pos = 0;
        } else if (pos >= getBufIfOpen().length) {
            // 隐含条件：markpos >= 0
            if (markpos > 0) {
                int sz = pos - markpos;
                System.arraycopy(getBufIfOpen(), markpos, getBufIfOpen(), 0, sz);
                markpos = 0;
                pos = sz;
            } else if (getBufIfOpen().length > marklimit) {
                // 隐含条件： markpos == 0 && pos >= marklimit
                // throw away the buf
                markpos = -1;
                pos = 0;
            } else if (getBufIfOpen().length >= BUF_MAX_SIZE) {
                throw new OutOfMemoryError("Required array size too large");
            } else {
                // 隐含条件： markpos == 0 && getBufIfOpen().length <= limit
                // 必须扩容
                int nsz = pos > (BUF_MAX_SIZE - pos) ? BUF_MAX_SIZE : 2 * pos;
                byte[] nbuf = new byte[nsz];
                System.arraycopy(getBufIfOpen(), 0, nbuf, 0, getBufIfOpen().length);
            }
        } else {
            // 隐含条件：markpos >= 0 && pos < buf.length
            // do nothing to the buf, just read more bytes from the underline InputStream
        }

        count = pos;
        int n = getInIfOpen().read(getBufIfOpen(), pos,
                getBufIfOpen().length - pos);
        if (n > 0) {
            count += n;
        }
    }


}
