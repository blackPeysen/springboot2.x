package com.org.peysen.bootcommon.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 记录每个磁盘播放的次数
 * @Author: peysen
 * @CreateDate: 2019/7/23 07:50
 * @UpdateRemark: The modified content
 */
@Aspect
public class TrackCounter {

    private Map<Integer, Integer> trackCountMap = new HashMap<>();

    @Pointcut("execution(* com.org.peysen.bootcommon.aspect.CompactDisc.playTrack(int))" +
                "&& args(trackNum)")
    private void trackPlayed(int trackNum){ }

    @Before("trackPlayed(trackNum)")
    public void countTrack(int trackNum){
        int currentCount = getPlayCount(trackNum);
        trackCountMap.put(trackNum,++currentCount);
        System.out.println("play "+ trackNum + " is " + currentCount);
    }

    private int getPlayCount(int trackNum){
        return trackCountMap.containsKey(trackNum) ? trackCountMap.get(trackNum) : 0;
    }
}
