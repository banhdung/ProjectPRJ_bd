/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Categories;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author banhdung
 */
public class CategoriesDAO extends DBContext {

    public ArrayList<Categories> getCategories() {
        ArrayList<Categories> categories = new ArrayList<Categories>();

        try {
            String sql = "select * from Categories;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");

                Categories c = new Categories(CategoryID, CategoryName, Description, Picture);
                categories.add(c);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return categories;
    }
    
    public Categories getCategoryById(int id) {
        Categories category = null;
        try {
            String sql = "select * from Categories where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");

                category = new Categories(CategoryID, CategoryName, Description);

                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return category;
    }

    public ArrayList getCategoryID() {
        ArrayList<Integer> category = new ArrayList<>();
        try {
            String sql = "select CategoryID from Categories";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
//                String CategoryName = rs.getString("CategoryName");
//                String Description = rs.getString("Description");

//                Category c = new Category(CategoryID, CategoryName, Description);

                category.add(CategoryID);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return category;
    }

    public static void main(String[] args) {
        ArrayList<Categories> list = new CategoriesDAO().getCategories();
        for (Categories categories : list) {
            System.out.println(categories);
        }
    }

}
