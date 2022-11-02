package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.*;
import dal.Account;
import dal.*;
import jakarta.servlet.http.HttpSession;
import javax.swing.text.html.HTML;

;

public class AccountLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Receive data form login
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");
        req.setAttribute("email", email);
        req.setAttribute("pass", pass);

        String msgEmail = "", msgPass = "";
        if (email == "") {
            msgEmail = "Email is not required";
            req.setAttribute("msgEmail", msgEmail);
        }

        if (pass == "") {
            msgPass = "Password is not required";
            req.setAttribute("msgPass", msgPass);
        }

        if (!msgEmail.equals("") || !msgPass.equals("")) {
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        } else {
            Account acc = new AccountDAO().getAccount(email, pass);
            if (acc != null) {
                req.getSession().removeAttribute("cart");
                req.getSession().setAttribute("size", 0);
                req.getSession().setAttribute("t", 0);
                //cap session
                Customer cust = new CustomerDAO().getCustomerByID(acc.getCustomerID());
                req.getSession().setAttribute("AccSession", acc);
                req.getSession().setAttribute("role", acc.getRole());
                //dieu huong toi index

                if (acc.getRole() == 1) {
                    req.getSession().setAttribute("role", 1);
                    resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                } else {
                    req.getSession().setAttribute("uname", cust.getContactName());
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                req.setAttribute("msg", "This account does not exist.");
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("AccSession");
            req.getSession().removeAttribute("cart");
            req.getSession().setAttribute("size", 0);
            req.getSession().setAttribute("t", 0);
            req.getSession().removeAttribute("role");
            req.getSession().removeAttribute("uname");
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        }
    }
}
