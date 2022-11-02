package controllers;

import dal.*;
import dal.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.*;

public class ProductEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int ProductID = Integer.parseInt(request.getParameter("id"));
        //Product
        ProductDAO pd = new ProductDAO();
        Product p = pd.getProductById(ProductID);
        request.setAttribute("product", p);
        //Category ID
        CategoriesDAO cd = new CategoriesDAO();
        //Category
        ArrayList<Categories> c = cd.getCategories();
        request.setAttribute("category", c);
        request.getRequestDispatcher("product-edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ProductID = Integer.parseInt(request.getParameter("ProductID"));
        String ProductName = request.getParameter("ProductName");
        int CategoryID = Integer.parseInt(request.getParameter("ddlCategoryID"));
        String QuantityPerUnit = request.getParameter("QuantityPerUnit");
        double UnitPrice = Double.parseDouble(request.getParameter("UnitPrice"));
        int UnitsInStock = Integer.parseInt(request.getParameter("UnitsInStock"));
        int UnitsOnOrder = Integer.parseInt(request.getParameter("UnitsOnOrder"));
        int ReorderLevel = Integer.parseInt(request.getParameter("ReorderLevel"));
        boolean Discontinued = Boolean.parseBoolean(request.getParameter("Discontinued"));

        ProductDAO pd = new ProductDAO();
        Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
        pd.editProduct(p);

        response.sendRedirect("product-list");
    }
}
