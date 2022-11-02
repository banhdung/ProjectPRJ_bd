package models;

import dal.Account;
import dal.Customer;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountDAO extends DBContext {

    public  String randomString(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public Account getAccount(String email, String pass) {
        Account account = null;
        try {
            String sql = "select * from Accounts where Email=? and Password=?;";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //set cac gia tri cho cac tham so cua 'sql'
            ps.setString(1, email);
            ps.setString(2, pass);
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");
                account = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Account checkAccountExist(String email) {
        Account account = null;
        try {
            String sql = "select * from Accounts where Email=?;";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //set cac gia tri cho cac tham so cua 'sql'
            ps.setString(1, email);
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");
                account = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                return account;

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getAccount(Account acc, Customer cus) {
        int result1 = 0,result2 = 0;
        try {
        String sql ="insert into Customers values (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cus.getCustomerID());
            st.setString(2, cus.getCompanyName());
            st.setString(3, cus.getContactName());
            st.setString(4, cus.getContactTitle());
            st.setString(5, cus.getAddress());
            result1=st.executeUpdate();
            String sql1 ="insert into Accounts(Email,Password,CustomerID,Role) values (?,?,?,?)";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, acc.getEmail());
            st1.setString(2, acc.getPassword());
            st1.setString(3, cus.getCustomerID());
            st1.setInt(4, acc.getRole());
            result2=st1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result1!=0&&result2!=0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    
     public int getCountAccount() {
        try {
            String sql = "Select count(*) from Accounts";
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
//        System.out.println(new AccountDAO().getAccount("cust1@gmail.com", "123"));
//        System.out.println(new AccountDAO().checkAccountExist("cust1@gmail.com"));
        Account a = new Account(0, "cus1", "123", "1", 0, 2);
        Customer c = new Customer("1", "CompanyName", "ContactName", "ContactTitle", " Address");
        System.out.println(new AccountDAO().getAccount(a, c));
        System.out.println(new AccountDAO().getAccount("cust1@gmail.com", "123"));

    }
}
