package com.peysen.gof23.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 8:52
 * @Desc:
 */
public class CollegeNode extends OrganizationNode {
    private List<OrganizationNode> departmentNodes = new ArrayList<>();

    public CollegeNode(String nodeName) {
        super(nodeName);
    }

    @Override
    public boolean addOrganization(OrganizationNode organizationNode) {
        return departmentNodes.add(organizationNode);
    }

    @Override
    public void print() {
        System.out.println("我是一个学院，名叫：" + this.nodeName);
        System.out.println("我手下有如下部门：");
        this.departmentNodes.stream().forEach(OrganizationNode::print);

        System.out.println("==============\r\n");

    }
}
