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
public class HomeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> proList = new ProductDAO().getProducts();
        ArrayList<Categories> listCate = (new CategoriesDAO()).getCategories();
       
       
        req.setAttribute("products", proList);
        req.setAttribute("category", listCate);

        ArrayList<Product> productsHot = new ProductDAO().getProductsHot();
        ArrayList<Product> productsBest = new ProductDAO().getProductsBest();
        ArrayList<Product> productsNew = new ProductDAO().getProductsNew();
        req.setAttribute("productsHot", productsHot);
        req.setAttribute("productsBest", productsBest);
        req.setAttribute("productsNew", productsNew);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
