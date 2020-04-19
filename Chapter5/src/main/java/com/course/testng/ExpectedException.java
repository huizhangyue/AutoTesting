package com.course.testng;


import org.testng.annotations.Test;



//这是一个结果会失败的异常测试
public class ExpectedException {
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

//这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个会成功的异常测试");
        throw new RuntimeException();
    }
}
