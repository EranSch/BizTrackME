package biztrackme.server;

import biztrackme.common.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Eran
 */
public class Router implements Runnable {
  
  private static int threadCount;
  
  private final MySQLAccess db;
  private final Socket connection;
  
  private final int threadID;

  /**
   * This is the preferred means of instantiating this class. 
   * @param connection
   * @param db 
   */
  public Router(Socket connection, MySQLAccess db) {
    this.connection = connection;
    this.db = db;
    this.threadID = threadCount++;
  }
  
  /**
   * Handles the message passing and invocation of various methods based
   * on communication with the client.
   */
  @Override
  public void run(){   

      try {
        
        // Get hostname of client
        String clientName = connection.getInetAddress().getHostName()
          + "[" + threadID + "]";
        
        // Announce connection
        
        BizTrackMEServer.logEvent("event", clientName + " connected.");
        
        // This streams data FROM the client
        ObjectInputStream in = new ObjectInputStream(
          connection.getInputStream()
        );
        
        // This streams data TO the client
        ObjectOutputStream out = new ObjectOutputStream(
          connection.getOutputStream()
        );
        out.flush();
        
        // This will store requests as they come
        String req,
               ID;
        
        /**
         * This is the routing loop, it should be repeated for the duration of a
         * connection if all goes correctly.
         */
        do{
          
          req = in.readUTF();
          BizTrackMEServer.logEvent("event", clientName + " >>> " + req );
          
          switch (req) {
            case "VIEW_PROD":
              out.writeObject(db.getProducts());
              out.flush();
              break;
            case "VIEW_CUST":
              out.writeObject(db.getCustomers());
              out.flush();
              break;
            case "ADD_PROD":
              db.addProduct((Product) this.readObject(in));
              BizTrackMEServer.logEvent("event", "Product received");
              break;
            case "ADD_CUST":
              db.addCustomer((Customer) this.readObject(in));
              BizTrackMEServer.logEvent("event", "Product received");
              break;
            case "SEARCH_CUST":
              out.writeObject(db.searchCustomers( in.readUTF() ));
              break;
            case "SEARCH_PROD":
              out.writeObject(db.searchProducts( in.readUTF() ));
              break;
            case "UPDATE_PROD":
              ID = in.readUTF();
              db.updateProduct(ID, (Product)this.readObject(in));
              break;
            case "UPDATE_CUST":
              ID = in.readUTF();
              db.updateCustomer(ID, (Customer)this.readObject(in));
              break;
            case "DELETE_PROD":
              db.remove("products",in.readUTF());
              break;
            case "DELETE_CUST":
              db.remove("customers",in.readUTF());
              break;
            case "TERMINATE":
              BizTrackMEServer.logEvent("event", "Client initiated kill.");
              db.close();
              System.exit(0);
            case "CLIENT_DISCONNECT":
              BizTrackMEServer.logEvent("event", clientName + " disconnected.");
              break;
            default:
              out.writeUTF("MESSAGE NOT RECOGNIZED");
              break;
          }
          
        }while(!req.equals("CLIENT_DISCONNECT"));
      } catch (IOException ex) {
        BizTrackMEServer.logEvent("error", "Router IO Error!\n" + ex.getMessage());
      }
  }
  
  /**
   * Gets an object from the provided input stream. Mostly just convenience
   * in terms of not having to repeatedly write try/catches.
   * @param in ObjectInputStream to be pulled from
   * @return Object Be sure to cast as desired
   */
  private Object readObject( ObjectInputStream in ){
    try {
      return in.readObject();
    } catch (IOException ex) {
      BizTrackMEServer.logEvent("error", "IO Error!" + ex.getMessage());
    } catch (ClassNotFoundException ex) {
      BizTrackMEServer.logEvent("error", "Class error!" + ex.getMessage());
    }
    return null;
  }
    
}
