package com.org.peysen.bootcommon.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: Class实例对象的基本API
 * Created by mengmeng.Pei
 * 2019/9/20 13:56
 */
public class ClassDemo3 {

    /**
     * 打印类的信息，包括类的成员函数，成员变量,构造函数
     * @param object
     */
    public static  void printClassMessage(Object object){
        //获取类的信息，先获取类的类类型
        Class<?> objectClass = null;
        if (object != null){
            objectClass = object.getClass();
            System.out.println("类的名称是:" + objectClass.getName());

            /**
             * Method:方法对象，一个成员方法就是一个Method对象
             * getMethods():获取的是所有的public的函数，包括父类继承而来的。
             * getDeclaredMethods():获取的是所有该类自己声明的方法，不管访问权限
             */
            printMethodMessage(objectClass);

            /**
             * Field: 成员变量对象，封装了关于成员变量的操作。
             * getFields():获取的是所有的public成员变量，包括父类继承而来的
             * getDeclaredFields():获取的是所有该类自己声明的成员变量，不管访问权限
             */
            printFieldMessage(objectClass);

            /**
             * Constructor: 构造函数对象，封装了构造函数的操作。
             * getConstructors():获取的是所有的public构造函数，包括父类继承而来的
             * getDeclaredFields():获取的是所有该类自己声明的成员变量，不管访问权限
             */
            printConstructorMessage(objectClass);
        }

    }

    private static void printConstructorMessage(Class<?> objectClass) {
        Constructor<?>[] constructors = null;
        constructors = objectClass.getConstructors();
        constructors = objectClass.getDeclaredConstructors();
        for (Constructor constructor : constructors){
            String name = constructor.getName();
            System.out.println("constructor ：" + name);
        }
    }

    private static void printMethodMessage(Class<?> objectClass) {
        Method[] methods = null;
        methods = objectClass.getMethods();
        methods = objectClass.getDeclaredMethods();

        String methodName = "";
        Class<?> returnType = null;
        for(Method method : methods){
            //获取方法的方法名
            methodName = method.getName();

            //获取方法的返回值类型的类类型
            returnType = method.getReturnType();
            System.out.println("methodName :" + methodName + ",returnType:" + returnType);

            //获取方法的参数类型->得到的是参数列表的类型的类类型
            Class<?>[] parameterTypes = method.getParameterTypes();

            for(Class paramType : parameterTypes){
                System.out.print("paramType: " + paramType.getName());
            }




        }
    }

    private static void printFieldMessage(Class<?> objectClass) {
        Field[] fields = null;
        fields = objectClass.getFields();
        fields = objectClass.getDeclaredFields();

        for(Field field : fields){
            //获取成员变量的类型的类类型
            Class<?> type = field.getType();
            //获取成员变量的名称
            String name = field.getName();

            System.out.println(name + "--" + type);
        }
    }


    public static void main(String[] args) {
        String s ="hel";
        printClassMessage(s);
    }

}
