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
public class ProductStore extends DataStore implements Serializable  {
  
  private ArrayList<Product> records = new ArrayList();
  
  
  /**
   * This constructor attempts to fill out the class's ArrayList with Product
   * objects. The text file given to it must have a very specific format...
   *
   *  1| NAME 
   *  2| SKU
   *  3| PRICE (must be parsable to double)
   *     [...]
   *
   * Assuming this pattern is maintained, the result should be a happy, clean
   * record of the entire file's worth of Products.
   *
   * @param path Path to the text file containing existing records
   */
  public ProductStore(String path) {
    
    this.path = path;
    
    // Fields for line reading logic
    String line;
    int lineNum = 1;

    // Fields for incoming data
    String  name = "",
            sku = "";
    double  price = 0;

    try {

      FileReader file = new FileReader(this.path);
      BufferedReader reader = new BufferedReader(file);

      try {
        while ((line = reader.readLine()) != null) {
          switch (lineNum % 3) {
            case 1: // First line
              name = line;
              lineNum++;
              break;
            case 2: // Second line
              sku = line;
              lineNum++;
              break;
            case 0: // Third line
              try{
                price = Double.parseDouble(line);
                records.add(new Product(name, sku, price));
                lineNum++;
                break;
              }catch(NumberFormatException ex){
                System.out.println("Error during input!\n"
                  + "Unable to parse double at line: " + lineNum + "\n"
                  + ex.getMessage() );
                return;
              }
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
    
    System.out.println("Product data loaded! [" + records.size() + "]");
    
  }

  @Override
  public ArrayList<Product> getRecords() {
    return records;
  }

  /**
   * Adds a record to the ArrayList. This method requires that the String
   * included as an argument be formatted with each attribute on a separate
   * line. Format should be as follows:
   *
   * 1| NAME 
   * 2| SKU 
   * 3| PRICE
   *
   * This method will only read the first three lines of the submitted String.
   *
   * @param recordData Strong
   * @return success boolean
   */
  @Override
  public boolean addRecord(String recordData) {
 
    // Split up data by lines
    String[] record = recordData.split("\n");

    try {
      records.add(new Product(
        record[0], 
        record[1], 
        Double.parseDouble(record[2])
      ));
      return true;
    } catch (NumberFormatException e) {
      System.out.println("Add record failed!" + e.getMessage());
      return false;
    }
  }

  /**
   * Conveniently prepares and returns a formatted String output
   *
   * @return String Formatted output of all product data held in memory
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String lb = System.getProperty("line.separator");

    for (Product p : records) {

      // Extract data
      sb.append(p.getProductName()).append(lb);
      sb.append(p.getSku()).append(lb);
      sb.append(p.getPrice()).append(lb);
    }
    return sb.toString();
  }
  
}
