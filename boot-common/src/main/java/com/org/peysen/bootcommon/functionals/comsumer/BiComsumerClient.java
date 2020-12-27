package com.org.peysen.bootcommon.functionals.comsumer;

import java.util.function.BiConsumer;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/27 14:46
 * @Desc: 函数接口BiComsumer 的测试代码
 *
 *  BiComsumer 接受两个参数，并对参数进行逻辑操作，无返回值，是可能具有副作用的函数接口。
 */
public class BiComsumerClient {
    public static void main(String[] args) {
        for (double i = 0; i < 10; i++) {
            InnerClass innerClass = new InnerClass(i);

            System.out.println("before a= " + innerClass.getNum());

            // 使用BiConsumer函数对innerClass实例对象中的num成员属性进行重新赋值
            test(innerClass, innerClass, (t, u) -> t.setNum(Double.valueOf(Math.pow(t.getNum(), u.getNum()))));

            System.out.println("after a=" + innerClass.getNum());

            System.out.println();
        }
    }

    private static void test(InnerClass t, InnerClass u, BiConsumer<InnerClass, InnerClass> biComsumer){
        biComsumer.accept(t,u);
    }

    static class InnerClass{
        private Double num;

        public InnerClass(Double num) {
            this.num = num;
        }

        public void setNum(Double num) {
            this.num = num;
        }

        public Double getNum() {
            return num;
        }
    }
}
