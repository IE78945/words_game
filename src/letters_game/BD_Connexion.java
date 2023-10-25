/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package letters_game;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class BD_Connexion {
    private static Connection con;
    static {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/letters_game";
            String user="root";
            String pass="";
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("connected");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
       
    }
        
    public static  Connection getConnection(){return con;}
    
    
    
}
