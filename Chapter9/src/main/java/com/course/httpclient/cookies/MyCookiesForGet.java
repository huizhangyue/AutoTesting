package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.nio.cs.ext.GBK;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {



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
        CloseableHttpClient client =HttpClients.custom().setDefaultCookieStore(store).build();
        //3、建立get请求
        HttpGet get = new HttpGet(testUrl);
        //4、发送get请求
        CloseableHttpResponse response =client.execute(get);
        //5、获取相应内容
        result = EntityUtils.toString(response.getEntity(), "GBK");
        CloseableHttpResponse response1 = client.execute(get);
        List <Cookie>  CookieList = store.getCookies();

        System.out.println(result);



        for (Cookie cookie: CookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name ="  + name + " ;cookie value:" + value);
        }


    }

   @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
            String uri = bundle.getString("test.get.with.cookies");
            String testUrl = this.url+uri;

            //1、创建本地cookies实例
            BasicCookieStore store = new BasicCookieStore();
            //2、建立httpClient对象，将CookieStore设置到client中
            CloseableHttpClient client =HttpClients.custom().setDefaultCookieStore(store).build();
            //3、建立get请求
            HttpGet get = new HttpGet(testUrl);
            //4、发送get请求
            CloseableHttpResponse response =client.execute(get);
           int statusCode = response.getStatusLine().getStatusCode();
           System.out.println("statusCode = " + statusCode);

       if (statusCode == 200){
           String result = EntityUtils.toString(response.getEntity(),"utf-8");
           System.out.println(result);

       }


    }



}
