/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import data_model.User;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brb25
 */
@WebService(serviceName = "DatabaseConnection")
@Stateless()
public class DatabaseConnection {
   private Connection con = null;
   
   

    private void start_connection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/WIZARD",
                    "root",
                    "root");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            close_connection();
        }
    }

    private void close_connection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex1);
            } finally {
                con = null;
            }
        }
    }

    private ResultSet get_registers(String query) {
        start_connection();
        if (con != null) {
            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query);
                return rs;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    private void update_register(String query){
        start_connection();
        if (con != null) {
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                close_connection();
            }
        }
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserData")
    public User getUserData(@WebParam(name = "username") String username) {
        //TODO write your implementation code here:
        User user = new User();
        try{

            start_connection();
            ResultSet rs = get_registers("SELECT * FROM USERS WHERE NAME = '" + username + "'");
            while(rs.next()) {
                user.id = rs.getInt("ID");
                user.name = rs.getString("NAME");
                user.pass = rs.getString("PASS");
            }

            close_connection(); 
        }catch(Exception ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
    
    
}
