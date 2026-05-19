package com.mvc.example.spring_mvc_example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/third")
public class ThirdServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("processing doget request by third servlet");
    }



        @Override
        protected void doPost(
                HttpServletRequest req,
                HttpServletResponse resp)
                throws IOException {

            String name =
                    req.getParameter("name");

            resp.getWriter()
                    .println("Saved: " + name);

            System.out.println("form submitted using post method");
        }
    }

