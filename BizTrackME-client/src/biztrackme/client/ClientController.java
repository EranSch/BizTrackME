package biztrackme.client;

import biztrackme.common.*;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Eran
 */
public final class ClientController {
  
  String SERVER_HOSTNAME = "localhost";
  int    SERVER_PORT     = 12345;
  
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private Socket serverSocket;
  private ArrayList<Product> p;
  private ArrayList<Customer> c;

  public ClientController() {
        
    try {
      
      // Initialize the connection and populate data stores
      this.connect(SERVER_HOSTNAME,SERVER_PORT);
      this.establishStreams();     
      this.populateStores();
      
      System.out.println( this.p.size() 
        + " products received");
      
      System.out.println( this.c.size() 
        + " customers received");      
      
    } catch (IOException ex) {
      System.out.print("Error connecting to server!");
    }
    
    /*
     * This method registers a shutdown hook for the client. The intention is
     * to dismantle the network connections gently.
     */
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        closeConnections();
      }
    });

  }
  
  /**
   * Connect to a server's socket using the provided host and port
   * @param host
   * @param port
   * @throws IOException 
   */
  public void connect(String host, int port) throws IOException{
    serverSocket = new Socket(host, port);
  }
  
  /**
   * After connecting to a server, consider invoking this method to 
   * assign I/O streams to instance variables.
   * @throws IOException 
   */
  public void establishStreams() throws IOException{
    
    // This streams data TO the client
    out = new ObjectOutputStream(
      serverSocket.getOutputStream()
    );
    out.flush();
    
    // This streams data FROM the client
    in = new ObjectInputStream(
      serverSocket.getInputStream()
    );
    
  }
  
  /**
   * Send a UTF encoded string to connected server.
   * @param message 
   */
  public void sendString(String message){
    try {
      out.writeUTF(message);
      out.flush();
      System.out.println("Message sent. ["+message+"]");
    } catch (IOException ex) {
      System.err.println("Failed to send message");
    }
  }
  
  /**
   * Read a UTF encoded string from the connected server.
   * @return 
   */
  public String readString(){
    String input;
    try {
      input = in.readUTF();
    } catch (IOException ex) {
      System.err.println("Cannot read message");
      input = "ERR";
    }
    return input;
  }
  
  /**
   * Passes String parameter to server and expects the return of an Object.
   * The return is indeed castable so be sure to know what to expect and
   * cast the return accordingly.
   * @param message
   * @return Object
   */
  public Object getObject(String message){
    this.sendString(message);
    Object incoming = null;
    try {
      incoming = in.readObject();
    } catch (IOException ex) {
      System.err.println("IO Error\n"+ex.getMessage());
    } catch (ClassNotFoundException ex) {
      System.err.println("Unexpected object type.");
    }
    return incoming;
  }

  /**
   * Re-sync the server's data stores with the client's. Don't run this 
   * before a socket as well as the streams have been established.
   */
  public void populateStores() {
    
    // Get products
    try {
      this.p = (ArrayList<Product>) this.getObject("VIEW_PROD");
    }catch(Exception ex){
      System.err.println("Unexpected object type.");
    }
    
    // Get customers
    try {
      this.c = (ArrayList<Customer>) this.getObject("VIEW_CUST"); 
    }catch(Exception ex){
      System.err.println("Unexpected object type.");
    }
  }
  
  /**
   * Attempts to disassemble all stream and and socket.
   */
  public void closeConnections(){
    this.sendString("CLIENT_DISCONNECT");
    try {
      if (out != null) {
        out.flush();
        out.close();
      }
      if (in != null) {
        in.close();
      }
      if (serverSocket != null) {
        serverSocket.close(); 
      }
    } catch (IOException ex) {
      System.err.println("Failed to close all streams\n" + ex.getMessage());
    } 
  }

  /**
   * Sends a new Product object back to the server and, assuming it is received,
   * also adds the new object to the local datastore. This method also updates
   * the relevant status JLabel to update the UI with confirmation or error.
   * @param prodStatus
   * @param prodName
   * @param prodSKU
   * @param prodPrice 
   */
  void addProduct(
    JLabel prodStatus, 
    JTextField prodName, 
    JTextField prodSKU, 
    JTextField prodPrice,
    JTextField prodColor) {
    
    // Create object, then write to server
    try{
      Product prod = new Product(
        prodName.getText(),
        prodSKU.getText(),
        Double.parseDouble(prodPrice.getText()),
        prodColor.getText()
      );
      this.sendString("ADD_PROD");
      out.writeObject(prod);
      out.flush();
      
      this.p.add(prod);
      
      prodStatus.setText("Product added!");
      prodStatus.setForeground(Color.black);
      
    }catch(NumberFormatException ex){
      System.err.println("Cannot parse given price");
      prodStatus.setText("Error parsing price.");
      prodStatus.setForeground(Color.red);
    }catch(IOException ex){
      System.err.println("IO Error!");
      prodStatus.setText("Error connecting to server :(");
      prodStatus.setForeground(Color.red);
    }   
  }

  /**
   * Sends a new Customer object back to the server and, assuming it is received,
   * also adds the new object to the local datastore. This method also updates
   * the relevant status JLabel to update the UI with confirmation or error.   
   * @param custStatus
   * @param custName
   * @param custAddress
   * @param custPhone 
   */
  void addCustomer(
    JLabel custStatus, 
    JTextField custFirstName, 
    JTextField custLastName, 
    JTextField custAddress, 
    JTextField custPhone) {

    // Create object, then write to server
    try {
      Customer cust = new Customer(
        custFirstName.getText(),
        custLastName.getText(),
        custAddress.getText(),
        custPhone.getText()
      );
      this.sendString("ADD_CUST");
      out.writeObject(cust);
      out.flush();

      this.c.add(cust);

      custStatus.setText("Customer added!");
      custStatus.setForeground(Color.black);

    } catch (IOException ex) {
      System.err.println("IO Error!");
      custStatus.setText("Error connecting to server :(");
      custStatus.setForeground(Color.red);
    }
  
  }

  public String search(String recordType, String query) {
    
    String result = "No record found";
    
    switch(recordType){
      
      case "cust":
       for( Customer cust :  this.c ){
         if(cust.getFirstName().toLowerCase().contains(query.toLowerCase())){
           result = cust.toString();
           break;
         }
       }
      case "prod":
        for( Product prod : this.p ){
          if(prod.getProductName().toLowerCase().contains(query.toLowerCase())){
            result = prod.toString();
            break;
          }
        }
      
    }
    
    return result; 
    
  }

  public ArrayList<Product> getP() {
    return p;
  }

  public ArrayList<Customer> getC() {
    return c;
  }
  
  public CustomerTableModel buildCustModel(){
    CustomerTableModel ctm = new CustomerTableModel(this.c);
    return ctm;
  }
  
  public ProductTableModel buildProdModel(){
    ProductTableModel ptm = new ProductTableModel(this.p);
    return ptm;
  }
  
}
