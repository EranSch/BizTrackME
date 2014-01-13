package biztrackme.server;

import biztrackme.common.ProductStore;
import biztrackme.common.CustomerStore;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Eran
 */
public class BizTrackMEServer {
  
  String CUSTOMER_DATA = "customers.txt";
  String PRODUCT_DATA  = "products.txt";
  
  CustomerStore c;
  ProductStore p;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    BizTrackMEServer app = new BizTrackMEServer();
    app.init();
  }   

  private void init() {
    
    c = new CustomerStore(CUSTOMER_DATA);
    p = new ProductStore(PRODUCT_DATA);

    Router router = new Router(c, p);

    try {
      Server s = new Server(12345);
      router.route(s.server);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }


  }
}
