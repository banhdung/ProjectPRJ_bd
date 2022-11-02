package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dal.*;
import jakarta.websocket.Session;
import models.*;


public class ProfileEdit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("CusID");
        String CompanyName = req.getParameter("txtCompanyName");
        String ContactName = req.getParameter("txtContactName");
        String ContactTitle = req.getParameter("txtContactTitle");
        String Address = req.getParameter("txtAddress");
        try {
            new CustomerDAO().updateCustomer(new Customer(id, CompanyName, ContactName, ContactTitle, Address));
            req.getSession().setAttribute("uname", ContactName);
            resp.sendRedirect(req.getContextPath() + "/account/profile");
        } catch (Exception e) {
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account)(req.getSession().getAttribute("AccSession"));
        String id = acc.getCustomerID();
        try {
            Customer CusD = new CustomerDAO().getCustomerByID(id);
            req.setAttribute("cus", CusD);
            req.setAttribute("CusID", id);
            req.getRequestDispatcher("./profile-edit.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e);
        }
        
      

    }

}
