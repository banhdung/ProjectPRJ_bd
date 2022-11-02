/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.*;
import dal.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.lang.System.out;
import java.time.LocalDate;
import models.*;

/**
 *
 * @author banhdung
 */
public class OrderCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CompanyName = req.getParameter("txtCompanyName");
        String ContactName = req.getParameter("txtContactName");
        String ContactTitle = req.getParameter("txtContactTitle");
        String Address = req.getParameter("Address");
        String txtRequiredDate = req.getParameter("txtRequiredDate");
//        String msgCPN = "";
//        String msgCTN = "";
//        String msgCTT = "";
//        String msgADR = "";
//        String msgRD = "";
//        req.setAttribute("CompanyName", CompanyName);
//        req.setAttribute("Contactname", ContactName);
//        req.setAttribute("Contacttitle", ContactTitle);
//        req.setAttribute("Address", Address);
//        String msgString = "";
        Cart sc = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            sc = (Cart) o;
        } else {
            sc = new Cart();
        }
//        Customer customer = (Customer) req.getSession().getAttribute("CusSession");
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        //Case guest
        String guestID = new AccountDAO().randomString(5);
        if (acc == null) {
            if (req.getSession().getAttribute("cart") == null) {
                req.setAttribute("messOrderProduct", "Cart has Nothing to order");
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            } else {
                new CustomerDAO().addCustomer(guestID, CompanyName, ContactName, ContactTitle, Address);
                Customer guest = new CustomerDAO().getCustomerByID(guestID);
                new OrderDAO().addOrder(guest, sc, txtRequiredDate);
                req.getSession().removeAttribute("cart");
                req.getSession().setAttribute("size", 0);
                req.getSession().setAttribute("t", 0);
                req.setAttribute("messOrderProduct", "OrderSuccess");
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            }
        } else {
            if (req.getSession().getAttribute("cart") == null) {
                req.setAttribute("messOrderProduct", "Cart has Nothing to order");
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            } else {
                Customer cus = new CustomerDAO().getCustomerByID(acc.getCustomerID());           
                new OrderDAO().addOrder(cus, sc, txtRequiredDate);
                req.getSession().removeAttribute("cart");
                req.getSession().setAttribute("size", 0);
                req.getSession().setAttribute("t", 0);
                req.setAttribute("messOrderProduct", "OrderSuccessByCus");
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

}
