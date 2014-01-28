package biztrackme.server;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Eran
 */
public class BizTrackMEServer {
  
  String  DB_URL        = "jdbc:mysql://localhost/it351db";
  int     LISTEN_PORT   = 12345;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    BizTrackMEServer app = new BizTrackMEServer();
    app.init();
  }   
  

  /**
   * Initializes the server. This includes establishing database connection, 
   * opening a server socket, and listening for requests.
   */
  private void init() {
    
    //Connect the Database
    MySQLAccess db = new MySQLAccess(DB_URL, "ctuonline", "student");

    // Open the socket
    Server s = new Server(LISTEN_PORT);
    
    // Listen for connections, spin up a new thread for each one
    while(true){
      
      Socket socket = null;
      try {
        socket = s.server.accept();
      } catch (IOException ex) {
        BizTrackMEServer.logEvent(
          "error", 
          "Failed to establish connection\n" + ex.getMessage());
      }

      // Instantiate new thread
      Thread client = new Thread(new Router(socket, db));

      // Off it goes!
      client.start();
    }
    
  }
  
  /**
   * Writes log entry to console with timestamp. Set type to either "error" or 
   * "event" in order to also include visual indicator. 
   * @param type
   * @param message 
   */
  public static void logEvent(String type, String message){
    
    // Get the time
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(
      Calendar.getInstance().getTime());
    
    // Parse the parameters
    switch(type){
      case "error":
        System.err.println(timeStamp + " X " + message);
        break;
      case "event":
      default:
        System.out.println(timeStamp + " | " + message);
        break;
    }
  }
  
}
