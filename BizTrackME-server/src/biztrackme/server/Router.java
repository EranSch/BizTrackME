package biztrackme.server;

import biztrackme.common.ProductStore;
import biztrackme.common.CustomerStore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
      System.out.println( connection.getInetAddress().getHostName() + 
        " connected.");

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
        System.out.println(req);
        
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
            // @TODO Add product method
            break;
          case "ADD_CUST":
            //@TODO Add customer method
            break;
          case "TERMINATE":
            System.out.println("Client initiated kill.");
            System.exit(0);
          default:
            out.writeUTF("MESSAGE NOT RECOGNIZED");
            break;
        }
        
      }while(!req.equals("TERMINATE"));

    }    
  }
}
