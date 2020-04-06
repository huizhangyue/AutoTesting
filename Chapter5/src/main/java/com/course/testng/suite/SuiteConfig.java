package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SuiteConfig {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before suite 运行啦");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite 运行啦");
    }
}
