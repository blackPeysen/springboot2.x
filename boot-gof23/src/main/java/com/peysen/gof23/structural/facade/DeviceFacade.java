package com.peysen.gof23.structural.facade;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 9:40
 * @Desc:
 */
public class DeviceFacade {
    private DVDPlay dvdPlay = DVDPlay.getInstance();
    private VideoPlay videoPlay = VideoPlay.getInstance();

    public void openDevice(){
        dvdPlay.open();
        videoPlay.open();
    }

    public void closeDevice(){
        dvdPlay.close();
        videoPlay.close();
    }

}
