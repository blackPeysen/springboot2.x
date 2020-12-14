package com.peysen.gof23.structural.composite;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 8:49
 * @Desc: 使用组合模式设计大学、学院、系部等组织关系结构，并打印出所有的部门名称
 */
public class Client {

    public static void main(String[] args) {
        OrganizationNode university = new UniversityNode("清华大学");

        OrganizationNode college1 = new CollegeNode("计算机学院");
        OrganizationNode college2 = new CollegeNode("经管学院");


        OrganizationNode department1 = new DepartmentNode("学生部");
        OrganizationNode department2 = new DepartmentNode("体育部");
        OrganizationNode department3 = new DepartmentNode("文化部");
        OrganizationNode department4 = new DepartmentNode("监管部");

        university.addOrganization(college1);
        university.addOrganization(college2);

        college1.addOrganization(department1);
        college1.addOrganization(department2);
        college2.addOrganization(department3);
        college2.addOrganization(department4);

        university.print();
    }

}
