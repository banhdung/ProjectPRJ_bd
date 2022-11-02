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
import jakarta.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoriesDAO;
import models.ProductDAO;

/**
 *
 * @author banhdung
 */
public class ProductSearchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearch = req.getParameter("txtSearch");
        int page = 0;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }
        int elements = 10;
        ArrayList<Product> listProduct = new ProductDAO().getProductsByPageandID(page, elements, txtSearch);
        req.setAttribute("product", listProduct);
        ArrayList<Categories> listCate = new CategoriesDAO().getCategories();
        req.setAttribute("category", listCate);
        if (txtSearch == null) {
            txtSearch = "";
        }
        int numberOfPage = new ProductDAO().SerachProductByName(txtSearch).size() % elements == 0 ? new ProductDAO().SerachProductByName(txtSearch).size() / elements : new ProductDAO().SerachProductByName(txtSearch).size() / elements + 1;
        req.setAttribute("numberOfPage", numberOfPage);
        req.setAttribute("page", page);
        req.setAttribute("txtSearch", txtSearch);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

}
