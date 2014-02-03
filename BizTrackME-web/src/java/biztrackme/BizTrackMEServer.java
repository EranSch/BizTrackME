package biztrackme;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Eran
 */
public class BizTrackMEServer {
  
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
