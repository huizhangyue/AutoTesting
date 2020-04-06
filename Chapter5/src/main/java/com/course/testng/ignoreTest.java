package com.course.testng;

import org.testng.annotations.Test;

public class ignoreTest {
    @Test
    public void ignore1(){
        System.out.println("ignore 1  执行");
    }
    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore 2 执行");
    }
    @Test(enabled = true)
    public void ignore3(){
        System.out.println("ignore3 执行");
    }
}
