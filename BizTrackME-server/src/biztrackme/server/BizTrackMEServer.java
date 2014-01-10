package biztrackme.server;

import java.io.IOException;

/**
 *
 * @author Eran
 */
public class BizTrackMEServer {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    CustomerStore c = new CustomerStore("customers.txt");   
    ProductStore p = new ProductStore("products.txt");
    Router router = new Router(c, p);
    
    try {
      Server s = new Server(12345);
      router.route(s.server);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
    
  }   
}
