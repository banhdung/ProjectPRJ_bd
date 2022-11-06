package models;

import dal.DBContext;
import dal.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBContext {

    // Action: Read all products
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "select * from Products;";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                // Khoi tao doi tuong kieu 'Product'
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Bo sung 'p' vao danh sach 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public Product getProductById(int ProductID) {
        try {
            String sql = "select * from Products where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ProductID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ProductID2 = rs.getInt(1);
                String ProductName = rs.getString(2);
                int CategoryID = rs.getInt(3);
                String QuantityPerUnit = rs.getString(4);
                double UnitPrice = rs.getDouble(5);
                int UnitsInStock = rs.getInt(6);
                int UnitsOnOrder = rs.getInt(7);
                int ReorderLevel = rs.getInt(8);
                boolean Discontinued = rs.getBoolean(9);

                Product p = new Product(ProductID2, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void editProduct(Product p) {
        try {
            String sql = "update Products set ProductName=?, CategoryID=?, QuantityPerUnit=?, UnitPrice=?, \n"
                    + "UnitsInStock=?, UnitsOnOrder=?, ReorderLevel=?, Discontinued=? where ProductID=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
            ps.setInt(6, p.getUnitsOnOrder());
            ps.setInt(7, p.getReorderLevel());
            ps.setBoolean(8, p.isDiscontinued());
            ps.setInt(9, p.getProductID());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteProductById(int ProductID) {
        try {
            String sql1 = "delete from Products where ProductID = ?";
            String sql2 = "delete from \"Order Details\" where ProductID=?";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);

            ps2.setInt(1, ProductID);
            ps2.executeUpdate();

            ps1.setInt(1, ProductID);
            ps1.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int addProduct(Product p) {
        int result = 0;
        try {
            String sql = "insert Products\n"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setString(2, String.valueOf(p.getCategoryID()));
            ps.setString(3, p.getQuantityPerUnit());
            ps.setString(4, String.valueOf(p.getUnitPrice()));
            ps.setString(5, String.valueOf(p.getUnitsInStock()));
            ps.setString(6, String.valueOf(p.getUnitsOnOrder()));
            ps.setString(7, String.valueOf(p.getReorderLevel()));
            ps.setString(8, String.valueOf(p.isDiscontinued()));

            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<Product> SerachProductByName(String txtSearch) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "select * from Products where ProductName like ?;";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                // Khoi tao doi tuong kieu 'Product'
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Bo sung 'p' vao danh sach 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Product> getProductsByPage(int page, int elements) {
        ArrayList<Product> products = new ArrayList<>();
        int start = page * elements - elements;
        try {
            String sql = "SELECT *FROM products\n"
                    + "ORDER BY\n"
                    + "   ProductID\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, elements);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategoryID(rs.getInt("CategoryID"));
                p.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                p.setUnitPrice(rs.getDouble("UnitPrice"));
                p.setUnitsInStock(rs.getInt("UnitsInStock"));
                p.setUnitsOnOrder(rs.getInt("UnitsOnOrder"));
                p.setReorderLevel(rs.getInt("ReorderLevel"));
                p.setDiscontinued(rs.getBoolean("Discontinued"));
                products.add(p);
            }
        } catch (Exception e) {
        }
        return products;
    }

    public ArrayList<Product> getProductsByPageandID(int page, int elements, String productName) {
        ArrayList<Product> products = new ArrayList<>();
        int start = page * elements - elements;
        try {
            String sql = "SELECT *FROM products where ProductName like ?\n "
                    + "ORDER BY\n"
                    + "   ProductID\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + productName + "%");
            ps.setInt(2, start);
            ps.setInt(3, elements);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategoryID(rs.getInt("CategoryID"));
                p.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                p.setUnitPrice(rs.getDouble("UnitPrice"));
                p.setUnitsInStock(rs.getInt("UnitsInStock"));
                p.setUnitsOnOrder(rs.getInt("UnitsOnOrder"));
                p.setReorderLevel(rs.getInt("ReorderLevel"));
                p.setDiscontinued(rs.getBoolean("Discontinued"));
                products.add(p);
            }
        } catch (Exception e) {
        }
        return products;
    }

    public ArrayList<Product> SerachProductByCateID(int CateID) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "select * from Products where CategoryID =  ?;";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, CateID);
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                // Khoi tao doi tuong kieu 'Product'
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Bo sung 'p' vao danh sach 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }
    
    public ArrayList<Product> SerachandFilter(int CateID , String txtSearch) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "select * from products where CategoryID =  ? and ProductName like  ?;";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, CateID);
            ps.setString(2, "%"+txtSearch+"%");
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                // Khoi tao doi tuong kieu 'Product'
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Bo sung 'p' vao danh sach 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Product> getProductsHot() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "select top(4) * from Products p , [Order Details] o\n"
                    + "where p.ProductID = o.ProductID\n"
                    + "order by o.Discount desc;";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                // Khoi tao doi tuong kieu 'Product'
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Bo sung 'p' vao danh sach 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Product> getProductsNew() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "select top(4)* from Products \n"
                    + "order by ProductID desc";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                // Khoi tao doi tuong kieu 'Product'
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Bo sung 'p' vao danh sach 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Product> getProductsBest() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            String sql = "select top(4)  p.ProductName,p.ProductID,p.CategoryID,p.Discontinued,p.QuantityPerUnit,p.ReorderLevel,p.UnitPrice,p.UnitsInStock,p.UnitsOnOrder,count(o.ProductID) as NumberOfOrder from Products p , [Order Details] o \n"
                    + "where p.ProductID = o.ProductID\n"
                    + "group by p.ProductName,p.ProductID,p.CategoryID,p.Discontinued,p.QuantityPerUnit,p.ReorderLevel,p.UnitPrice,p.UnitsInStock,p.UnitsOnOrder\n"
                    + "order by NumberOfOrder desc";
            // B2: Tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            // B3: Thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // B4: Xu ly ket qua tra ve
            while (rs.next()) {
                // Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                // Khoi tao doi tuong kieu 'Product'
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Bo sung 'p' vao danh sach 'products'
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }
    public static void main(String[] args) {
        System.out.println(new ProductDAO().SerachandFilter(1,""));
    }
}
