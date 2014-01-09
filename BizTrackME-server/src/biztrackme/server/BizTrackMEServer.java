package biztrackme.server;

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
    c.addRecord("Eran Schoellhorn\n15907 NW 188th St\n(970) 325-3726");
    System.out.print(c.toString());
    
  }
  
}
