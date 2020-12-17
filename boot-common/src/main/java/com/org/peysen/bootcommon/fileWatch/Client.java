package com.org.peysen.bootcommon.fileWatch;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/15 17:22
 * @Desc: 文件变化监听器
 *  在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 *  由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 *  如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 *  
 */
public class Client {
    public static void main(String[] args) throws Exception{
        // 监控目录
        String rootDir = "C:\\Users\\peimengmeng\\Desktop\\Focus\\ftpServer";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(1);

        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(FileFilterUtils.directoryFileFilter(), HiddenFileFilter.VISIBLE);
        IOFileFilter files    = FileFilterUtils.and(FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter(".xlsx"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        // 使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
        //不使用过滤器
        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener());
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
    }
}
