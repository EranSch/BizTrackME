package biztrackme.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Eran
 */
public class CustomerStore extends DataStore implements Serializable {
  
  private ArrayList<Customer> records = new ArrayList();
  
  /**
   * This constructor attempts to fill out the class's ArrayList with Customer
   * objects. The text file given to it must have a very specific format...
   * 
   *  1|  NAME
   *  2|  ADDRESS
   *  3|  PHONE
   *      [...]
   * 
   * Assuming this pattern is maintained, the result should be a happy, clean 
   * record of the entire file's worth of Customers.
   * 
   * @param path Path to the text file containing existing records
   */
  public CustomerStore(String path) {
    
    // Fields for line reading logic
    String line;
    int lineNum = 1;
    
    // Fields for incoming data
    String  name = "",
            phone = "",
            address = "";
    
    try {
      
      FileReader file = new FileReader(path);
      BufferedReader reader = new BufferedReader(file);

      try {
        while ((line = reader.readLine()) != null) {
          switch(lineNum%3){
            case 1: // First line
              name = line;
              lineNum++;
              break;
            case 2: // Second line
              address = line;
              lineNum++;
              break;
            case 0: // Third line
              phone = line;
              records.add(new Customer(name, address, phone));
              lineNum++;
              break;
            default:
              break;
          }
        }
                
      } catch (IOException ex) {
        System.out.println("File read error");
      }

    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
    }
    
    System.out.println("Customer data loaded! [" + records.size() + "]");
    
  }

  @Override
  public ArrayList<Customer> getRecords() {
    return records;
  }

  /**
   * Adds a record to the ArrayList. This method requires that the String 
   * included as an argument be formatted with each attribute on a separate
   * line. Format should be as follows:
   * 
   *  1|  NAME
   *  2|  ADDRESS
   *  3|  PHONE
   * 
   * This method will only read the first three lines of the submitted
   * String.
   * @param recordData Strong
   * @return success boolean
   */
  @Override
    public boolean addRecord(String recordData) {
    
    // Split up data by lines
    String[] record = recordData.split("\n");
    
    try{
      records.add(new Customer(record[0], record[1], record[2]));
      return true;
    }catch(Exception e){
      System.out.println( "Add record failed!" + e.getMessage());
      return false;
    }
  }

  /**
   * Conveniently prepares and returns a formatted String output 
   * @return String Formatted output of all customer records held in memory 
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String lb = System.getProperty("line.separator");
    
    for ( Customer c : records) {
      
      // Extract data
      sb.append(c.getName()).append(lb);
      sb.append(c.getAddress()).append(lb);
      sb.append(c.getPhone()).append(lb); 
    }
    return sb.toString();
  }

  
}
