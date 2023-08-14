package com.hsp.tomcat;

import com.hsp.tomcat.handler.HspRequestHandler;
import com.hsp.tomcat.servlet.HspHttpServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/11 15:44
 */
public class HspTomcat03 {

    public static ConcurrentHashMap<String, HspHttpServlet> servletMapping = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, String> servletUrlMapping = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException, DocumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        HspTomcat03 hspTomcat03 = new HspTomcat03();
        hspTomcat03.init();
        hspTomcat03.run();
    }

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("=====8080=====");
        while (!serverSocket.isClosed()){
            Socket accept = serverSocket.accept();
            HspRequestHandler hspRequestHandler = new HspRequestHandler(accept);
            new Thread(new HspRequestHandler(accept)).start();
        }
    }

    public void init() throws MalformedURLException, DocumentException {

        String path = HspTomcat03.class.getResource("/").getPath();
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(new File(path + "web.xml"));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            if ("servlet".equalsIgnoreCase(element.getName())){

                Element servletName = element.element("servlet-name");
                Element servletClass=element.element("servlet-class");
                String className = servletClass.getText().trim();
                try {
                    HspHttpServlet servletInstance = (HspHttpServlet) Class.forName(className).newInstance();
                    servletMapping.put(servletName.getText(), servletInstance);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else if ("servlet-mapping".equalsIgnoreCase(element.getName())){
                Element servletName = element.element("servlet-name");
                Element urlPattern=element.element("url-pattern");
                servletUrlMapping.put(urlPattern.getText(),servletName.getText());
            }
        }
    }
}
