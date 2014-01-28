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
      BizTrackMEServer.logEvent("event", "Listening on port " + listenPort);
    } catch (IOException ex) {
      BizTrackMEServer.logEvent("error", "Failed to open socket\n" + ex.getMessage());
      server = null;
    }
  } 
}
