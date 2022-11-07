
package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.OrderDAO;
import models.ProductDAO;

/**
 *
 * @author Asus
 */
public class ProductDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ProductID = Integer.parseInt(request.getParameter("id"));
        ProductDAO pd = new ProductDAO();
        if(new OrderDAO().getAllOrder_DetailsByPid(ProductID)!=null){
//            request.setAttribute("mess", "Delete Fail because product in User's order");
        }
        else{
            pd.deleteProductById(ProductID);
        }

        response.sendRedirect("product-list");
    }
}
