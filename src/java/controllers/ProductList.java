package controllers;

import dal.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.CategoriesDAO;
import models.ProductDAO;

public class ProductList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int page = 0;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                page = 1;
            }

            ProductDAO dao = new ProductDAO();
            int elements = 10;
            ArrayList<Product> products = dao.getProductsByPage(page, elements);
            int numberOfPage = dao.getProducts().size() % elements == 0 ? dao.getProducts().size() / elements : dao.getProducts().size() / elements + 1;
            request.setAttribute("category", new CategoriesDAO().getCategories());
            request.setAttribute("product", products);
            request.setAttribute("page", page);
            request.setAttribute("numberOfPage", numberOfPage);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
