package biztrackme.common;

/**
 *
 * @author Eran
 */
public class Customer extends Person {
  
  String address;
  
  /**
   * Constructor used for streamlined instantiation of customer objects!
   * @param name    Customer's name
   * @param phone   Customer's phone
   * @param address Customer's address
   */

  public Customer(String name, String address, String phone ) {
    this.name = name;
    this.phone = phone;
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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
    
    sb.append(this.name).append(nl);
    sb.append(this.phone).append(nl);
    sb.append(this.address).append(nl);
    
    return sb.toString();
    
  }
}
