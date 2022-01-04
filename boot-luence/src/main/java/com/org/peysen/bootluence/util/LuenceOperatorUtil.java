package com.org.peysen.bootluence.util;

import cn.hutool.core.io.FileUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 9:40
 * Desc:
 */
public class LuenceOperatorUtil {

    /**
     * 创建索引
     *
     * @param indexDir
     * @param file
     * @throws IOException
     */
    public static void createIndex(String indexDir, File file) throws IOException {
        //指定索引库存放的路径
        Directory directory = FSDirectory.open(new File(indexDir).toPath());

        //索引库还可以存放到内存中
        //Directory directory = new RAMDirectory();

        //创建indexwriterCofig对象
        IndexWriterConfig config = new IndexWriterConfig();

        //创建indexwriter对象
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //文件名
        String fileName = file.getName();
        //文件路径
        String filePath = file.getPath();
        //文件的大小
        long fileSize  = FileUtil.size(file);
        //文件内容
        String fileContent = FileUtil.readUtf8String(file);

        //创建文件名域
        //第一个参数：域的名称
        //第二个参数：域的内容
        //第三个参数：是否存储
        Field fileNameField = new TextField("filename", fileName, Field.Store.YES);
        //文件内容域
        Field fileContentField = new TextField("content", fileContent, Field.Store.YES);
        //文件路径域（不分析、不索引、只存储）
        Field filePathField = new TextField("path", filePath, Field.Store.YES);
        //文件大小域
        Field fileSizeField = new TextField("size", fileSize + "", Field.Store.YES);

        //创建document对象
        Document document = new Document();
        document.add(fileNameField);
        document.add(fileContentField);
        document.add(filePathField);
        document.add(fileSizeField);

        //创建索引，并写入索引库
        indexWriter.addDocument(document);

        //关闭indexwriter
        indexWriter.close();
    }

}
