package com.mvc.example.spring_mvc_example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/first")
public class Firstservelet implements Servlet {
    private ServletConfig servletConfig;

    //LIFE CYCLE METHODS

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig=servletConfig;
        System.out.println("initiailizing servelet");
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service request");
    }


    @Override
    public void destroy() {
        System.out.println("destroying servelet");

    }

    //NON LIFE CYCLE METHODS



    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }


    @Override
    public String getServletInfo() {
        return "this servelet is created by author ";
    }

}
