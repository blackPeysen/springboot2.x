package com.peysen.gof23.structural.adapter;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 19:30
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {

        MediaAdapter mediaAdapter = new MediaAdapter(new Mp4Player());

        AudioPlayer audioPlayer = new AudioPlayer(mediaAdapter);

        audioPlayer.playMp3();

    }
}
