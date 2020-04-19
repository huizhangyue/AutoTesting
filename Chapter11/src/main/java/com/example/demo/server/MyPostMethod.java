package com.example.demo.server;


import com.example.demo.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description  = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {


    //这个变量用来装我们cookies信息的
    private static Cookie cookie;

    //用户登录成功获取到cookies，然后在访问其他接口列表
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value= "userName",required = true) String userName,
                        @RequestParam(value = "password",required = true) String password){
        if (userName.equals("zhangsan") && password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功！";
        }
        return "用户名或者密码错误";
    }


    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User u){
        //获取cookies信息
        User user;
        Cookie [] cookies = request.getCookies();
        //验证cookies是否合法

        for (Cookie c : cookies){
            if (c.getName()=="login" &&
                    c.getValue()=="true" &&
                    u.getUserName()=="zhagnsan" &&
                    u.getPassword() == "123456"){
                user = new User();
                user.setName("lisi");
                user.setAge("15");
                user.setSex("man");
                return user.toString();

            }

        }
        return "参数不合法";
    }


}


