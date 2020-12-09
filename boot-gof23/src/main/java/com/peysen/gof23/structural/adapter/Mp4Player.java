package com.peysen.gof23.structural.adapter;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 14:03
 * @Desc:
 */
public class Mp4Player implements IAdvanceMediaPlayer {
    @Override
    public void playMp4() {
        System.out.println("mp4 播放音乐...");
    }
}
