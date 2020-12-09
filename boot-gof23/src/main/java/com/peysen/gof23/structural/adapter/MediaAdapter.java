package com.peysen.gof23.structural.adapter;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 14:04
 * @Desc:
 */
public class MediaAdapter implements IMediaPlayer {
    private IAdvanceMediaPlayer advanceMediaPlayer;

    public MediaAdapter(IAdvanceMediaPlayer advanceMediaPlayer) {
        this.advanceMediaPlayer = advanceMediaPlayer;
    }

    @Override
    public void playMp3() {
        System.out.println("使用适配器模式播放mp4音乐");
        advanceMediaPlayer.playMp4();
    }
}
