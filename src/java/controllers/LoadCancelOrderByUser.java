package controllers;


import dal.Account;
import dal.Customer;
import dal.Order_Detail;
import dal.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CustomerDAO;
import models.OrderDAO;

/**
 *
 * @author banhdung
 */
public class LoadCancelOrderByUser extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        OrderDAO dao = new OrderDAO();
        try {
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            Customer cus = new CustomerDAO().getCustomerByID(acc.getCustomerID());
            ArrayList<Orders> listOrder = new OrderDAO().getListOrderByCusID(cus.getCustomerID());
            ArrayList<Order_Detail> listOrderDetail = new OrderDAO().getListOrderDetailByOID(listOrder);
            req.getSession().setAttribute("listOrder", listOrder);
            req.getSession().setAttribute("listOrderDetail", listOrderDetail);
            req.getRequestDispatcher("CancelOrderUser.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
        }
    }
    
}
