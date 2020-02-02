package com.org.peysen.bootJdk8.java.time;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/20
     * boolean equals(Object obj)
     * 检查这个时钟是否等于另一个时钟。
     * int hashCode()
     * 这个时钟的哈希码。
     * abstract Instant instant()
     * 获取当前的时钟瞬间。
     * long millis()
     * 获取时钟的当前毫秒时间。
     * static Clock offset(Clock baseClock, Duration offsetDuration)
     * 获取一个时钟，从指定的时钟周期返回时钟，并添加指定的时间
     * static Clock system(ZoneId zone)
     * 获取使用最佳可用系统时钟返回当前时刻的时钟。
     * static Clock systemDefaultZone()
     * 获取使用最佳可用系统时钟返回当前即时的时钟，使用默认时区转换为日期和时间。
     * static Clock systemUTC()
     * 获取使用最佳可用系统时钟返回当前即时的时钟，使用UTC时区转换为日期和时间。
     * static Clock tick(Clock baseClock, Duration tickDuration)
     * 获取时钟，将时钟从指定的时钟截断返回到指定持续时间的最近出现。
     * static Clock tickMinutes(ZoneId zone)
     * 获取时钟，使用最佳可用系统时钟在一分钟内返回当前瞬间滴答作响。
     * static Clock tickSeconds(ZoneId zone)
     * 获取时钟，使用最佳可用系统时钟在一秒钟内返回当前瞬间滴答。
 */

public class ClockTest {
    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        System.out.println("zone:" + clock.getZone().toString());
        System.out.println("instant:" + clock.instant().toString());
        System.out.println("millis:" + clock.millis());
        System.out.println("systemUTC--------------");

        clock = Clock.systemDefaultZone();
        System.out.println("zone:" + clock.getZone().toString());
        System.out.println("instant:" + clock.instant().toString());
        System.out.println("millis:" + clock.millis());
        System.out.println("systemDefaultZone--------------");

        clock = Clock.system(ZoneId.of("Europe/Rome"));
        System.out.println("zone:" + clock.getZone().toString());
        System.out.println("instant:" + clock.instant().toString());
        System.out.println("millis:" + clock.millis());
        System.out.println("system--------------");


        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        System.out.println("zone:" + clock.getZone().toString());
        System.out.println("instant:" + clock.instant().toString());
        System.out.println("millis:" + clock.millis());
        System.out.println("fixed--------------");

        clock = Clock.systemDefaultZone();
        clock = Clock.tick(clock, Duration.ofDays(14));
        System.out.println("zone:" + clock.getZone().toString());
        System.out.println("instant:" + clock.instant().toString());
        System.out.println("millis:" + clock.millis());
        System.out.println("tick--------------");

        clock = Clock.tickMinutes(ZoneId.of("Europe/Rome"));
        System.out.println("zone:" + clock.getZone().toString());
        System.out.println("instant:" + clock.instant().toString());
        System.out.println("millis:" + clock.millis());
        System.out.println("tickMinutes--------------");

    }
}
