package com.hsp.tomcat.servlet;

import com.hsp.tomcat.http.HttpRequest;
import com.hsp.tomcat.http.HttpResponse;

import java.io.IOException;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/11 10:37
 */
public interface HspServlet {
    void init() throws Exception;

    void service(HttpRequest request, HttpResponse response)throws IOException;

    void destroy();
}
