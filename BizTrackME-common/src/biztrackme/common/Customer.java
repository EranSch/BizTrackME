package biztrackme.common;

import java.io.Serializable;

/**
 *
 * @author Eran
 */
public class Customer extends Person implements Serializable {
  int ID;
  String address;
  
  /**
   * Constructor used for streamlined instantiation of customer objects!
   * @param firstName
   * @param lastName
   * @param phone   Customer's phone
   * @param address Customer's address
   */

  public Customer(
    String firstName, 
    String lastName, 
    String address, 
    String phone 
  ) {
    this(0, firstName, lastName, address, phone);
  }
  
  public Customer(
    int ID, 
    String firstName, 
    String lastName, 
    String address, 
    String phone 
  ) {
    this.ID = ID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getID() {
    return ID;
  }
  
  
  /**
   * Reduce customer information into string data
   * @return customerData String
   */

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    // Use system property rather than \n for better compatibility
    String nl = System.getProperty("line.separator");
    
    sb.append(this.firstName).append(" ").append(this.lastName).append(nl);
    sb.append(this.address).append(nl);
    sb.append(this.phone).append(nl);
    
    return sb.toString();
    
  }
}
