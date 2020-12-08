package com.peysen.gof23.creational.prototype;

import java.io.Serializable;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:38
 * @Desc:
 */
public class Address implements Serializable, Cloneable {
    private String cityName;
    private String streetName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Address{" +
                "cityName='" + cityName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", hashcode='" + this.hashCode() + '\'' +
                '}';
    }
}
