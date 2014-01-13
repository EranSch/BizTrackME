package biztrackme.server;

import biztrackme.common.ProductStore;
import biztrackme.common.CustomerStore;

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

    // Instantiate the Router
    Router router = new Router(c, p);
    
    // Open the socket
    Server s = new Server(LISTEN_PORT);
    
    // Begin routing requests
    router.route(s.server);
  }
}
