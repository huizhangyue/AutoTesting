package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import scala.util.parsing.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;

    //用来存储cookie信息
    private BasicCookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url+uri;

        /**HttpGet get = new HttpGet(testUrl);
         HttpClient client = HttpClientBuilder.create().build();
         CloseableHttpClient client = HttpClients.createDefault();
         CloseableHttpResponse response = client.execute(get);
         result = EntityUtils.toString(response.getEntity(),"utf-8");
         System.out.println(result);
         */


        //获取cookies信息

        //1、创建本地cookies实例
        BasicCookieStore store = new BasicCookieStore();
        //2、建立httpClient对象，将CookieStore设置到client中
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        //3、建立get请求
        HttpGet get = new HttpGet(testUrl);
        //4、发送get请求
        CloseableHttpResponse response =client.execute(get);
        //5、获取相应内容
        result = EntityUtils.toString(response.getEntity(), "GBK");
        CloseableHttpResponse response1 = client.execute(get);
        List<Cookie> CookieList = store.getCookies();

        System.out.println(result);

        for (Cookie cookie: CookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name ="  + name + " ;cookie value:" + value);
        }


    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        //拼接最终的测试地址
        String testUrl = this.url + uri;
        //声明一个Client对象，用来进行方法的执行
        BasicCookieStore store = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        //声明一个post方法
        HttpPost post =  new HttpPost(testUrl);
        //添加参数
        JSONObject param = new JSONObject();

        param.put("name","zhangsan");
        param.put("age",14);
        //设置请求头信息
        post.setHeader("content-type","application/json");

        //将参数添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //设置cookies信息



        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串返回成json对象

        JSONObject resultJson = new JSONObject(result);


        //获取到结果值
        String success = (String) resultJson.get("zhangsan");
        String status  =（String) resultJson.get("status");

        //具体判断返回结果值
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);



    }
}
