/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.OrderDAO;
import dal.*;

/**
 *
 * @author banhdung
 */
public class AllOrderAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 0;
            try {
                page = Integer.parseInt(req.getParameter("page"));
            } catch (Exception e) {
                page = 1;
            }
            int elements = 16;
            OrderDAO dao = new OrderDAO();
            ArrayList<Orders> orders = new OrderDAO().getOrderByPage(page, elements);
            int numberOfPage = dao.getListOrders().size() % elements == 0 ? dao.getListOrders().size() / elements : dao.getListOrders().size() / elements + 1;
            req.setAttribute("page", page);
            req.setAttribute("numberOfPage", numberOfPage);
            req.setAttribute("listAll", orders);
            req.getRequestDispatcher("order.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }
}
