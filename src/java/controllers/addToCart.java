/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author banhdung
 */
public class addToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idc = req.getParameter("idC");
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
//            int as = 0;
//            for (ItemCart ic : list) {
//                as = ic.getProduct().getProductID();
//            }
            t = c.getToltalMoney();
            req.getSession().setAttribute("t", t);
//            req.getSession().setAttribute("as", as);
            req.getSession().setAttribute("size", list.size());
            req.getRequestDispatcher("detail?id=" + id).forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println(e);
        }
    }

}
