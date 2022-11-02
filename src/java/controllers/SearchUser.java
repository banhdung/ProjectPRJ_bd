/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Categories;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoriesDAO;
import models.ProductDAO;

/**
 *
 * @author banhdung
 */
public class SearchUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearchUser = req.getParameter("txtSearchUser");
        int page = 0;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }
        int elements = 12;

        ArrayList<Product> listProduct = new ProductDAO().getProductsByPageandID(page, elements, txtSearchUser);
        req.setAttribute("product", listProduct);
        ArrayList<Categories> listCate = new CategoriesDAO().getCategories();
        req.setAttribute("category", listCate);

        int numberOfPage = new ProductDAO().SerachProductByName(txtSearchUser).size() % elements == 0 ? new ProductDAO().SerachProductByName(txtSearchUser).size() / elements : new ProductDAO().SerachProductByName(txtSearchUser).size() / elements + 1;
        req.setAttribute("numberOfPage", numberOfPage);
        req.setAttribute("page", page);
        req.setAttribute("txtSearchUser", txtSearchUser);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearchUser = req.getParameter("txtSearchUser");
        int page = 0;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }
        int elements = 12;

        ArrayList<Product> listProduct = new ProductDAO().getProductsByPageandID(page, elements, txtSearchUser);
        req.setAttribute("product", listProduct);
        ArrayList<Categories> listCate = new CategoriesDAO().getCategories();
        req.setAttribute("category", listCate);

        int numberOfPage = new ProductDAO().SerachProductByName(txtSearchUser).size() % elements == 0 ? new ProductDAO().SerachProductByName(txtSearchUser).size() / elements : new ProductDAO().SerachProductByName(txtSearchUser).size() / elements + 1;
        req.setAttribute("numberOfPage", numberOfPage);
        req.setAttribute("page", page);
        req.setAttribute("txtSearchUser", txtSearchUser);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

}
