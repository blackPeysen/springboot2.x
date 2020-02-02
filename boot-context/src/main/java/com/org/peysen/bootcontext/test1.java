package com.org.peysen.bootcontext;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/1/17
 * @Desc :
 */
public class test1 {
    /**
     * 题目2: 请用JAVA代码完成以下功能，要求使用多线程
     *      cat /home/admin/logs/*.log | grep "Login" | sort | uniq -c | sort -nr
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(Long.valueOf("-1000"));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10 ,60, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(10));

        //该path路径可以进行配置
        String path = "/home/admin/logs/";
        File filePath = new File(path);
        File[] listFiles = null;
        if(filePath.exists() && filePath.isDirectory()){
            listFiles = filePath.listFiles(new FilenameFilterImpl());
        }

        List<Future<List<String>> > futureList = new ArrayList<>();
        Future<List<String>> submit = null;
        if(listFiles != null && listFiles.length > 0){
            for(File file : listFiles){
                submit  = threadPoolExecutor.submit(new FileRead(file));
                futureList.add(submit);
            }
        }

        List<String> allStr = new ArrayList<>();
        for(Future<List<String>> listFuture : futureList){
            List<String> strings = listFuture.get();
            allStr.addAll(strings);
        }
        //对list进行排序
        allStr = allStr.stream().sorted(Comparator.comparing(String::toString)).collect(Collectors.toList());

        threadPoolExecutor.shutdown();

    }

    static class FilenameFilterImpl implements FilenameFilter{

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".log");
        }
    }



    static class FileRead implements Callable<List<String>> {
        private File file;

        public FileRead(File file) {
            this.file = file;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> list = null;
            if(file.exists()){
                try (BufferedReader brs = new BufferedReader(new FileReader(file));){
                    list = new ArrayList<>();
                    String str = "";

                    while((str = brs.readLine()) != null){
                        if(str.contentEquals("Login")){
                            list.add(str);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return list;
        }
    }

}
