package com.peysen.gof23.structural.adapter;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 14:03
 * @Desc:
 */
public class VicPlayer implements IAdvanceMediaPlayer {

    @Override
    public void playMp4() {
        System.out.println("Vic 播放音乐...");
    }
}
