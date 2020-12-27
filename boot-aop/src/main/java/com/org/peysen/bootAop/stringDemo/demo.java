package com.org.peysen.bootAop.stringDemo;

/**
 * @Author Peysen
 * @Date 2020/9/3 08:06
 * @Desc TODO
 */
public class demo {
    public static void main(String[] args) {
        String s1 = "Hollis";
        String s2 = new String("Hollis");
        String s3 = new String("Hollis").intern();

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);

        String ss1 = "Hollis";
        String ss2 = "Chuang";
        String ss3 = ss1 + ss2; // (new StringBuilder()).append(s1).append(s2).toString();
        String ss4 = ss1.intern() + ss2.intern();
        String ss5 = "Hollis" + "Chuang";
        String ss6 = "HollisChuang";

        System.out.println(ss3 == ss4);
        System.out.println(ss3 == ss5);
        System.out.println(ss4 == ss5);
        System.out.println(ss6 == ss5);

        System.out.println(testReturn());
    }

    private static int testReturn(){
        try{
            throw new Exception();
            //return 0;
        }catch (Exception e){
            System.out.println("1----");
            return 1;
        }finally {
            return 2;
        }
    }
}
