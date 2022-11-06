package controllers;

import dal.Cart;
import dal.ItemCart;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.ProductDAO;

public class BuyProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Cart c = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            c = (Cart) o;
        } else {
            c = new Cart();
        }
        try {
            Product p = new ProductDAO().getProductById(id);
            ItemCart i = new ItemCart(p, 1, p.getUnitPrice());
            c.addItem(i);
            List<ItemCart> list = c.getItems();
            req.getSession().setAttribute("cart", c);
            req.getSession().setAttribute("t", c.getToltalMoney());
            req.getSession().setAttribute("size", list.size());
            resp.sendRedirect("product/detail?ID=" + id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Cart c = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            c = (Cart) o;
        } else {
            c = new Cart();
        }
        try {
            Product p = new ProductDAO().getProductById(id);
            ItemCart i = new ItemCart(p, 1, p.getUnitPrice());
            c.addItem(i);
            List<ItemCart> list = c.getItems();
            req.getSession().setAttribute("cart", c);
            double t;
            t = c.getToltalMoney();
            req.getSession().setAttribute("t", t);
            req.getSession().setAttribute("size", list.size());
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }
}
