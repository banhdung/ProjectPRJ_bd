/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Cart;
import dal.ItemCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author banhdung
 */
public class removeProductCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart sc = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            sc = (Cart) o;
        } else {
            sc = new Cart();
        }
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            sc.removeItem(id);
            List<ItemCart> list = sc.getItems();
            req.getSession().setAttribute("cast", sc);
            req.getSession().setAttribute("size", list.size());
            req.getSession().setAttribute("t", sc.getToltalMoney());
            req.getRequestDispatcher("cart.jsp").forward(req, resp);

        } catch (Exception e) {
        }
    }
}
