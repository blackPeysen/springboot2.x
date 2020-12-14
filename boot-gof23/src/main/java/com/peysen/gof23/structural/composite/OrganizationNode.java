package com.peysen.gof23.structural.composite;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 8:50
 * @Desc:
 */
public abstract class OrganizationNode implements IOrganization{
    protected String nodeName;

    public OrganizationNode(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public boolean addOrganization(OrganizationNode organizationNode) {
        return false;
    }
}
