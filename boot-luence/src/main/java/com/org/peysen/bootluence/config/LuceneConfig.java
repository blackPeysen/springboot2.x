package com.org.peysen.bootluence.config;

import com.org.peysen.bootluence.analyzer.SynonyAnalyzer;
import com.org.peysen.bootluence.service.ISynonyEngine;
import com.org.peysen.bootluence.service.impl.SynonyEngineImpl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.ControlledRealTimeReopenThread;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Configuration
public class LuceneConfig {

    /**
     * lucene索引,存放位置
     */
    @Value("${luence.index.dir}")
    private String indexPath;


    @Bean(name = "stopAnalyzer")
    public Analyzer stopAnalyzer(){
        return new StopAnalyzer();
    }

    @Bean(name = "standardAnalyzer")
    public Analyzer standardAnalyzer() {
        return new StandardAnalyzer();
    }
 
    @Bean(name = "smartChineseAnalyzer")
    public Analyzer smartChineseAnalyzer() {
        return new SmartChineseAnalyzer(false);
    }
 
    @Bean(name = "whitespaceAnalyzer")
    public Analyzer whitespaceAnalyzer(){
        return new WhitespaceAnalyzer();
    }

    @Bean(name = "synonyAnalyzer")
    public Analyzer synonyAnalyzer(){
        ISynonyEngine synonyEngine = new SynonyEngineImpl();
        return new SynonyAnalyzer(synonyEngine);
    }


    /**
     * 索引位置
     *
     * @return
     * @throws IOException
     */
    @Bean
    public Directory directory() throws IOException {
        Path path = Paths.get(indexPath);
        File file = path.toFile();

        if(!file.exists()) {
            //如果文件夹不存在,则创建
            file.mkdirs();
        }
        return FSDirectory.open(path);
    }
 
    /**
     * 创建indexWriter
     *
     * @param directory
     * @param standardAnalyzer
     * @return
     * @throws IOException
     */
    @Bean
    public IndexWriter indexWriter(Directory directory, Analyzer standardAnalyzer) throws IOException {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);

        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // 清空索引
//        indexWriter.deleteAll();
//        indexWriter.commit();

        return indexWriter;
    }

    /**
     * SearcherManager管理
     *
     * @param directory
     * @return
     * @throws IOException
     */
    @Bean
    public SearcherManager searcherManager(Directory directory, IndexWriter indexWriter) throws IOException {
        SearcherManager searcherManager = new SearcherManager(indexWriter, false, false, new SearcherFactory());

        ControlledRealTimeReopenThread cRTReopenThead = new ControlledRealTimeReopenThread(indexWriter, searcherManager, 5.0, 0.025);
        cRTReopenThead.setDaemon(true);
        //线程名称
        cRTReopenThead.setName("更新IndexReader线程");
        // 开启线程
        cRTReopenThead.start();
        System.out.println("定时任务开启中........." + new Date());

        return searcherManager;
    }
 
}