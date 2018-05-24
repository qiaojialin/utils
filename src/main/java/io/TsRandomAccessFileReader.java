package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * File-read interface for local file.
 *
 * @author Jinrui Zhang
 */
public class TsRandomAccessFileReader{

    private RandomAccessFile raf;

    public TsRandomAccessFileReader(String filePath) throws FileNotFoundException {
        this.raf = new RandomAccessFile(filePath, "r");
    }

    public void seek(long offset) throws IOException {
        this.raf.seek(offset);
    }

    public int read() throws IOException {
        return raf.read();
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int end = len + off;
        int get = 1;
        int total = 0;
        for (int i = off; i < end; i += get) {
            get = raf.read(b, i, end - i);
            if (get > 0)
                total += get;
            else
                break;
        }
        return total;
    }

    public long length() throws IOException {
        return raf.length();
    }

    public int readInt() throws IOException {
        return raf.readInt();
    }

    public void close() throws IOException {
        raf.close();
    }
}
