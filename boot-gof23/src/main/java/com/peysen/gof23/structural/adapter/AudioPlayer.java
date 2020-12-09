package com.peysen.gof23.structural.adapter;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 14:02
 * @Desc:
 */
public class AudioPlayer implements IMediaPlayer {
    private MediaAdapter mediaAdapter;

    public AudioPlayer(MediaAdapter mediaAdapter) {
        this.mediaAdapter = mediaAdapter;
    }

    @Override
    public void playMp3() {
        System.out.println("本身既可以播放mp3音乐");
        System.out.println("还可以播放mp4音乐");
        mediaAdapter.playMp3();
    }
}
