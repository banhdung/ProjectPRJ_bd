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
import dal.Account;
import dal.Customer;
import models.AccountDAO;
import models.CustomerDAO;

/**
 *
 * @author banhdung
 */
public class AccountSignupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");
        String repass = req.getParameter("txtRepass");
        String CompanyName = req.getParameter("companyName");
        String ContactName = req.getParameter("contactName");
        String ContactTitle = req.getParameter("contactTitle");
        String Address = req.getParameter("address");

        String msgEmail = "", msgPass = "", msgRepass = "", msgCompanyName = "", msgContactName = "", msgContactTitle = "", msgAddress = "";
         if (!email.matches("[a-zA-Z]\\w+@\\w+(\\.\\w+){1,3}")) {
             msgEmail ="Email invalid . Pls enter follow : dungdung@gmail.com";
             req.setAttribute("msgEmail", msgEmail);
         }

        if (pass == "") {
            msgPass = "Password is not required";
            req.setAttribute("msgPass", msgPass);
        }
        if (CompanyName == "") {
            msgCompanyName = "CompanyName is not required";
            req.setAttribute("msgCompanyName", msgCompanyName);
        }
        if (Address== "") {
            msgAddress = "Address is not required";
            req.setAttribute("msgAddress", msgAddress);
        }
        if (ContactName == "") {
            msgContactName = "ContactName is not required";
            req.setAttribute("msgContactName", msgContactName);
        }
        if (ContactTitle == "") {
            msgContactTitle = "ContactTitle is not required";
            req.setAttribute("msgContactTitle", msgContactTitle);
        }
        if (repass == "") {
            msgRepass = "Re-pass is not required";
            req.setAttribute("msgRepass", msgRepass);
        }

        if (!msgAddress.equals("") || !msgCompanyName.equals("") || !msgContactName.equals("") || !msgContactTitle.equals("") || !msgEmail.equals("") || !msgPass.equals("") || !msgRepass.equals("")) {
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {
            String customerID = new AccountDAO().randomString(5);
            Customer cus = new Customer(customerID, CompanyName, ContactName, ContactTitle, Address);
            Account acc = new Account(email, pass, customerID, 2);

            if (!pass.equals(repass)) {
                resp.sendRedirect("../signup.jsp");
            } else {
                Account a = new AccountDAO().checkAccountExist(email);
                if (a == null && new AccountDAO().getAccount(acc, cus) > 0) {
                    req.getRequestDispatcher("../signin.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("../signup.jsp");
                }
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getSession().getAttribute("AccSeesion") == null) {
//            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
//        } else {
//            resp.sendRedirect("../index.jsp");
//        }
        req.getRequestDispatcher("../signup.jsp").forward(req, resp);
    }

}
