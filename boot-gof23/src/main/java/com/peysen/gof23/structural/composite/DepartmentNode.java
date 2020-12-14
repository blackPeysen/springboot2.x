package com.peysen.gof23.structural.composite;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 8:53
 * @Desc:
 */
public class DepartmentNode extends OrganizationNode {

    public DepartmentNode(String nodeName) {
        super(nodeName);
    }

    @Override
    public void print() {
        System.out.println("我是一个系部，名叫：" + this.nodeName);
    }
}
