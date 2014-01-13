package biztrackme.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Eran
 */
public class Server {
  
  public ServerSocket server;

  /**
   * Instantiates a server socket on the requested port
   * @param listenPort 
   */
  public Server(int listenPort){  
    try {
      server = new ServerSocket(listenPort);
    } catch (IOException ex) {
      System.err.println("Failed to open socket\n" + ex.getMessage());
      server = null;
    }
  } 
}
