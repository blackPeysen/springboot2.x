package com.peysen.gof23.creational.prototype;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:50
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        Address address = new Address();
        address.setCityName("nanjing");
        address.setStreetName("xinghuolu");

        Person person = new Person();
        person.setAge(25);
        person.setName("peimengmeng");
        person.setAddress(address);
        System.out.println("person:" + person.toString());

        Person person1 = (Person)person.clone();
        System.out.println("person1:" + person1.toString());

        Person person2 = (Person)person.deepClone();
        System.out.println("person2:" + person2.toString());
    }
}
