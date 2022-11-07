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
public class AmountProductInCart extends HttpServlet {
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
        int id, num;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            num = Integer.parseInt(req.getParameter("num"));

            if ((num == -1) && (sc.getQuantityById(id) == 1)) {
                sc.removeItem(id);
            } else {
                Product p = new ProductDAO().getProductById(id);
                ItemCart t = new ItemCart(p, num, p.getUnitPrice());
                sc.addItem(t);
            }
        } catch (Exception e) {
        }

        List<ItemCart> list = sc.getItems();

        req.getSession().setAttribute("t", sc.getToltalMoney());
        req.getSession().setAttribute("cast", sc);
        req.getSession().setAttribute("size", list.size());
        resp.sendRedirect("cart.jsp");
    }

}


