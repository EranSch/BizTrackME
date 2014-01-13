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
   * @throws IOException 
   */
  public void route( ServerSocket server ) throws IOException{   

    while(true){
      
      Socket connection = server.accept();
      
      String clientName = connection.getInetAddress().getHostName();
      
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
      
      
      String req;
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

    }    
  }
  
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
