package com.qiancijun.notebook.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author Xu Chun
 * @Desc 用于读取文件
 * @Time 2021/3/19 19:07
 */

public class FileUtil {
    public static String readFile(File file) throws IOException {
        StringBuilder res = new StringBuilder();
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);// 缓冲区
        while (channel.read(buf) != -1) {
            buf.flip();
            res.append(new String(buf.array(), 0, buf.limit()));
            buf.clear();
        }
        fis.close();
        return res.toString();
    }

    public static void saveFile(File file, String data) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        FileChannel channel = fos.getChannel();
        ByteBuffer src = Charset.forName("utf8").encode(data);
        int len = 0;
        while ((len = channel.write(src)) != 0);
        fos.close();
    }
}
