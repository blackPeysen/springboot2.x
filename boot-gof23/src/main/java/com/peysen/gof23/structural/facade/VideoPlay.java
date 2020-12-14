package com.peysen.gof23.structural.facade;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 9:16
 * @Desc:
 */
public class VideoPlay {
    private static final VideoPlay instance = new VideoPlay();

    public static VideoPlay getInstance() {
        return instance;
    }
    public void open(){
        System.out.println("打开视频播放器");
    }

    public void close(){
        System.out.println("关闭视频播放器");
    }
}
