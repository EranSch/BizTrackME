package biztrackme.server;

import biztrackme.common.ProductStore;
import biztrackme.common.CustomerStore;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Eran
 */
public class BizTrackMEServer {
  
  String  CUSTOMER_DATA = "customers.txt",
          PRODUCT_DATA  = "products.txt";
  int     LISTEN_PORT   = 12345;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    BizTrackMEServer app = new BizTrackMEServer();
    app.init();
  }   

  /**
   * Initializes the server. This includes establishing datastores, opening
   * a server socket, and listening for any requests.
   */
  private void init() {
    
    // Load datastores
    CustomerStore c = new CustomerStore(CUSTOMER_DATA);
    ProductStore p = new ProductStore(PRODUCT_DATA);

    
    // Open the socket
    Server s = new Server(LISTEN_PORT);
    
    while(true){
      
      // Listen for connections
      Socket connection = null;
      try {
        connection = s.server.accept();
      } catch (IOException ex) {
        System.err.println("Failed to establish connection\n" + ex.getMessage());
      }

      Thread client = new Thread(new Router(connection, c, p));

      client.start();
      
    }
  }
}
