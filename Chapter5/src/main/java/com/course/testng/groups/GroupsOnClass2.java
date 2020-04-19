package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass2 {
    public void teacher1(){
        System.out.println("GroupsOnClass1中的teacher1111运行");
    }
    public void teacher2(){
        System.out.println("GroupsOnClass1中的teacher2222运行");
    }
}
