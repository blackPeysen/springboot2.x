package com.org.peysen.bootcommon.aspect;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/23 08:03
 * @UpdateRemark: The modified content
 */
public class BlankDisc implements CompactDisc {

    private String title;

    private String artist;

    private List<String> tracks;

    @Override
    public void playTrack(int trackNum) {

        String trackName = tracks.get(trackNum);

        System.out.println("BlankDisc playTrack：" + trackName);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "BlankDisc{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", tracks=" + tracks +
                '}';
    }
}
