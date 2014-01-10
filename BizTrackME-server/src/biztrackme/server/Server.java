package biztrackme.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Eran
 */
public class Server {
  
  public final ServerSocket server;

  /**
   * Instantiates a server on the requested port
   * @param listenPort
   * @throws IOException 
   */
  public Server(int listenPort) throws IOException {  
    server = new ServerSocket(listenPort);
  }
  
}
