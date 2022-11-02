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

public class ProductListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> proList = new ProductDAO().getProducts();
        // Chuyen tiep du lieu ra View
        req.setAttribute("products", proList);
        String cateIDx = req.getParameter("cateID");
        ArrayList<Categories> listCate = (new CategoriesDAO()).getCategories();
        int cateID = Integer.parseInt(cateIDx);
        Categories c = new CategoriesDAO().getCategoryById(cateID);
        String nameCate = c.getCategoryName();
        req.setAttribute("nameCate", nameCate);
        req.setAttribute("cateID", cateID);
        req.setAttribute("category", listCate);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

}
