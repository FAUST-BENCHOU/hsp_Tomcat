package com.hsp.tomcat.handler;

import com.hsp.tomcat.HspTomcat03;
import com.hsp.tomcat.http.HttpRequest;
import com.hsp.tomcat.http.HttpResponse;
import com.hsp.tomcat.servlet.HspHttpServlet;

import java.io.*;
import java.net.Socket;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/10 16:57
 */
public class HspRequestHandler implements Runnable{

    private Socket socket;

    public HspRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            HttpRequest httpRequest = new HttpRequest(inputStream);
            HttpResponse httpResponse = new HttpResponse(socket.getOutputStream());

            String uri = httpRequest.getUri();
            String servletName = HspTomcat03.servletUrlMapping.get(uri);

            HspHttpServlet hspHttpServlet=null;
            if (servletName != null && HspTomcat03.servletMapping.containsKey(servletName)) {
                hspHttpServlet = HspTomcat03.servletMapping.get(servletName);
                // 进一步处理 hspHttpServlet
            } else {
                System.out.println(servletName==null);
                System.out.println(servletName);
                System.out.println(uri);
                System.out.println(HspTomcat03.servletUrlMapping);
            }

//            HspHttpServlet hspHttpServlet = HspTomcat03.servletMapping.get(servletName);

            if (hspHttpServlet!=null) {
                hspHttpServlet.service(httpRequest, httpResponse);
            }else {
                String resp = httpResponse.responseHeader+"<h1>404!!</h1>";
                OutputStream outputStream=httpResponse.getOutputStream();
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();

            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
