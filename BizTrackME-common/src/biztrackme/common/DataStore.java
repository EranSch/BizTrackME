package biztrackme.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract for data stores
 * @author Eran
 */
public abstract class DataStore implements DataTransactor {
  
  String path;
  
  public void writeRecord( String record ){

        try {
          File file = new File(this.path);
          try (BufferedWriter output = 
            new BufferedWriter(
              new FileWriter(file, true)
            )) {
            output.write(record);
            output.close();
          }
        } catch (FileNotFoundException ex) {
          System.err.println("File not found!");
        } catch (IOException ex) {
          System.err.println("IO Error!");
        }

      }
  
}
