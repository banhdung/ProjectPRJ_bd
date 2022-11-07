/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerDAO extends DBContext {

    public void addCustomer(Customer cus) {
        try {
            String sql = "INSERT INTO Customers"
                    + "  (CustomerID, CompanyName, ContactName, ContactTitle, Address) VALUES "
                    + " (?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cus.getCustomerID());
            ps.setString(2, cus.getCompanyName());
            ps.setString(3, cus.getContactName());
            ps.setString(4, cus.getContactTitle());
            ps.setString(5, cus.getAddress());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void addCustomer(String CusID, String CompanyName, String ContactName, String ContactTitle, String Address) {
        try {
            String sql = "INSERT INTO Customers"
                    + "  (CustomerID, CompanyName, ContactName, ContactTitle, Address) VALUES "
                    + " (?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, CusID);
            ps.setString(2, CompanyName);
            ps.setString(3, ContactName);
            ps.setString(4, ContactTitle);
            ps.setString(5, Address);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public Customer getCustomerByID(String ID) {
        Customer cus = null;
        try {
            String sql = "select * from Customers where CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");

                cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);
                return cus;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateCustomer(Customer cus) {
        String sql = "UPDATE [dbo].[Customers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + " WHERE CustomerID =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, cus.getCompanyName());
            ps.setString(2, cus.getContactName());
            ps.setString(3, cus.getContactTitle());
            ps.setString(4, cus.getAddress());
            ps.setString(5, cus.getCustomerID());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void updateProfile(String cusID, String CompanyName, String ContactName, String ContactTitle, String Address) {
        try {
            String sql = "UPDATE [dbo].[Customers]\n"
                    + "SET [CompanyName] = ?,\n"
                    + "    [ContactName] = ?,\n"
                    + "    [ContactTitle] = ?,\n"
                    + "    [Address] = ?\n"
                    + "WHERE CustomerID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, CompanyName);
            ps.setString(2, ContactName);
            ps.setString(3, ContactTitle);
            ps.setString(4, Address);
            ps.setString(5, cusID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    
    public int getCountCustomer() {
        try {
            String sql = "Select count(*) from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }  
    
 
    
    public static void main(String[] args) {
        Customer c = new Customer("dK2DK", "fbc", "h3", "h3", "abc");
        new CustomerDAO().updateCustomer(c);
    }

}
