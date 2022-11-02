/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.*;

/**
 *
 * @author Admin
 */
public class ProductCreate extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ProductName = req.getParameter("txtName");
        int CategoryID = Integer.parseInt(req.getParameter("txtCategory"));
        String QuantityPerUnit = req.getParameter("txtQuantity");
        double UnitPrice = Double.parseDouble(req.getParameter("txtUnitPrice")) ;
        int UnitsInStock = Integer.parseInt(req.getParameter("txtUnitsInStock"));
        int UnitsOnOrder = Integer.parseInt(req.getParameter("txtUnitsOnOrder"));
        int ReorderLevel = Integer.parseInt(req.getParameter("txtReorderLevel"));
        boolean Discontinued = Boolean.parseBoolean(req.getParameter("txtDiscontinued"));
        Product p = new Product(0, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
        
        if (new ProductDAO().addProduct(p) != 0) {
            resp.sendRedirect("product-list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("category", new CategoriesDAO().getCategories());
        req.getRequestDispatcher("product-create.jsp").forward(req, resp);
    }
    
}
