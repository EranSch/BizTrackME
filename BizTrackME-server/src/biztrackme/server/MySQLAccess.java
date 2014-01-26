package biztrackme.server;

import biztrackme.common.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eran
 */
public class MySQLAccess {
  
  private final String driver = "org.gjt.mm.mysql.Driver";
  private Connection c;

  /**
   * Constructor to establish connection.
   * @param location
   * @param user
   * @param password 
   */
  public MySQLAccess(String location, String user, String password) {    
    try{
      Class.forName(driver);
      c = DriverManager.getConnection(location, user, password);
      BizTrackMEServer.logEvent("event", "Database connected! [" + location + "]");
    }catch( ClassNotFoundException ex ){
      BizTrackMEServer.logEvent("error", "Database driver error!");
    }catch(SQLException ex){
      BizTrackMEServer.logEvent("error", "Database connection error!");
    }    
  }
  
  /**
   * Attempt to close the connection to the database.
   */
  public void close(){
    try {
      c.close();
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Error closing connection!");
    }
  }
  
  /**
   * Perform a generic query, get a result set.
   * @param query
   * @return 
   */
  public ResultSet query(String query){    
    ResultSet rs;
    try {
      rs = c.createStatement().executeQuery(query);
      return rs;
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Query failed!");
      return null;
    }
  }
  
  public void addCustomer(Customer c){
    // TODO Add customer
  }
  
  public void addProduct(Product p){
    // TODO Add product
  }
  
  public ArrayList<Product> getProducts(){
    
    try {
      ArrayList<Product> products = new ArrayList();

      ResultSet rs = this.query("SELECT * FROM products");

      while (rs.next() == true) {
        products.add(new Product(
          rs.getString("product_name"),
          rs.getString("sku"),
          rs.getDouble("price"),
          rs.getString("color")
        ));
      }
      return products;
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Get products failed.");
    }

    return null;
    
  }
  
  public ArrayList<Customer> getCustomers(){
    
    try {
      ArrayList<Customer> customers = new ArrayList();
      
      ResultSet rs = this.query("SELECT * FROM customers");
      
      while (rs.next() == true){
        customers.add(new Customer(
          rs.getString("first_name"),
          rs.getString("last_name"),
          rs.getString("address"),
          rs.getString("phone")
        ));   
      }
      return customers;
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Get customers failed.");
    }
    
    return null;
    
  }
  
  
}
