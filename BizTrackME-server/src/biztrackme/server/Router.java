package biztrackme.server;

import biztrackme.common.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eran
 */
public class Router {
  
  private final CustomerStore c;
  private final ProductStore p;

  /**
   * This is the preferred means of instantiating this class. 
   * @param cust CustomerStore
   * @param prod ProductStore 
   */
  public Router(CustomerStore cust, ProductStore prod) {
    this.c = cust;
    this.p = prod;
    System.out.println("Router Instantiated.");
  }

  /**
   * Please, just don't use this Constructor!
   */
  public Router() {
    this.c = null;
    this.p = null;
  }
  
  /**
   * Handles the message passing and invocation of various methods based
   * on communication with the client.
   * @param server 
   */
  public void route( ServerSocket server ){   

    while(true){
      try {
        // Listen for connections
        Socket connection = server.accept();
        
        // Get hostname of client
        String clientName = connection.getInetAddress().getHostName();
        
        // Announce connection
        System.out.println( clientName + " connected.");
        
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
        String req;
        
        /**
         * This is the routing loop, it should be repeated for the duration of a
         * connection if all goes correctly.
         */
        do{
          
          req = in.readUTF();
          System.out.println( clientName + ">>>" + req );
          
          switch (req) {
            case "VIEW_PROD":
              out.writeObject(p);
              out.flush();
              break;
            case "VIEW_CUST":
              out.writeObject(c);
              out.flush();
              break;
            case "ADD_PROD":
              Product prod = (Product) this.readObject(in);
              p.getRecords().add(prod);
              p.writeRecord(prod.toString());
              System.out.println(prod);
              break;
            case "ADD_CUST":
              Customer cust = (Customer) this.readObject(in);
              c.getRecords().add(cust);
              c.writeRecord(cust.toString());
              System.out.println(cust);
              break;
            case "TERMINATE":
              System.out.println("Client initiated kill.");
              System.exit(0);
            case "CLIENT_DISCONNECT":
              System.out.println(clientName + " disconnected.");
              break;
            default:
              out.writeUTF("MESSAGE NOT RECOGNIZED");
              break;
          }
          
        }while(!req.equals("CLIENT_DISCONNECT"));
      } catch (IOException ex) {
        System.err.println("Router IO Error!\n" + ex.getMessage());
      }

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
      System.err.println("IO Error!" + ex.getMessage());
    } catch (ClassNotFoundException ex) {
      System.err.println("Class error!" + ex.getMessage());
    }
    return null;
  }
    
}
