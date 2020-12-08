package com.peysen.gof23.creational.prototype;

import java.io.*;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:37
 * @Desc:
 */
public class Person implements Serializable, Cloneable {
    private String name;
    private int age;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * 浅拷贝：无法实现address属性的拷贝。
     *
     * @return
     * @throws CloneNotSupportedException
     */
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    /**
     * 深拷贝：实现address属性的拷贝。
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone(){
        Person person = null;
        try{
            person = (Person) super.clone();
            person.setAddress((Address)person.getAddress().clone());
        }catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }

    /**
     * 通过序列化实现深拷贝
     * @return
     */
    public Object deepClone(){
        Person person = null;

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try{
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            person = (Person) ois.readObject();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", hashcode='" + this.hashCode() + '\'' +
                '}';
    }
}
