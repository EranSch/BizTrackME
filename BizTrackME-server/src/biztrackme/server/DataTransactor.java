package biztrackme.server;

import java.util.ArrayList;

/**
 * Just a basic interface for the data storage classes
 * @author Eran
 */
public interface DataTransactor {
  
  public ArrayList getRecords();
  public boolean addRecord( String recordData );
  
  @Override
  public String toString();
  
}
