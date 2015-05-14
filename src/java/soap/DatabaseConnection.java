/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import data_model.Database;
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
import java.util.ArrayList;
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
   
   

    private void start_connection(String schema) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/WIZARD",
                    "root",
                    "root");
            con.setSchema(schema);
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
    
        
    private void create_schema(String database_name){
        start_connection("ROOT");
        if (con != null) {
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate("CREATE SCHEMA " + database_name + " AUTHORIZATION root");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                close_connection();
            }
        }
    }

    private ResultSet execute_query(String query, String schema) {
        start_connection(schema);
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
    
    private ResultSet execute_update(String query, String schema) {
        start_connection(schema);
        if (con != null) {
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(query);
                con.commit();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserData")
    public User getUserData(@WebParam(name = "username") String username) {
        //TODO write your implementation code here:
        User user = new User();
        try{

            ResultSet rs = execute_query("SELECT * FROM USRS WHERE NAME = '" + username + "'","ROOT");
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createDatabase")
    public boolean createDataBase(@WebParam(name = "iduser") int iduser, @WebParam(name = "databasename") String databasename) {
        //TODO write your implementation code here:
        create_schema(databasename);
        close_connection();

        String query = "INSERT INTO USRS_SCHEMAS VALUES (" + iduser + ", '" + databasename + "' )";
        execute_update(query, "ROOT");
        close_connection();
        return false;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getDatabases")
    public ArrayList<Database> getDatabases(@WebParam(name = "iduser") int iduser) {
        //TODO write your implementation code here:

        ArrayList<Database> result = new ArrayList<Database>();
        try{

            ResultSet rs = execute_query("SELECT NAME_SCHEMA FROM USRS_SCHEMAS WHERE ID_USR = " + iduser + "", "ROOT");
            while(rs.next()) {
                Database dbs = new Database();
                dbs.name = rs.getString("NAME_SCHEMA");
                result.add(dbs);
            }

            close_connection(); 
        }catch(Exception ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    
}
