package com.course.testng.paramter;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamterTest {
    @Test
    @Parameters({"name","age"} )
    public void paramTest1(String name,int age){
        System.out.println("name=" + name + "age=" + age);
    }
}
