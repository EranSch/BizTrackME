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
  
  private final CustomerStore c;
  private final ProductStore p;
  private final Socket connection;

  /**
   * This is the preferred means of instantiating this class. 
   * @param connection
   * @param cust CustomerStore
   * @param prod ProductStore 
   */
  public Router(Socket connection, CustomerStore cust, ProductStore prod) {
    this.connection = connection;
    this.c = cust;
    this.p = prod;
  }
  
  /**
   * Handles the message passing and invocation of various methods based
   * on communication with the client.
   */
  @Override
  public void run(){   

      try {
        
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
