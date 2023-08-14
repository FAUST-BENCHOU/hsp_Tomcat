package com.hsp.tomcat.servlet;

import com.hsp.tomcat.http.HttpRequest;
import com.hsp.tomcat.http.HttpResponse;

import java.io.IOException;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/11 10:37
 */
public abstract class HspHttpServlet implements HspServlet{

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        if ("GET".equalsIgnoreCase((request.getMethod()))){
            this.doGet(request,response);
        }else if ("POST".equalsIgnoreCase((request.getMethod()))){
            this.doPost(request,response);
        }
    }

    public abstract void doGet(HttpRequest request, HttpResponse response) throws IOException;
    public abstract void doPost(HttpRequest request,HttpResponse response) throws IOException;
}
