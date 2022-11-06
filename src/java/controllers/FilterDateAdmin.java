/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.OrderDAO;

/**
 *
 * @author banhdung
 */
public class FilterDateAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 0;
            try {
                page = Integer.parseInt(req.getParameter("page"));
            } catch (Exception e) {
                page = 1;
            }
            int elements = 10;

            OrderDAO dao = new OrderDAO();
            String txtStartOrderDate = req.getParameter("txtStartOrderDate");
            String txtEndOrderDate = req.getParameter("txtEndOrderDate");
            req.setAttribute("txtStartOrderDate", txtStartOrderDate);
            req.setAttribute("txtEndOrderDate", txtEndOrderDate);
            ArrayList<Orders> list = new OrderDAO().FilterDate(page, elements, txtStartOrderDate, txtEndOrderDate);
            int numberOfPage = dao.getListOrderByDate(txtStartOrderDate, txtEndOrderDate).size() % elements == 0 ? dao.getListOrderByDate(txtStartOrderDate, txtEndOrderDate).size() / elements : dao.getListOrderByDate(txtStartOrderDate, txtEndOrderDate).size() / elements + 1;
            req.getSession().setAttribute("listAll", list);
            req.setAttribute("page", page);
            req.setAttribute("numberOfPage", numberOfPage);
            req.getRequestDispatcher("order_1.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
        }
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
            int elements = 10;

            OrderDAO dao = new OrderDAO();
            String txtStartOrderDate = req.getParameter("txtStartOrderDate");
            String txtEndOrderDate = req.getParameter("txtEndOrderDate");
            req.setAttribute("txtStartOrderDate", txtStartOrderDate);
            req.setAttribute("txtEndOrderDate", txtEndOrderDate);
            ArrayList<Orders> list = new OrderDAO().FilterDate(page, elements, txtStartOrderDate, txtEndOrderDate);
            int numberOfPage = dao.getListOrderByDate(txtStartOrderDate, txtEndOrderDate).size() % elements == 0 ? dao.getListOrderByDate(txtStartOrderDate, txtEndOrderDate).size() / elements : dao.getListOrderByDate(txtStartOrderDate, txtEndOrderDate).size() / elements + 1;
            req.getSession().setAttribute("listAll", list);
            req.setAttribute("page", page);
            req.setAttribute("numberOfPage", numberOfPage);
            req.getRequestDispatcher("order_1.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
        }
    }
    
    

}
