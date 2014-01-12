package biztrackme.client;

import biztrackme.common.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eran
 */
public class BizTrackMEClient {
  
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private Socket serverSocket;
  private ProductStore p;
  private CustomerStore c;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    BizTrackMEClient client = new BizTrackMEClient();
    
    BufferedReader input = new BufferedReader(
      new InputStreamReader(System.in)
    );
    
    try {
      
      // Initialize the connection and populate data stores
      client.connect("localhost", 12345);
      client.establishStreams();     
      client.populateStores();
      
      System.out.println( client.getP().getRecords().size() 
        + " products received");
      
      System.out.println( client.getC().getRecords().size() 
        + " customers received");
      
      client.sendString("TERMINATE");
      
      
    } catch (IOException ex) {
      Logger.getLogger(BizTrackMEClient.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
  /**
   * Connect to a server's socket using the provided host and port
   * @param host
   * @param port
   * @throws IOException 
   */
  public void connect( String host, int port ) throws IOException{
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
  private void populateStores() {
    
    // Get products
    try {
      this.p = (ProductStore) this.getObject("VIEW_PROD");   
    }catch(Exception ex){
      System.err.println("Unexpected object type.");
    }
    
    // Get customers
    try {
      this.c = (CustomerStore) this.getObject("VIEW_CUST"); 
    }catch(Exception ex){
      System.err.println("Unexpected object type.");
    }
    
  }

  /**
   * Retrieve the products datastore
   * @return ProductStore
   */
  public ProductStore getP() {
    return p;
  }

  /**
   * Retreive the customers datastore
   * @return CustomerStore
   */
  public CustomerStore getC() {
    return c;
  }
  
}
