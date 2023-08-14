package com.hsp.tomcat.http;

import java.io.OutputStream;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/11 10:35
 */
public class HttpResponse {
    private OutputStream outputStream=null;

    public static final String responseHeader = "HTTP/1.1 200 OK\r\n"+"Content-Type: text/html;charset=utf-8\r\n\r\n";

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
