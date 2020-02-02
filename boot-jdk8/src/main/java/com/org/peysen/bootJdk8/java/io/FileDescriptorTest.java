package com.org.peysen.bootJdk8.java.io;

import java.io.*;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/20
 * @Desc :
 */
public class FileDescriptorTest {
    private static final String FileName = "/Users/peysen/Desktop/kongming/central/ddd.txt";
    private static final String OutText = "Hi FileDescriptor";

    public static void main(String[] args) throws IOException {
//         testStandFD();
         testWrite();
//         testRead();
    }

    /**
     * 该程序的效果 等价于 System.out.println(OutText);
     *
     * @throws IOException
     * @Method_Name: testStandFD
     * @Description: * void
     */
    public static void testStandFD() throws IOException {
        FileOutputStream out = new FileOutputStream(FileDescriptor.out);
        PrintStream print = new PrintStream(out);
        print.write(OutText.getBytes());
    }

    /**
     * FileDescriptor写入示例程序
     *
     * @throws IOException
     * @Method_Name: testWrite
     * @Description: * void
     */
    private static void testWrite() throws IOException {
        // 新建file对应FileOutPutStream对象
        FileOutputStream fout = new FileOutputStream(FileName);
        // 获得file对应的FileDescripto对象
        FileDescriptor fd = fout.getFD();
        // 根据FileDescriptor创建fileOutPutStream对象
        FileOutputStream out = new FileOutputStream(fd.out);
        fout.write("Hello".getBytes());
        out.write("World".getBytes());
        out.close();
        fout.close();
    }

    private static void testRead() throws IOException {
        // 新建文件“file.txt”对应的FileInputStream对象
        FileInputStream fis = new FileInputStream(FileName);
        // 获取文件“file.txt”对应的“文件描述符”
        FileDescriptor fd = fis.getFD();
        // 根据“文件描述符”创建“FileInputStream”对象
        FileInputStream fiss = new FileInputStream(fd);
        System.out.println("in1.read():" + (char) fis.read());
        System.out.println("in2.read():" + (char) fiss.read());
        fis.close();
        fiss.close();
    }
}
