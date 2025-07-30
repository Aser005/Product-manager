package dao;
import exception.InvalidProductException;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//my dataBase helper
public class Productdao {

    public void insertProductinDatabase(Product product){
        try{
            Connection conn=DriverManager.getConnection( "jdbc:mysql://localhost:3306/productdb", "root", "Asora2005#");
            String sql = "INSERT INTO products (name, price, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.executeUpdate();
            conn.close();

        }

        catch(SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();


        }

    }



    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection( "jdbc:mysql://localhost:3306/productdb", "root", "Asora2005#");
            String sql = "SELECT * FROM products";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("rs executed");
            while(rs.next()){
                Product p= new Product(rs.getInt("id"),rs.getDouble("price"),rs.getString("name"),rs.getString("description"));
                products.add(p);

            }
        }

        catch(SQLException e){
            System.out.println("Connection to the database failed");
            e.printStackTrace();
        } catch (InvalidProductException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;

    }




}
