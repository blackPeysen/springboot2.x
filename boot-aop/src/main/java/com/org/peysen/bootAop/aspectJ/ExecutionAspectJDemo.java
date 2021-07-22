package com.org.peysen.bootAop.aspectJ;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/3/8
 * @Desc :
 * execution(modifier-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
 *
 * 上面的格式中，execution是不变的，用于作为execution表达式的开头，整个表达式中几个参数的详细解释如下：
 *      modifier-pattern：指定方法的修饰符，支持通配符，可选，如public、protected；
 *      ret-type-pattern：指定返回值类型，支持通配符，  必填，可以使用“*”来通配所有的返回值类型
 *      declaring-type-pattern：指定方法所属的类，支持通配符，可选，可以是任何类型模式
 *      name-pattern：指定匹配的方法名，支持通配符，必填，可以使用“*”来通配所有的方法名
 *      param-pattern：指定方法的形参列表，支持两个通配符，“*”和“..”，其中“*”代表一个任意类型的参数，而“..”代表0个或多个任意类型的参数。
 *      throw-pattern：指定方法声明抛出的异常，支持通配符，可选，
 *                  以“throws 异常全限定名列表”声明，异常全限定名列表如有多个以“，”分割，
 *                  如throws java.lang.IllegalArgumentException, java.lang.ArrayIndexOutOfBoundsException。
 */

//@Aspect
//@Component
public class ExecutionAspectJDemo {

    // 匹配com.org.peysen.bootAop.service包中所有public方法
    @Pointcut("execution(public * com.org.peysen.bootAop.service..*.*(..))")
    public void pointCut1(){}

    // 匹配com.org.peysen.bootAop.service.AspectJServiceDemo类中所有public 方法
    @Pointcut("execution(public * com.org.peysen.bootAop.service.AspectJServiceImpl.*(..))")
    public void pointCut2(){}

    @After(value = "pointCut1()")
    public void advise(){
        System.out.println("advise end....");
    }
}

