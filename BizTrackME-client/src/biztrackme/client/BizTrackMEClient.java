package biztrackme.client;

import biztrackme.common.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eran
 */
public class BizTrackMEClient {
  
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private Socket serverSocket;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    BizTrackMEClient client = new BizTrackMEClient();
    
    BufferedReader input = new BufferedReader(
      new InputStreamReader(System.in)
    );
    
    try {
      client.connect("localhost", 12345);
      client.establishStreams();
      
      // wait for ACK
      client.readString();
      
      client.sendString("VIEW_PROD");
      
      System.out.println(client.readString());
       
      client.sendString("TERMINATE");
      
      
    } catch (IOException ex) {
      Logger.getLogger(BizTrackMEClient.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
  public void connect( String host, int port ) throws IOException{
    serverSocket = new Socket(host, port);
  }
  
  public void establishStreams() throws IOException{
    
    // This streams data TO the client
    out = new ObjectOutputStream(
      serverSocket.getOutputStream()
    );
    out.flush();
    
    // This streams data FROM the client
    in = new ObjectInputStream(
      serverSocket.getInputStream()
    );
    
  }
  
  public void sendString(String message){
    try {
      out.writeUTF(message);
      out.flush();
      System.out.println("Message sent. ["+message+"]");
    } catch (IOException ex) {
      System.err.println("Failed to send message");
    }
  }
  
  public String readString(){
    String input;
    try {
      input = in.readUTF();
    } catch (IOException ex) {
      System.err.println("Cannot read message");
      input = "ERR";
    }
    return input;
  }
  
  public ArrayList getProducts(){
    this.sendString("VIEW_PROD");
    try {
      ArrayList prods = (ArrayList) in.readObject();
      return prods;
    } catch (IOException ex) {
      System.err.println("IO Error\n"+ex.getMessage());
      return null;
    } catch (ClassNotFoundException ex) {
      System.err.println("Unexpected object type.");
      return null;
    }
  }
  
}
