package biztrackme.common;

/**
 *
 * @author Eran
 */
public abstract class Product {
  
  // Static class variable for tracking the number of instantiated objects
  public static int numberOfProducts = 0;
  
  String  productName,
          sku;
  double  price;

  /**
   * Overloaded constructor for streamlined product creation. Also increments 
   * the class variable.
   * @param productname
   * @param sku
   * @param price 
   */
  public Product(String productname, String sku, double price) {
    
    this.productName = productname;
    this.sku = sku;
    this.price = price;
    
    numberOfProducts++;
  }

  /**
   * Override the no args constructor as well to ensure the class variable
   * gets incremented either way.
   */
  public Product() {
    numberOfProducts++;
  }
  
  /**
   * Quick export of product data for transmission
   * @return productData  String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    // Use system property rather than \n for better compatibility
    String nl = System.getProperty("line.separator");

    sb.append(this.productName).append(nl);
    sb.append(this.sku).append(nl);
    sb.append(String.valueOf(this.price)).append(nl);

    return sb.toString();
  }
  
  // Typical getters and setters...
  public static int getNumberOfProducts() {
    return numberOfProducts;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
