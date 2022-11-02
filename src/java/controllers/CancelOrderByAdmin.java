/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.OrderDAO;

/**
 *
 * @author banhdung
 */
public class CancelOrderByAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("oid", Integer.parseInt(req.getParameter("oid")));
        new OrderDAO().cancelOrderByID(Integer.parseInt(req.getParameter("oid")));
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (acc.getRole() == 1) {
            resp.sendRedirect(req.getContextPath() + "/OrderManager");
        }
        else{
             resp.sendRedirect(req.getContextPath() + "/allorder");
        }
    }

}
