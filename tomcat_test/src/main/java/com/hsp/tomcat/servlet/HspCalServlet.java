package com.hsp.tomcat.servlet;

import com.hsp.tomcat.http.HttpRequest;
import com.hsp.tomcat.http.HttpResponse;
import com.hsp.tomcat.utils.WebUtil;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/11 10:36
 */
public class HspCalServlet extends HspHttpServlet{


    @Override
    public void doGet(HttpRequest request, HttpResponse response) throws IOException {
        String n1 = request.getParameter("num1");
        String n2 = request.getParameter("num2");
        int num1 = WebUtil.parseString(n1);
        int num2 = WebUtil.parseString(n2);
        String resp =response.responseHeader+"<h1>计算</h1>"+"你输入的num1是"+num1+" 你输入的num2是"+num2+"</br>"+"总和是"+(num1+num2);
        OutputStream outputStream=response.getOutputStream();
        outputStream.write(resp.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) throws IOException {
        this.doGet(request,response);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }
}
