package com.course.testng;


import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.testng.annotations.*;

public class BasicAnnotation {
    @Test
     public void testCase1(){
        System.out.println("Test这是一个测试标签");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMehtod这是在测试方法之前运行的");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod是在测试方法后运行的");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass这是在类运行之前运行的");
    }
    @AfterClass
    public void AfterClass(){
        System.out.println("afterClass这是在类运行之后运行的");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite测试套件");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite测试套件");
    }

}
