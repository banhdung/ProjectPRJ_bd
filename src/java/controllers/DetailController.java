
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dal.*;
import models.*;


public class DetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("ID");
        if (ID == null) {
            req.getRequestDispatcher("./home").forward(req, resp);
        } else {
            Product p = new ProductDAO().getProductById(Integer.parseInt(ID));
            req.setAttribute("p", p);
            Categories ca = new CategoriesDAO().getCategoryById(p.getCategoryID());
            req.setAttribute("ca", ca);
            req.getRequestDispatcher("../detail.jsp").forward(req, resp);
        }
    }
}


