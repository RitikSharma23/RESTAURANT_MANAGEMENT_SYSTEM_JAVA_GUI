import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import Foodinfo.Food;

import java.awt.BorderLayout;
import java.util.*;
class sqlfunction{
    Users u=new Users();

    public sqlfunction(){}

    public void insertUser(String name, String type, String phone, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
    
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
    
            String query = "INSERT INTO user (name, type, phone, password) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
    
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setString(3, phone);
            pstmt.setString(4, password);
    
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");
        } catch (SQLException ex) {
            System.out.println("Error inserting user: " + ex.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing connection: " + ex.getMessage());
            }
        }
    }
    



}


