package com.hsp.tomcat.http;

import java.io.*;
import java.util.HashMap;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/11 10:35
 */
public class HttpRequest {
    private String method;
    private String uri;
    private HashMap<String,String> parametersMapping =  new HashMap<>();

    public HttpRequest(InputStream inputStream){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String requestLine = bufferedReader.readLine();
            method = requestLine.split(" ")[0];
            int index = requestLine.indexOf("?");
            if (index==-1){
                uri = requestLine.split(" ")[1];
            }else {
                uri = requestLine.split(" ")[1].substring(0,index-4);
                System.out.println(index);
                String parameters = requestLine.split(" ")[1].substring(index-3);
                String[] paraArr=parameters.split("&");
                if (paraArr!=null&&!"".equals(paraArr)){

                    for (String para : paraArr) {
                        String[] para_v = para.split("=");
                        if (para_v.length==2){
                            parametersMapping.put(para_v[0],para_v[1]);
                        }
                    }
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getParameter(String name){
        if (parametersMapping.containsKey(name)){
            return parametersMapping.get(name);
        }else {
            return null;
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }
}
