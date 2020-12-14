package com.peysen.gof23.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 8:52
 * @Desc:
 */
public class UniversityNode extends OrganizationNode {
    private List<OrganizationNode> collegeNodes = new ArrayList<>();

    public UniversityNode(String nodeName) {
        super(nodeName);
    }


    @Override
    public boolean addOrganization(OrganizationNode organizationNode) {
        return collegeNodes.add(organizationNode);
    }

    @Override
    public void print() {
        System.out.println("我是一个大学，名叫：" + this.nodeName);
        System.out.println("我手下有如下学院：===========================");
        this.collegeNodes.stream().forEach(OrganizationNode::print);
    }
}
