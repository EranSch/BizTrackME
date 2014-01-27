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
   * Constructor to establish database connection.
   * @param location URI location of server, (e.g. 'jdbc:mysql://localhost/it351db')
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
      BizTrackMEServer.logEvent("error", "Query failed!\n"+ex.getMessage());
      return null;
    }
  }
  
  /**
   * Preformats a basic INSERT query. Parameters B and C are String[]'s, 
   * comprising of the fields and values to push into the DB
   * @param table
   * @param fields
   * @param values 
   */
  public void insert( String table, String[] fields, String[] values ){
    
    String query = "INSERT INTO `" + table + "` "
      + "(" + this.convertToCS(fields, "`") + ")"
      + " VALUES "
      + "(" + this.convertToCS(values, "'") + ")";
    
    BizTrackMEServer.logEvent("event", "Issuing Query [" + query + "]");
    
    try {
      c.createStatement().execute(query);
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Query failed!\n"+ex.getMessage());
    }
  }
  
  /**
   * Preformats the generic UPDATE query as well as handles all associated 
   * errors. Parameters B and C are String[]'s, comprising of the fields and 
   * values to push into the DB
   * @param table Either 'products' or 'customers'
   * @param ID
   * @param fields
   * @param values 
   */
  public void update( String table, String ID, String[] fields, String[] values ){
    
    // Determine the column name for the PK
    String idField = ( table.equalsIgnoreCase("products") ) ? "product_id" : "customer_id";
    
    // Start the query
    String query = "UPDATE `" + table + "` SET ";
    
    // Loop through each field and add to the query
    for(int i=0; i < fields.length; i++){
      query += "`" + fields[i] + "` = '" + values[i] + "'";
      if(i+1 != fields.length){
        query += ",";
      }
    }
      
    // Finish the query
    query += " WHERE `" + idField + "` = " + ID ;

    BizTrackMEServer.logEvent("event", "Issuing Query [" + query + "]");

    try {
      c.createStatement().execute(query);
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Query failed!\n" + ex.getMessage());
    }
  }
  
  /**
   * Inserts a Customer object into the database
   * @param c Customer 
   */
  public void addCustomer(Customer c){
    this.insert(
      "customers",
      new String[]{"first_name", "last_name", "address", "phone"},
      new String[]{
        c.getFirstName(),
        c.getLastName(),
        c.getAddress(),
        c.getPhone()
      }
    );
  }
  
  /**
   * Inserts a Product object into the database
   * @param p Product
   */
  public void addProduct(Product p){
    this.insert(
      "products", 
      new String[]{"product_name","sku","price", "color"},
      new String[]{ 
        p.getProductName(), 
        p.getSku(), 
        String.valueOf(p.getPrice()), 
        p.getColor()
      } 
    );
  }
  
  /**
   * Get an ArrayList containing all Product objects in the DB.
   * @return ArrayList<Product>
   */
  public ArrayList<Product> getProducts(){
    
    try {
      ArrayList<Product> products = new ArrayList();

      ResultSet rs = this.query("SELECT * FROM products");

      while (rs.next() == true) {
        products.add(new Product(
          rs.getInt("product_id"),
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
  
  /**
   * Get an ArrayList containing all Customer objects in the DB.
   * @return ArrayList<Customer>
   */
  public ArrayList<Customer> getCustomers(){
    
    try {
      ArrayList<Customer> customers = new ArrayList();
      
      ResultSet rs = this.query("SELECT * FROM customers");
      
      while (rs.next() == true){
        customers.add(new Customer(
          rs.getInt("customer_id"),
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

  /**
   * Simple method to convert a String[] into a single comma separated String.
   * Parameter B is the character used to encapsulate each item. As in a single
   * or double quotation.
   * @param array String[] to parse
   * @param sep Character to encapsulate each entry
   * @return String Comma separated string.
   */
  private String convertToCS(String[] array, String sep) {
    StringBuilder sb = new StringBuilder();
    for (String n : array) {
      if (sb.length() > 0) {
        sb.append(',');
      }
      sb.append(sep).append(n).append(sep);
    }
    return sb.toString();
  }

  /**
   * Submits a fuzzy query to the customers table, searching the first and last
   * name columns for the supplied query
   * @param query
   * @return  Multi-line string; 1 line per match
   */
  String searchCustomers(String query) {
    
    StringBuilder result = new StringBuilder();
    String separator = "  |  ";
    
    try {
      ResultSet rs = this.query(
        "SELECT * FROM customers " + 
          "WHERE `first_name` LIKE '%" + query + "%'" + 
          "OR `last_name` LIKE '%" + query + "%'");
      
      while (rs.next() == true) {
        result.append(rs.getString("customer_id")).append(separator);
        result.append(rs.getString("first_name")).append(" ");
        result.append(rs.getString("last_name")).append(separator);
        result.append(rs.getString("address")).append(separator);
        result.append(rs.getString("phone")).append("\n");
      }
      
      return result.toString();
      
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Query Error!");
    }
    
    return null;
  }

  /**
   * Submits a fuzzy query to the products table, searching the product
   * name column for the supplied query
   *
   * @param query
   * @return Multi-line string; 1 line per match
   */
  String searchProducts(String query) {
    
    StringBuilder result = new StringBuilder();
    String separator = "  |  ";

    try {
      ResultSet rs = this.query(
        "SELECT * FROM products "
        + "WHERE `product_name` LIKE '%" + query + "%'");

      while (rs.next() == true) {
        result.append(rs.getString("product_id")).append(separator);
        result.append(rs.getString("product_name")).append(separator);
        result.append(rs.getString("sku")).append(separator);
        result.append(rs.getString("price")).append(separator);
        result.append(rs.getString("color")).append("\n");
      }

      return result.toString();

    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Query Error!");
    }

    return null;
  }

  /**
   * Updates a single product identified by ID. The given Product object will
   * replace all fields for the corresponding ID!
   * @param ID
   * @param p Product
   */
  void updateProduct(String ID, Product p) {
    this.update(
      "products",
      ID,
      new String[]{"product_name", "sku", "price", "color"},
      new String[]{
        p.getProductName(),
        p.getSku(),
        String.valueOf(p.getPrice()),
        p.getColor()
      }
    );
  }

  /**
   * Updates a single customer identified by ID. The given Customer object will
   * replace all fields for the corresponding ID!
   * @param ID
   * @param c Customer
   */
  void updateCustomer(String ID, Customer c) {
    this.update(
      "customers", 
      ID, 
      new String[]{"first_name","last_name","address", "phone"}, 
      new String[]{
        c.getFirstName(),
        c.getLastName(),
        c.getAddress(),
        c.getPhone()
      }
    );
  }

  /**
   * Removes an entry from the DB. 
   * @param table
   * @param ID 
   */
  public void remove(String table, String ID) {
    
    // Determine the correct PK field name
    String idField = ( table.equalsIgnoreCase("products") ) ? "product_id" : "customer_id";
    
    // Build query
    String query = "DELETE from `" + table + "` WHERE `" + idField + "` = " + ID;
    
    BizTrackMEServer.logEvent("event", "Issuing Query [" + query + "]");

    try {
      c.createStatement().execute(query);
    } catch (SQLException ex) {
      BizTrackMEServer.logEvent("error", "Query failed!\n" + ex.getMessage());
    } 
  }
}
