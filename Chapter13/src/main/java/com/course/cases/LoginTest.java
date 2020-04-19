package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.LoginCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
























    @Test(groups = "loginTrue",description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

    }

    @Test(groups = "loginFalse",description = "用户登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
    }
}
