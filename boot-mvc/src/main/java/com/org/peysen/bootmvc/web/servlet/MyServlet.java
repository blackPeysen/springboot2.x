package com.org.peysen.bootmvc.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义原生Servlet
 * Created by mengmeng.Pei
 * 2019/8/19 17:09
 */

@WebServlet(urlPatterns = "/myServlet",asyncSupported = true)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //开启异步非阻塞Servlet
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(()->{
            try {
                resp.getWriter().println("MyServlet -->doGet");
                //触发完成
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("MyServlet -->doPost");
    }
}
