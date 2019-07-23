package com.org.peysen.bootcommon.aspect.config;

import com.org.peysen.bootcommon.aspect.BlankDisc;
import com.org.peysen.bootcommon.aspect.CompactDisc;
import com.org.peysen.bootcommon.aspect.TrackCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/23 07:58
 * @UpdateRemark: The modified content
 */
@Configuration
@EnableAspectJAutoProxy
public class TrackCountConfig {


    @Bean
    public CompactDisc blankDisc(){
        BlankDisc blankDisc = new BlankDisc();
        blankDisc.setTitle("aaaa");
        blankDisc.setArtist("bbb");

        List<String> tracks = new ArrayList<>();
        tracks.add("track1");
        tracks.add("track2");
        tracks.add("track3");
        tracks.add("track4");
        tracks.add("track5");
        blankDisc.setTracks(tracks);

        return blankDisc;
    }

    @Bean
    public TrackCounter trackCount(){
        return new TrackCounter();
    }


}
