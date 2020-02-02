package com.org.peysen.bootJdk8.java.io;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/20
 * @Desc :
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/peysen/Desktop/kongming/central/logo.png");

        if(file.isAbsolute()){
            System.out.println("file is 绝对路径 ");
        }else{
            System.out.println("file is not 绝对路径 ");
        }

        File canonicalFile = file.getCanonicalFile();
        System.out.println(canonicalFile.getAbsolutePath());
        System.out.println(canonicalFile.getPath());
        System.out.println(file.toURI().toString());

        FileDescriptor in = FileDescriptor.in;
    }
}
