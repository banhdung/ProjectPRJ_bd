package models;

import dal.*;
import dal.Customer;
import dal.DBContext;
import dal.ItemCart;
import dal.Orders;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAO extends DBContext {

    public void addOrder(Customer cus, Cart cart, String RequiedDate) {
        try {
            String sql1 = "INSERT INTO dbo.Orders\n"
                    + "(\n"
                    + "    CustomerID,\n"
                    + "    EmployeeID,\n"
                    + "    OrderDate,\n"
                    + "    RequiredDate,\n"
                    + "    ShippedDate,\n"
                    + "    Freight,\n"
                    + "    ShipName,\n"
                    + "    ShipAddress,\n"
                    + "    ShipCity,\n"
                    + "    ShipRegion,\n"
                    + "    ShipPostalCode,\n"
                    + "    ShipCountry\n"
                    + ")\n"
                    + "VALUES\n"
                    + "(   ?,       -- CustomerID - nchar(5)\n"
                    + "    NULL,         -- EmployeeID - int\n"
                    + "    GETDATE(), -- OrderDate - datetime\n"
                    + "    ?, -- RequiredDate - datetime\n"
                    + "    NULL, -- ShippedDate - datetime\n"
                    + "    NULL,      -- Freight - money\n"
                    + "    ?,       -- ShipName - nvarchar(40)\n"
                    + "    ?,       -- ShipAddress - nvarchar(60)\n"
                    + "    NULL,       -- ShipCity - nvarchar(15)\n"
                    + "    NULL,       -- ShipRegion - nvarchar(15)\n"
                    + "    NULL,       -- ShipPostalCode - nvarchar(10)\n"
                    + "    NULL        -- ShipCountry - nvarchar(15)\n"
                    + "    )";

            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, cus.getCustomerID());
            ps1.setString(2, RequiedDate);
            ps1.setString(3, cus.getContactName());
            ps1.setString(4, cus.getAddress());
            ps1.executeUpdate();
            //lay ra orderID cua sql1 vua add
            String sql2 = "SELECT TOP 1\n"
                    + "       OrderID\n"
                    + "FROM dbo.Orders\n"
                    + "ORDER BY OrderID DESC;";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ResultSet rs = ps2.executeQuery();
            //add vao bang order details
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                for (ItemCart i : cart.getItems()) {
                    String sql3 = "INSERT INTO dbo.[Order Details]\n"
                            + "(\n"
                            + "    OrderID,\n"
                            + "    ProductID,\n"
                            + "    UnitPrice,\n"
                            + "    Quantity,\n"
                            + "    Discount\n"
                            + ")\n"
                            + "VALUES\n"
                            + "(   ?,    -- OrderID - int\n"
                            + "    ?,    -- ProductID - int\n"
                            + "    ?, -- UnitPrice - money\n"
                            + "    ?,    -- Quantity - smallint\n"
                            + "    ?   -- Discount - real\n"
                            + "    );";
                    PreparedStatement ps3 = connection.prepareStatement(sql3);
                    ps3.setInt(1, orderID);
                    ps3.setInt(2, i.getProduct().getProductID());
                    ps3.setDouble(3, i.getPrice());
                    ps3.setInt(4, i.getQuantity());
                    ps3.setDouble(5, 0.0);
                    ps3.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Orders> getListOrderByCusID(String cid) {
        ArrayList<Orders> li = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM Orders\n"
                    + "WHERE CustomerID = ? order by OrderID desc;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                li.add(new Orders(rs.getInt("OrderID"), rs.getString("CustomerID"), rs.getDate("OrderDate"), rs.getDate("RequiredDate"), rs.getDate("ShippedDate"), rs.getString("ShipName"), rs.getString("ShipAddress")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return li;
    }

    public ArrayList<Order_Detail> getListOrderDetailByOID(ArrayList<Orders> lobid) {
        ArrayList<Order_Detail> li = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM dbo.[Order Details]\n"
                    + "WHERE OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Orders o : lobid) {
                ps.setInt(1, o.getOrderID());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int OrderID = rs.getInt("OrderID");
                    int ProductID = rs.getInt("ProductID");
                    double UnitPrice = rs.getDouble("UnitPrice");
                    int Quantity = rs.getInt("Quantity");
                    li.add(new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, 0));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return li;
    }

    public ArrayList<Orders> getListOrders() {
        ArrayList<Orders> list = new ArrayList<>();
        try {
            String sql = "select * from orders order by OrderID desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                list.add(new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Orders> FilterDate(int page, int elements, String startdate, String end) {
        ArrayList<Orders> list = new ArrayList<>();
        int start = page * elements - elements;
        try {
            String sql = "select * from Orders o where o.OrderDate between ? and ? order by o.OrderID desc OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startdate);
            ps.setString(2, end);
            ps.setInt(3, start);
            ps.setInt(4, elements);
            

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                list.add(new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public ArrayList<Orders> getListOrderByDate(String startdate, String end) {
        ArrayList<Orders> list = new ArrayList<>();
     
        try {
            String sql = "select * from Orders o where o.OrderDate between ? and ? order by o.OrderID desc ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, startdate);
            ps.setString(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                list.add(new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void cancelOrderByID(int oid) {
        String sql = "update orders set [RequiredDate] = null where OrderID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Order_Detail> getAllOrder_Details() {
        ArrayList<Order_Detail> ordetail = new ArrayList<>();

        try {
            String sql = "Select * from [Order Details]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                ordetail.add(ordt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ordetail;
    }

    public ArrayList<Orders> getOrderByPage(int page, int elements) {
        ArrayList<Orders> orders = new ArrayList<>();
        int start = page * elements - elements;
        try {
            String sql = "SELECT *FROM Orders\n"
                    + "ORDER BY\n"
                    + "   OrderID desc\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, elements);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderID(rs.getInt(1));
                o.setCustomerID(rs.getString(2));
                o.setOrderDate(rs.getDate(4));
                o.setRequiredDate(rs.getDate(5));
                o.setShippedDate(rs.getDate(6));
                o.setFreight(rs.getDouble(7));
                o.setShipName(rs.getString(8));
                o.setShipAddress(rs.getString(9));
                orders.add(o);
            }
        } catch (Exception e) {
        }
        return orders;
    }

    public int countOrderByMonth(int month, int year) {
        try {
            String sql = "SELECT count(*) from Orders \n"
                    + "where MONTH(OrderDate) = ? and YEAR(OrderDate) = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Order_Detail> getWeeklySale() {
        ArrayList<Order_Detail> ord = new ArrayList<>();
        LocalDate curD = java.time.LocalDate.now();
        String date = curD.toString();
        try {
            String sql = "select od.OrderID, od.ProductID, od.UnitPrice, od.Quantity, od.Discount from [Order Details]  od\n"
                    + "join Orders o\n"
                    + "on o.OrderID = od.OrderID\n"
                    + "where ? - o.OrderDate < 7";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                ord.add(ordt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ord;
        
    }
    
     public ArrayList<Order_Detail> getAllOrder_DetailsByPid(int productid) {
        ArrayList<Order_Detail> ordetail = new ArrayList<>();

        try {
            String sql = "Select * from [Order Details] where ProductID= ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                ordetail.add(ordt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ordetail;
    }
    
    

}
